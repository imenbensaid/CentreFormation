package com.cenfor.app.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cenfor.app.entities.*;
import com.cenfor.app.respositories.MoyParFormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cenfor.app.services.FormateurServices;
import com.cenfor.app.services.FromationServices ;
@Controller
@RequestMapping("/formateur")
public class FormateurController {
@Autowired
private FormateurServices formateurServices ;

@Autowired
private FromationServices formationServices ;

@Autowired
private MoyParFormateurRepository moyParFormateurRepository ;

  @GetMapping("/")
  public  String  getAllFormateurs(@RequestParam(name = "page" ,defaultValue = "0") int page ,
										 @RequestParam(name = "size" ,defaultValue = "5") int size ,Model model){
	   Page<Formateur> listformateurs =formateurServices.getAllFormateurs(PageRequest.of(page,size));
	   List<MoyParFormatuer> moyParFormatuers = moyParFormateurRepository.findAll();
	  ArrayList<String> nom = new ArrayList<>() ;
	  ArrayList<Double> moyetudiant =new ArrayList<>() ;
	  for (MoyParFormatuer ints :moyParFormatuers )
	  {
		  nom.add(ints.getNom());
		  moyetudiant.add(ints.getMoyetudiant());
	  }


	  model.addAttribute("pages", new int[listformateurs.getTotalPages()]);
	  model.addAttribute("listformateurs",listformateurs.getContent());
	  model.addAttribute("currentpage",page);
	  model.addAttribute("moyetudiant" ,moyetudiant);
	  model.addAttribute("nom" ,nom);
	   return  "formateur/listeformateur" ;
}

@RequestMapping("/create")
	public ModelAndView createFormateur(Model model ){
		Formateur formateur = new Formateur( );

		 model.addAttribute("formateur",formateur);
		return new ModelAndView("formateur/formformateur") ;
	}
  
@PostMapping(value= "/save")
public String saveFromateur(Model model ,  Formateur formateur  ){


	 if( formateur.getNom() !=null && formateur.getPrenom()!=null&& formateur.getTel() !=null ) {
		/* Optional<Formation> formation = formationServices.findbyid(idform);
		 List<Formation> formlis = null  ;
		 */

	   formateurServices.create(formateur);
	   return "redirect:/formateur/";
   } 
	 model.addAttribute("massage","tous les champ sont obligatoire" );
	 model.addAttribute("formateur",formateur);
	 return  "formateur/formformateur" ;
}
@RequestMapping("/update/{id}")
	public ModelAndView updateFormateur(Model model ,@PathVariable("id")  Long id ){
		Formateur formateur=formateurServices.getFormateur(id);

		return new ModelAndView("formateur/update","formateur",formateur) ;
	}

 @PostMapping("/update")
	public String  saveupdateFormateur(Model model ,  Formateur formateur){
		if(formateur.equals(null)) {
			model.addAttribute("massage","tous les champ sont obligatoire" );
			model.addAttribute("formateur" ,formateur);
			return  "formateur/update";
		}

		formateurServices.update(formateur)  ;
		return "redirect:/formateur/";
	}

	@RequestMapping("/delete/{id}")
	public String delateetudiant(Model model ,@PathVariable("id")  Long id ){
		formateurServices.delete(id);

		return "redirect:/formateur/";
	}

}
