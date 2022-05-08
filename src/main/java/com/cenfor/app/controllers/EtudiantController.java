package com.cenfor.app.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.cenfor.app.entities.Formation;
import com.cenfor.app.respositories.EtudiantRepository;
import com.cenfor.app.respositories.FormateurRepository;
import com.cenfor.app.respositories.FormationRepository;
import com.cenfor.app.services.FromationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cenfor.app.entities.Etudiant;
import com.cenfor.app.entities.Formateur;
import com.cenfor.app.services.EtudiantService;

@Controller()
@RequestMapping("/etudiant")
public class EtudiantController {
	
	@Autowired
	private EtudiantService etudiantService;
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Autowired
	private FromationServices formationServices ;

	@GetMapping("/")
	public String  getAllUsers(@RequestParam(name = "page" ,defaultValue = "0") int page ,
									 @RequestParam(name = "size" ,defaultValue = "5") int size ,Model model) {
		 List<Formation> listeformation = formationServices.getAllFromationsALL();
		 Page<Etudiant> listetudiants =etudiantRepository.findAll(PageRequest.of(page, size));
		model.addAttribute("pages", new int[listetudiants.getTotalPages()]);
		model.addAttribute("listetudiants",listetudiants.getContent());
		model.addAttribute("currentpage",page);

		model.addAttribute("listeformations",listeformation) ;

	return "etudiant/listeEtudiant" ;
	}

@RequestMapping("/create")
	public String createetudiant(Model model ){
		Etudiant etudiant = new Etudiant( );
	    List<Formation> listeformation = formationServices.getAllFromationsALL();
		 model.addAttribute("etudiant",etudiant);
	model.addAttribute("formationsid");
	    model.addAttribute("listeformations",listeformation) ;
		return  "etudiant/formEtudiant" ;
	}
@PostMapping(value = "/affecter")
public String affectFormation(@RequestParam("idetudiant") Long id ,@RequestParam("formationid") Long  formationid){

		System.out.println(id +"---------"+formationid);
		 Etudiant etudiant = etudiantRepository.getOne(id);
		Formation formation = formationServices.getFormation(formationid);
        System.out.println(etudiant);
		etudiant.getFormations().add(formation);

		 etudiantService.save(etudiant);
		return  "redirect:/etudiant/";
}
@PostMapping(value= "/save")
public String saveEtudiant(Model model ,  Etudiant etudiant ,@ModelAttribute("formationsid") Long id){

     System.out.println(id);
	 Formation  format = formationServices.getFormation(id);
	 Collection<Formation> listfor = new ArrayList<>();
	 listfor.add(format) ;
	 etudiant.setFormations(listfor);
	 System.out.println(etudiant);

	 if( etudiant.getNom() !=null && etudiant.getPrenom()!=null&& etudiant.getTel() !=null && etudiant.getDateN()!=null && etudiant.getDateN()!=null) {
		 etudiantService.save(etudiant);
	   return "redirect:/etudiant/";
   } 
	 model.addAttribute("massage","tous les champ sont obligatoire" );
	 model.addAttribute("etudiant",etudiant);
	 return  "etudiant/formEtudiant" ;
}

@RequestMapping("/update/{id}")
public ModelAndView updatetudiant(Model model ,@PathVariable("id")  Long id ){
	 Etudiant etudiant=etudiantService.getetudiant(id);
	 model.addAttribute("etudiant",etudiant);
	 return new ModelAndView("etudiant/update","etudiant",etudiant) ;
}

@RequestMapping("/delete-formation/{idf}/ide")
public String deleletFormation(@PathVariable("id") Long idf,@PathVariable("ide") Long ide){
		Formation formation = formationServices.getFormation(idf);
		Etudiant etudiant=etudiantService.getetudiant(ide);
		etudiant.getFormations().remove(formation);
		etudiantService.save(etudiant);
		return "redirect:/etudiant/";

}


@PostMapping("/update")
	public String updateEtudiant(Model model ,  Etudiant etudiant){
		if(etudiant.equals(null)) {
			model.addAttribute("massage","tous les champ sont obligatoire" );
			return  "etudiant/update";
		 }
		Etudiant ensEtudiant = etudiantService.getetudiant(etudiant.getId());
		etudiant.setFormations(ensEtudiant.getFormations());
		System.out.println(etudiant.getId());
		etudiantService.update(etudiant)  ;
		  return "redirect:/etudiant/";
	}

@RequestMapping("/delete/{id}")
	public String delateetudiant(Model model ,@PathVariable("id")  Long id ){
		Etudiant etudiant = new Etudiant( );
		 etudiantService.delete(id);
		 return "redirect:/etudiant/";
	}
	}
 