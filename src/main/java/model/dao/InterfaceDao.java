package model.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.List;


public interface InterfaceDao<T> {
    
    public abstract void incluir(T entidade) throws Exception;
    
    public abstract void incluirR(T entidade) throws Exception;
    
    public abstract void excluirR(int id) throws Exception;

    public abstract void editar(T entidade) throws Exception;

    public abstract void excluir(T entidade) throws Exception;

    public abstract T pesquisarPorId(int id) throws Exception;

    public abstract List<T> listar() throws Exception;
    
    public abstract List<T> filtrarPorNome(String nome) throws Exception;
}
