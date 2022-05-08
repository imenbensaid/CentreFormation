package com.cenfor.app.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Formateur {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String nom ;
	private String prenom ;
	private Long tel  ;
	
	
	
}
