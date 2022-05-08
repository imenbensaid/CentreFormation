package com.cenfor.app.respositories;

import com.cenfor.app.entities.EtudiantsParFormation;
import com.cenfor.app.entities.Formation;
import com.cenfor.app.entities.MoyParFormatuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoyParFormateurRepository extends  JpaRepository<MoyParFormatuer,Long> {

}
