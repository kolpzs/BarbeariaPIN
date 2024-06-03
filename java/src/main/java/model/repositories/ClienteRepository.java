package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.ClienteEntity;

public class ClienteRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public ClienteRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public ClienteEntity create(Object object) {
        ClienteEntity clienteEntity = (ClienteEntity) object;
        em.getTransaction().begin();
        em.persist(clienteEntity);
        em.getTransaction().commit();
        return clienteEntity;
    }

    @Override
    public ClienteEntity findById(Long id) {
        return em.find(ClienteEntity.class, id);
    }

    @Override
    public ClienteEntity updateById(Object object) {
        ClienteEntity clienteEntity = (ClienteEntity) object;
        em.getTransaction().begin();
        ClienteEntity updatedClienteEntity = em.merge(clienteEntity);
        em.getTransaction().commit();
        return updatedClienteEntity;
    }

    @Override
    public void delete(Long id) {
        ClienteEntity clienteEntity = findById(id);
        if (clienteEntity != null) {
            em.getTransaction().begin();
            em.remove(clienteEntity);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<ClienteEntity> findAll() {
        TypedQuery<ClienteEntity> query = em.createQuery("SELECT c FROM Cliente c", ClienteEntity.class);
        return query.getResultList();
    }
}
