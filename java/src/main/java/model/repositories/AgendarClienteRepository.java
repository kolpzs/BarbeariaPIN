package model.repositories;

import model.entities.AgendarClienteEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AgendarClienteRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public AgendarClienteRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public AgendarClienteEntity create(Object object) {
        AgendarClienteEntity agendarClienteEntity = (AgendarClienteEntity) object;
        em.getTransaction().begin();
        em.persist(agendarClienteEntity);
        em.getTransaction().commit();
        return agendarClienteEntity;
    }

    @Override
    public AgendarClienteEntity findById(Long id) {
        return em.find(AgendarClienteEntity.class, id);
    }

    @Override
    public AgendarClienteEntity updateById(Object object) {
        AgendarClienteEntity agendarClienteEntity = (AgendarClienteEntity) object;
        em.getTransaction().begin();
        AgendarClienteEntity updatedAgendarClienteEntity = em.merge(agendarClienteEntity);
        em.getTransaction().commit();
        return updatedAgendarClienteEntity;
    }

    @Override
    public void delete(Long id) {
        AgendarClienteEntity agendarClienteEntity = findById(id);
        if (agendarClienteEntity != null) {
            em.getTransaction().begin();
            em.remove(agendarClienteEntity);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<AgendarClienteEntity> findAll() {
        TypedQuery<AgendarClienteEntity> query = em.createQuery("SELECT a FROM Agendar_Cliente a", AgendarClienteEntity.class);
        return query.getResultList();
    }

    public void close() {
        em.close();
    }

    public static void closeFactory() {
        emf.close();
    }
}
