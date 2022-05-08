package com.cenfor.app.respositories;
import com.cenfor.app.entities.EtudiantsParFormation;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfor.app.entities.Formation ;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormationRepository extends  JpaRepository<Formation,Long> {


   /* create or REPLACE VIEW EtudiantsParFormation as SELECT formation.id ,titre , count(etin.Etudiant_id) as nbrEtudiant from
    etudiantinscriptions etin JOIN formation on formation.id=etin.formations_id group by formation.id, titre;

    CREATE or REPLACE VIEW moyParFormatuer as select formateur.id id , nom , sum(etf.nbrEtudiant)/COUNT(etf.id) moyetudiant  from
     formateur join  formation f on formateur.id=f.formateur_id JOIN etudiantsparformation etf
      on f.id=etf.id GROUP by formateur.id ,formateur.nom ;
    */

    @Query(value = " SELECT id ,titre , count(etin.Etudiant_id) as nbrEtudiant from\n" +
            "    etudiantinscriptions etin JOIN formation on formation.id=etin.formations_id group by titre;  ",
            nativeQuery = true)
    List<EtudiantsParFormation> findAllEtudiantParFormation();


}
