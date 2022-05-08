package com.cenfor.app.controllers;
import com.cenfor.app.entities.*;

import com.cenfor.app.respositories.EtudiantParFormationRepository;
import com.cenfor.app.respositories.FormationRepository;
import com.cenfor.app.respositories.MoyParFormateurRepository;
import com.cenfor.app.services.FormateurServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.cenfor.app.services.FromationServices;
import org.springframework.web.servlet.ModelAndView;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class FormationController {
@Autowired 
private FromationServices formationServices ;
@Autowired
 private FormationRepository formationRepository ;
@Autowired
private EtudiantParFormationRepository etudiantParFormationRepository;
@Autowired
private MoyParFormateurRepository moyParFormateurRepository;
@Autowired
private FormateurServices formateurServices;
@RequestMapping("/")
public String getAllFormations(@RequestParam(name = "page" ,defaultValue = "0") int page ,
									 @RequestParam(name = "size" ,defaultValue = "5")
											 int size ,Model model){
	Page<Formation> listformation =formationServices.getAllFromations(PageRequest.of(page,size));
	List<EtudiantsParFormation> etudiantsParFormations = etudiantParFormationRepository.findAll();
	ArrayList<String> titre = new ArrayList<>() ;
	ArrayList<Long> nbreparfor =new ArrayList<>() ;
	for (EtudiantsParFormation ints :etudiantsParFormations )
	{
		titre.add(ints.getTitre());
		nbreparfor.add(ints.getNbrEtudiant());
	}




	System.out.println(etudiantsParFormations);

	model.addAttribute("pages", new int[listformation.getTotalPages()]);
	model.addAttribute("listformations",listformation.getContent());
	model.addAttribute("currentpage",page);
	model.addAttribute("titre",titre);
	model.addAttribute("nbreparfor",nbreparfor);
	 return "listeformation" ;
}

	@RequestMapping(value = "/create" ,method = RequestMethod.GET)
	public String createFormations(Model model){
		 Formation formation = new Formation();
		List<Formateur> listeformateurs = formateurServices.getAllFormateurs();
		model.addAttribute("listeformateurs",listeformateurs) ;
		 model.addAttribute("formation",formation);
		return   "formformation" ;
	}
	
@PostMapping(value = "/save" )
public String saveFormation(Model model ,  Formation formation ,@ModelAttribute("formateurid") Long id ){
	formation.setFormateur(formateurServices.getFormateur(id));
	System.out.println(formation);
	 if( formation.getTitre() !=null && formation.getFrais()!=null&& formation.getDatedbut()!=null && formation.getDatefin()!=null) {
	   formationServices.create(formation);
	   return "redirect:/";
    } 
	 model.addAttribute("massage","tous les champ sont obligatoire" );
	 model.addAttribute("formation",formation);
	 return  "formformation" ;
}

	@RequestMapping("/update/{id}")
	public ModelAndView updateFormation(Model model , @PathVariable("id")  Long id ){
		Formation formation=formationServices.getFormation(id);
		List<Formateur> listeformateurs = formateurServices.getAllFormateurs();
		model.addAttribute("listeformateurs",listeformateurs) ;
		return new ModelAndView("/update","formation",formation) ;
	}

	@PostMapping("/update")
	public String  saveupdateFormateur(Model model ,  Formation formation ,@ModelAttribute("formateurid") Long id ){
		if(formation.equals(null)) {
			model.addAttribute("massage","tous les champ sont obligatoire" );
			model.addAttribute("formation" ,formation);
			return  "/update";
		}
		formation.setFormateur(formateurServices.getFormateur(id));
		formationServices.update(formation)  ;
		return "redirect:/";
	}

	@RequestMapping("/delete/{id}")
	public String delateformation(Model model ,@PathVariable("id")  Long id ){
		formationServices.delete(id);

		return "redirect:/";
	}




}
