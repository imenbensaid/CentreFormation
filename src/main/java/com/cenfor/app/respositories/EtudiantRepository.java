package com.cenfor.app.respositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfor.app.entities.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long>{
 Etudiant findByNni(Long nni) ;
}
