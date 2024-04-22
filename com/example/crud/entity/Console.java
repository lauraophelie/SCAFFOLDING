package com.example.crud.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "console")
public class Console {
	@Column(name = "nom_console")
	String nomConsole;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;


	public Console(){}
	public String getNomConsole(){
		return this.nomConsole;
	}
	public void setNomConsole(String nomConsole){
		this.nomConsole = nomConsole;
	}
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id = id;
	}

}
