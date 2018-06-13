package com.benicio.brewer.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.benicio.brewer.model.Cerveja;

@Controller
public class CervejasController {

	@RequestMapping("/cervejas/novo")//vou digitar no browser
	public String novo() {
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attribute) { /* Mapeamento - Declaro o name da tag input do meu html*/
		if(result.hasErrors()) {
			model.addAttribute("mensagem", "Erro no formulário");
			return "cerveja/CadastroCerveja";
		}
		System.out.println("SKU: " + cerveja.getSku());
		System.out.println("Nome: " + cerveja.getNome());
		attribute.addFlashAttribute("mensagem", "Cerveja cadastrada com sucesso!!");
		return "redirect:/cervejas/novo";
	}

}
