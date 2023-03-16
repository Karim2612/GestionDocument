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
import com.gestion.model.Utilisateur;
import com.gestion.repository.UtilisateurRepository;
import com.gestion.repository.RoleRepository;


@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/utilisateurs")
	public String showUtilisateurList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/utilisateurs/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Utilisateur> page = utilisateurRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listUtilisateurs", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);

		return "utilisateurs";
	}

	@GetMapping("/utilisateurs/new")
	public String showCreateNewUtilisateurForm(Model model) {
		model.addAttribute("listRoles", roleRepository.findAll());
		model.addAttribute("utilisateur", new Utilisateur());
		return "utilisateur_form";
	}

	@PostMapping("/utilisateurs/save")
	public String saveUtilisateur(Utilisateur utilisateur) {
		
		utilisateurRepository.save(utilisateur);
		return "redirect:/utilisateurs";
	}

	@GetMapping("/utilisateurs/edit/{id}")
	public String showCreateNewUtilisateurForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listRoles", roleRepository.findAll());
		model.addAttribute("utilisateur", utilisateurRepository.findById(id).get());
		return "utilisateur_form";
	}

	@GetMapping("/utilisateurs/delete/{id}")
	public String deleteUtilisateur(@PathVariable Integer id) {
		utilisateurRepository.deleteById(id);
		return "redirect:/utilisateurs";
	}
}
