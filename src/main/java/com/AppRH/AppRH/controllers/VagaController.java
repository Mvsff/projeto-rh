package com.AppRH.AppRH.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Vagas;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;


@Controller
public class VagaController {
	
	private VagaRepository vr;
	private CandidatoRepository cr;
	
	//CADASTRA VAGA
	@RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
	public String form() {
		return "vaga/formVaga";
	} 
	

	@RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
	public String form(@Valid Vagas vagas, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/cadastarVaga";
		}
		
		vr.save(vagas);
		attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
		return "redirect:/cadastrarVaga";
		
	}
	
	
	//LISTA VAGAS
	@RequestMapping("/vagas")
	public ModelAndView listaVagas() {
		ModelAndView mv = new ModelAndView ("vaga/listaVaga");
		Iterable<Vagas>vagas = vr.findAll();
		mv.addObject("vagas", vagas);	
		return mv;
	}
	
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
		Vagas vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/detalhesVaga");
		mv.addObject("vaga", vaga);
		Iterable<Candidato>candidatos = cr.findByVagas(vaga);
		mv.addObject("candidatos", candidatos);
		
		return mv;
		
	}
	
	//DELETA VAGA
	@RequestMapping("/deletarVaga")
	public String deletarVaga(long codigo) {
		Vagas vaga = vr.findByCodigo(codigo);
		vr.delete(vaga);
		return "redirect:/vagas";	
	}
	
	public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato, 
			BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/{codigo}";
		}
		
		
		//RG DUPLICADO
		if(cr.findByRg(candidato.getRg()) != null) {
			attributes.addFlashAttribute("mensagem erro", "RG duplicado");
			return "redirect:/{codigo}";	
		}
		
		Vagas vaga = vr.findByCodigo(codigo);
		candidato.setVagas(vaga);
		cr.save(candidato);
		attributes.addFlashAttribute("mensagem", "Candidato adicionado com sucesso!");
		return "redirect:/{codigo}";	
	}
	
	//DELETA CANDIDATO PELO RG
	@RequestMapping("/deletarCandidato")
	public String deletarCandidato(String rg) {
		Candidato candidato = cr.findByRg(rg);
		Vagas vaga = candidato.getVagas();
		String codigo = "" + vaga.getCodigo();
		
		cr.delete(candidato);
		
		return "redirect:/" + codigo;
	}
	
	//MÉTODOS QUE ATUALIZAM VAGA
	//FORMULÁRIO DE EDIÇÃO DE VAGA
	@RequestMapping(value= "/editar-vaga", method = RequestMethod.GET)
	public ModelAndView editarVaga(long codigo) {
		Vagas vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("vaga/update-vaga");
		mv.addObject("vaga", vaga);
		return mv;
	}
	
	//UPDATE VAGA
	@RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
	public String updateVaga(@Valid Vagas vaga, BindingResult result, RedirectAttributes attributes) {
		vr.save(vaga);
		attributes.addFlashAttribute("sucess", "Vaga alterada com sucesso!");
		
		long codigoLong = vaga.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
	
	
	
}
