package model.repositories;

import model.entities.AgendarEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AgendarRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public AgendarRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public AgendarEntity create(Object object) {
        AgendarEntity agendarEntity = (AgendarEntity) object;
        em.getTransaction().begin();
        em.persist(agendarEntity);
        em.getTransaction().commit();
        return agendarEntity;
    }

    @Override
    public AgendarEntity findById(Long id) {
        return em.find(AgendarEntity.class, id);
    }

    @Override
    public AgendarEntity updateById(Object object) {
        AgendarEntity agendarEntity = (AgendarEntity) object;
        em.getTransaction().begin();
        AgendarEntity updatedAgendarEntity = em.merge(agendarEntity);
        em.getTransaction().commit();
        return updatedAgendarEntity;
    }

    @Override
    public void delete(Long id) {
        AgendarEntity agendarEntity = findById(id);
        if (agendarEntity != null) {
            em.getTransaction().begin();
            em.remove(agendarEntity);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<AgendarEntity> findAll() {
        TypedQuery<AgendarEntity> query = em.createQuery("SELECT a FROM Agendar a", AgendarEntity.class);
        return query.getResultList();
    }

    public void close() {
        em.close();
    }

    public static void closeFactory() {
        emf.close();
    }
}
