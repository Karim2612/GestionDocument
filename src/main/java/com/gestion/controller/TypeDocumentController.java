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
//import com.gestion.model.Document;
import com.gestion.model.TypeDocument;
//import com.gestion.repository.DocumentRepository;
import com.gestion.repository.TypeDocumentRepository;
//import com.gestion.repository.LabelRepository;
//import com.gestion.repository.CategorieRepository;

@Controller
public class TypeDocumentController {

	//@Autowired
	//private DocumentRepository documentRepository;
	
	@Autowired
	private TypeDocumentRepository typeDocumentRepository;
	
//	@Autowired
//	private Type_documentRepository type_documentRepository;
//	
//	@Autowired
//	private LabelRepository labelRepository;
//	
//	@Autowired
//	private CategorieRepository categorieRepository;
		
	@GetMapping("/type_document")
	public String showDocumentList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/type_document/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<TypeDocument> page = typeDocumentRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));
		
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listDocuments", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		
		return "type_document";
	}
	
	@GetMapping("/type_document/new")
	public String showCreateNewDocumentForm(Model model) {
		model.addAttribute("document", new TypeDocument());
		return "type_document_form";
	}
	
	@PostMapping("/type_document/save")
	public String saveDocument(TypeDocument typedocument) {
		typeDocumentRepository.save(typedocument);
		return "redirect:/type_document";
	}
	
	@GetMapping("/type_document/edit/{id}")
	public String showCreateNewDocumentForm(@PathVariable Integer id, Model model) {
		model.addAttribute("document", typeDocumentRepository.findById(id).get());
		return "type_document_form";
	}
	
	@GetMapping("/type_document/delete/{id}")
	public String deleteDocument(@PathVariable Integer id) {
		typeDocumentRepository.deleteById(id);
		return "redirect:/type_document";
	}
}
