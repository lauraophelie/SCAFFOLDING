package com.example.crud.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "games")
public class Games {
	@JoinColumn(name = "console")
	@ManyToOne
	Console console;
	@Column(name = "nom_jeu")
	String nomJeu;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;


	public Games(){}
	public Console getConsole(){
		return this.console;
	}
	public void setConsole(Console console){
		this.console = console;
	}
	public String getNomJeu(){
		return this.nomJeu;
	}
	public void setNomJeu(String nomJeu){
		this.nomJeu = nomJeu;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}

}
