package com.cenfor.app.services;
import com.cenfor.app.entities.Etudiant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

 

import com.cenfor.app.respositories.EtudiantRepository;

@Service
public class EtudiantService {

	    @Autowired
	    private EtudiantRepository etudiantRepository;

	    public List<Etudiant> getAllEtudiants() {
	        List<Etudiant> dbEtudiants = etudiantRepository.findAll();
	     /*   if (dbEtudiants.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }*/
	        // TODO Auto-generated method stub
	        return  dbEtudiants;
	    }
	    
	    public Etudiant save (Etudiant etudiant) {
	    	
	    	return etudiantRepository.save(etudiant) ;
	    }
       public Etudiant update (Etudiant etudiant) {
	    	return etudiantRepository.save(etudiant) ;
	    }
       public Boolean delete(Long id) {
    	    etudiantRepository.deleteById(id);
    	    return true ;
       }

	public Etudiant getetudiant(Long id ) {
	   return 	etudiantRepository.getOne(id);
		 
	}
	}

