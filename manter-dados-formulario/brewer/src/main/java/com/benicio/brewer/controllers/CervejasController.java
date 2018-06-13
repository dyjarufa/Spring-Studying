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
	public String novo(Cerveja cerveja) {//Quando coloco a classe no contrutor o spring entende que quero usar no modelo
		//model.addAttribute(new Cerveja()); //carrego a pagina com o objeto cerveja
		return "cerveja/CadastroCerveja";
	}
	
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attribute) { /* Mapeamento - Declaro o name da tag input do meu html*/
		if(result.hasErrors()) {//Verifica se tem algum erro
			//model.addAttribute("mensagem", "Erro no formulário");//insere msg no thymeleaf span da pagina html: ${mensagem}
			//model.addAttribute(cerveja);//Mantenho o objeto na sessão da pagina html
			//return "cerveja/CadastroCerveja"; /*Conceito de Forward*/
			return novo(cerveja);
		}
		
		/*Conceito de Redirect*/
		System.out.println("SKU: " + cerveja.getSku());
		System.out.println("Nome: " + cerveja.getNome());
		System.out.println("Descrição: " + cerveja.getDescricao());
		attribute.addFlashAttribute("mensagem", "Cerveja cadastrada com sucesso!!");//No redirect a msg some...Por isso uso o addFlashAttribute do RedirectAttributes
		return "redirect:/cervejas/novo"; /*Conceito de Forward*/ // Redireciono para a URL e não para a View
	}

}
