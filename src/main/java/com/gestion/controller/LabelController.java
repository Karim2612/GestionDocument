package com.gestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestion.Constants;
import com.gestion.model.Label;
import com.gestion.repository.DocumentRepository;
import com.gestion.repository.LabelRepository;
import com.gestion.repository.UtilisateurRepository;
import com.gestion.repository.DocumentRepository;

@Controller
public class LabelController {

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private DocumentRepository documentRepository;


	@GetMapping("/labels")
	public String showLabelList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/labels/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Label> page = labelRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listLabels", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);

		return "labels";
	}

	@GetMapping("/labels/new")
	public String showCreateNewLabelForm(Model model) {
		model.addAttribute("label", new Label());
		model.addAttribute("listDocuments", documentRepository.findAll());
		return "label_form";
	}

	@PostMapping("/labels/save")
	public String saveLabel(Label label) {
		
		labelRepository.save(label);
		return "redirect:/labels";
	}

	@GetMapping("/labels/edit/{id}")
	public String showCreateNewLabelForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listLabels", labelRepository.findAll());
		model.addAttribute("label", labelRepository.findById(id).get());
		return "label_form";
	}

	@GetMapping("/labels/delete/{id}")
	public String deleteLabel(@PathVariable Integer id) {
		labelRepository.deleteById(id);
		return "redirect:/labels";
	}
}
