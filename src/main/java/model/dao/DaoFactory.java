
package model.dao;

public class DaoFactory {
    
    public static ClienteDaoJpa novoClienteDAO() throws Exception {
        return new ClienteDaoJpa();
    }
    public static AnimalDaoJpa novoAnimalDAO() throws Exception {
        return new AnimalDaoJpa();
    }
     public static ClienteAnimalDaoJpa novoClienteAnimalDAO() throws Exception {
        return new ClienteAnimalDaoJpa();
    }
    
}
