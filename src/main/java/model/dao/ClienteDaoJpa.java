/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Animal;
import model.Cliente;



public class ClienteDaoJpa implements InterfaceDao<Cliente> {

    @Override
    public void incluir(Cliente entidade) throws Exception {
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
    public void editar(Cliente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Cliente entidade) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();

            Cliente c1 = em.find(Cliente.class, entidade.getId());
            em.remove(c1);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente pesquisarPorId(int id) throws Exception {
        Cliente c = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();

            c = em.find(Cliente.class, id);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return c;
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> lista = null;
        EntityManager em = ConnFactory.getEntityManager();
        try {
            em.getTransaction().begin();
            lista = em.createQuery("FROM Cliente c").getResultList();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return lista;
    }

    @Override
    public List<Cliente> filtrarPorNome(String nome) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();

        Query query = em.createNamedQuery("Cliente.filtrarPorNome");
        query.setParameter("nome", nome);
        List<Cliente> resultado = query.getResultList();
        return resultado;
    }

    @Override
    public void incluirR(Cliente entidade) throws Exception {
         EntityManager em = ConnFactory.getEntityManager();
         try {
            em.getTransaction().begin();
            for (Animal a : entidade.getAnimal()) {
                em.persist(a);
            }
            em.persist(entidade);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluirR(int id) throws Exception {
        EntityManager em = ConnFactory.getEntityManager();
         try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
    }


}
