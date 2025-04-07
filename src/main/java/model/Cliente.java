/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author evert
 */
@NamedQuery(name = "Cliente.filtrarPorNome", query = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome")
@Entity
public class Cliente {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
   
    
     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
      @JoinTable(
        name = "cliente_animal", 
        joinColumns = @JoinColumn(name = "cliente_id"), 
        inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
     
     
    private Collection<Animal> animal;

    public Cliente() {
    }
     
    public Cliente(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
         this.email = email;
        this.endereco = endereco;
       
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Animal> getAnimal() {
        return animal;
    }

    public void setAnimal(Collection<Animal> animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "cliente{" + "id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", email=" + email + '}';
    }
    
    
}
