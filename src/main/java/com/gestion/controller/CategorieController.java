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
import com.gestion.model.Categorie;
import com.gestion.repository.CategorieRepository;
import com.gestion.repository.UtilisateurRepository;


@Controller
public class CategorieController {

	@Autowired
	private CategorieRepository categorieRepository;

//	@Autowired
//	private TypeDocumentRepository typeDocumentRepository;
//
//	@Autowired
//	private LabelRepository labelRepository;
//
//	@Autowired
//	private CategorieRepository categorieRepository;

	@GetMapping("/categories")
	public String showCategorieList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/categories/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Categorie> page = categorieRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listCategories", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);

		return "categories";
	}

	@GetMapping("/categories/new")
	public String showCreateNewCategorieForm(Model model) {
		model.addAttribute("categorie", new Categorie());
		return "categorie_form";
	}

	@PostMapping("/categories/save")
	public String saveCategorie(Categorie categorie) {
		categorieRepository.save(categorie);
		return "redirect:/categories";
	}

	@GetMapping("/categories/edit/{id}")
	public String showCreateNewCategorieForm(@PathVariable Integer id, Model model) {
		model.addAttribute("categorie", categorieRepository.findById(id).get());
		return "categorie_form";
	}

	@GetMapping("/categories/delete/{id}")
	public String deleteCategorie(@PathVariable Integer id) {
		categorieRepository.deleteById(id);
		return "redirect:/categories";
	}
}
