package com.cenfor.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cenfor.app.entities.Etudiant;
import com.cenfor.app.entities.Formateur;
import com.cenfor.app.respositories.FormateurRepository;

@Service
public class FormateurServices {
  @Autowired 
  private FormateurRepository formateurRepository ;
  public Page<Formateur> getAllFormateurs(Pageable pageable){
	  Page<Formateur> dbFormateurs = formateurRepository.findAll(pageable);
	  
	return  dbFormateurs  ;
  }
    public List<Formateur> getAllFormateurs( ){
        List<Formateur> dbFormateurs = formateurRepository.findAll();

        return  dbFormateurs  ;
    }

  public Formateur create (Formateur formateur) {
  	
  	return formateurRepository.save(formateur) ;
  }
 public Formateur update (Formateur formateur) {
  	return formateurRepository.save(formateur) ;
  }
 public Boolean delete(Long id) {
	 formateurRepository.deleteById(id);
	    return true ;
 }

    public Formateur getFormateur(Long id) {
       return formateurRepository.getById(id);
    }
}
