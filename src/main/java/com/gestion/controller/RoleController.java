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
import com.gestion.model.Role;
import com.gestion.repository.RoleRepository;
import com.gestion.repository.UtilisateurRepository;

@Controller
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@GetMapping("/roles")
	public String showRoleList(Model model) {
		return listByPage(model, 1, "id", "asc", "");
	}

	@GetMapping("/roles/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Page<Role> page = roleRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("listRoles", page.getContent());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);

		return "roles";
	}

	@GetMapping("/roles/new")
	public String showCreateNewRoleForm(Model model) {
		model.addAttribute("listUtilisateurs", utilisateurRepository.findAll());
		model.addAttribute("role", new Role());
		return "role_form";
	}

	@PostMapping("/roles/save")
	public String saveRole(Role role) {
		
		roleRepository.save(role);
		return "redirect:/roles";
	}

	@GetMapping("/roles/edit/{id}")
	public String showCreateNewRoleForm(@PathVariable Integer id, Model model) {
		model.addAttribute("listUtilisateurs", utilisateurRepository.findAll());
		model.addAttribute("role", roleRepository.findById(id).get());
		return "role_form";
	}

	@GetMapping("/roles/delete/{id}")
	public String deleteRole(@PathVariable Integer id) {
		roleRepository.deleteById(id);
		return "redirect:/roles";
	}
}
