package com.cenfor.app.services;
import com.cenfor.app.entities.EtudiantsParFormation;
import com.cenfor.app.respositories.EtudiantParFormationRepository;
import com.cenfor.app.respositories.FormationRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cenfor.app.entities.Formation;

@Service
public class FromationServices {

	

    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private EtudiantParFormationRepository etudiantParFormationRepository;

    public Page<Formation> getAllFromations(Pageable pageable) {
        Page<Formation> dbFormations = formationRepository.findAll(pageable);
//        if (dbFormations.isEmpty()) {
//            return  (HttpStatus.NOT_FOUND);
//        }
        // TODO Auto-generated method stub
        return  dbFormations;
    }
    public List<Formation> getAllFromationsALL() {
        List<Formation> dbFormations = formationRepository.findAll();
        return  dbFormations;
    }
    public Formation create (Formation formation) {
      	
      	return formationRepository.save(formation) ;
      }
 public Formation findbyid (Long  id) {
      	
      	return formationRepository.getById(id);
      }
     public Formation update (Formation formation) {

        return formationRepository.save(formation) ;
      }
     public Boolean delete(Long id) {
    	 formationRepository.deleteById(id);
    	    return true ;
     }

    public List<EtudiantsParFormation> getAllEtudiantParFormation() {
       return  etudiantParFormationRepository.findAll();
    }

    public Formation getFormation(Long id) {
        return formationRepository.getById(id);
    }
}
