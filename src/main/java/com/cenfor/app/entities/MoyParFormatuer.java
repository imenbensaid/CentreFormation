package com.cenfor.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
public class MoyParFormatuer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id ;
    private String nom ;
    private Double moyetudiant ;

}
