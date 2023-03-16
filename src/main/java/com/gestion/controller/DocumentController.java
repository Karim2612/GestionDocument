package com.gestion.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.gestion.model.Document;
import com.gestion.model.TypeDocument;
import com.gestion.repository.DocumentRepository;
import com.gestion.repository.UtilisateurRepository;
import com.gestion.repository.TypeDocumentRepository;
import com.gestion.repository.LabelRepository;
import com.gestion.repository.CategorieRepository;

@Controller
public class DocumentController {

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private TypeDocumentRepository typeDocumentRepository;

	@GetMapping("/documents")
	public String showDocumentList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/documents/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Document> page = documentRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listDocuments", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);

		return "documents";
	}

	@GetMapping("/documents/new")
	public String showCreateNewDocumentForm(Model model) {
		model.addAttribute("document", new Document());
		model.addAttribute("listLabels", labelRepository.findAll());
		model.addAttribute("listCategories", categorieRepository.findAll());
		model.addAttribute("listUtilisateurs", utilisateurRepository.findAll());
		model.addAttribute("listTypeDocuments", typeDocumentRepository.findAll());
		return "document_form";
	}

	@PostMapping("/documents/save")
	public String saveDocument(Document document, HttpServletRequest request) {
		int last = typeDocumentRepository.findAll().size();
		TypeDocument p = typeDocumentRepository.findAll().get(last-1);
		document.setTypeDocument(p);
		documentRepository.save(document);
		return "redirect:/documents";
	}

	@GetMapping("/documents/edit/{id}")
	public String showCreateNewDocumentForm(@PathVariable Integer id, Model model) {
		model.addAttribute("document", documentRepository.findById(id).get());
		model.addAttribute("listTypeDocuments", typeDocumentRepository.findAll());
		model.addAttribute("listUtilisateurs", utilisateurRepository.findAll());
		model.addAttribute("listLabels", labelRepository.findAll());
		return "document_form";
	}

	@GetMapping("/documents/delete/{id}")
	public String deleteDocument(@PathVariable Integer id, Model model) {
		documentRepository.deleteById(id);
		return "redirect:/documents";
	}
}
