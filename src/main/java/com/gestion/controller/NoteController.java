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
import com.gestion.model.Note;
import com.gestion.model.Utilisateur;
import com.gestion.model.Document;
import com.gestion.repository.NoteRepository;
import com.gestion.repository.UtilisateurRepository;
import com.gestion.repository.DocumentRepository;

@Controller
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@GetMapping("/notes")
	public String showNoteList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/notes/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Note> page = noteRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listNotes", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);

		return "notes";
	}

	@GetMapping("/notes/new")
	public String showCreateNewNoteForm(Model model) {
		model.addAttribute("note", new Note());
		model.addAttribute("listUtilisateurs", utilisateurRepository.findAll());	
		model.addAttribute("listDocuments", documentRepository.findAll());
		
		return "note_form";
	}

	@PostMapping("/notes/save")
	public String saveNote(Note note, HttpServletRequest request) {
		int last = utilisateurRepository.findAll().size();
		Utilisateur p = utilisateurRepository.findAll().get(last-1);
		note.setUtilisateur(p);
		
		int last2 = documentRepository.findAll().size();
		Document p2 = documentRepository.findAll().get(last2-1);
		note.setDocument(p2);
		
		noteRepository.save(note);
		return "redirect:/notes";
	}

	@GetMapping("/notes/edit/{id}")
	public String showCreateNewNoteForm(@PathVariable Integer id, Model model) {
		model.addAttribute("note", noteRepository.findById(id).get());
		model.addAttribute("listUtilisateurs", utilisateurRepository.findAll());
		model.addAttribute("listDocuments", documentRepository.findAll());
		return "note_form";
	}

	@GetMapping("/notes/delete/{id}")
	public String deleteNote(@PathVariable Integer id) {
		noteRepository.deleteById(id);
		return "redirect:/notes";
	}
}
