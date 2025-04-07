/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import model.Animal;
import model.Cliente;

/**
 *
 * @author evert
 * 
 */

@NamedQueries({
    @NamedQuery(
        name = "ClienteAnimal.filtrarPorClienteId",
        query = "SELECT ca FROM ClienteAnimal ca WHERE ca.cliente.id = :clienteId"
    ),
    @NamedQuery(
        name = "ClienteAnimal.filtrarPorAnimalId",
        query = "SELECT ca FROM ClienteAnimal ca WHERE ca.animal.id = :animalId"
    ),
    @NamedQuery(
        name = "ClienteAnimal.filtrarPorClienteEAnimal",
        query = "SELECT ca FROM ClienteAnimal ca WHERE ca.cliente.id = :clienteId AND ca.animal.id = :animalId"
    )
})
@Entity
@Table(name = "cliente_animal")
public class ClienteAnimal {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

  

 

    public ClienteAnimal(Cliente cliente, Animal animal) {
        this.cliente = cliente;
        this.animal = animal;
        
    }

    public ClienteAnimal() {
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

}
    

