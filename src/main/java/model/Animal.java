/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 *
 * @author evert
 */
@NamedQuery(name = "Animal.filtrarPorNome", query = "SELECT c FROM Animal c WHERE c.nome LIKE :nome")
@Entity
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String especie;
    private String raca;
    
      @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean adotado = false;
      
      

    public Animal() {
    }

    
    public Animal(String nome, String especie, String raca) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
    }
    

    public Boolean getAdotado() {
        return adotado;
    }

    public void setAdotado(Boolean adotado) {
        this.adotado = adotado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", nome=" + nome + ", especie=" + especie + ", raca=" + raca + ", adotado=" + adotado + '}';
    }

  

    
  
    
    
    
    
}
