/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import model.Animal;
import model.ClienteAnimal;

/**
 *
 * @author evert
 */
public class ClienteAnimalDaoJpa implements InterfaceDao<ClienteAnimal>  {

    @Override
    public void incluir(ClienteAnimal entidade) throws Exception {
         EntityManager em = ConnFactory.getEntityManager();
         try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
    }

    @Override
    public void incluirR(ClienteAnimal entidade) throws Exception {
    }

    @Override
    public void excluirR(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(ClienteAnimal entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(ClienteAnimal entidade) throws Exception {
             EntityManager em = ConnFactory.getEntityManager();
              try {
            em.getTransaction().begin();
            ClienteAnimal ref = em.find(ClienteAnimal.class, entidade.getId());
            if (ref != null) {
                em.remove(ref);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public ClienteAnimal pesquisarPorId(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
         try {
            return em.find(ClienteAnimal.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<ClienteAnimal> listar() throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
         try {
            TypedQuery<ClienteAnimal> query = em.createQuery("SELECT c FROM ClienteAnimal c", ClienteAnimal.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ClienteAnimal> filtrarPorNome(String nome) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            TypedQuery<ClienteAnimal> query = em.createQuery("SELECT c FROM ClienteAnimal c WHERE c.cliente.nome LIKE :nome", ClienteAnimal.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<ClienteAnimal> filtrarPorClienteEAnimal(Integer clienteId, Integer animalId) throws Exception {
    EntityManager em = ConnFactory.getEntityManager();
    List<ClienteAnimal> resultado;

    try {
        em.getTransaction().begin();

        Query query;

        if (clienteId != null && animalId != null) {
            query = em.createNamedQuery("ClienteAnimal.filtrarPorClienteEAnimal");
            query.setParameter("clienteId", clienteId);
            query.setParameter("animalId", animalId);
        } else if (clienteId != null) {
            query = em.createNamedQuery("ClienteAnimal.filtrarPorClienteId");
            query.setParameter("clienteId", clienteId);
        } else if (animalId != null) {
            query = em.createNamedQuery("ClienteAnimal.filtrarPorAnimalId");
            query.setParameter("animalId", animalId);
        } else {
            query = em.createQuery("FROM ClienteAnimal ca");
        }

        resultado = query.getResultList();
        em.getTransaction().commit();
    } finally {
        em.close();
    }

    return resultado;
}

   
}

