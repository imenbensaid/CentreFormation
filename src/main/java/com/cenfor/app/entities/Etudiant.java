package com.cenfor.app.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Etudiant {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String nom ;
	private String prenom ;
	@Column(unique = true)
	private Long nni;
	private Long tel ;
	private String dateN;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "EtudiantInscriptions",  joinColumns={@JoinColumn(referencedColumnName="id")}
			, inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
	private Collection<Formation> formations ;

}
