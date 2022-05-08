package com.cenfor.app.entities;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Formation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String titre ;
	private String datedbut;
	private String datefin ;
	private Float frais ;
	private String niveau ;
	private String langue ;
	@ManyToOne(fetch=FetchType.EAGER)
	private  Formateur formateur;



}
