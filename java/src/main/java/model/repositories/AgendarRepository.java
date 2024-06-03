package model.repositories;

import model.entities.Agendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
    public Agendar create(Object object) {
        Agendar agendar = (Agendar) object;
        em.getTransaction().begin();
        em.persist(agendar);
        em.getTransaction().commit();
        return agendar;
    }

    @Override
    public Agendar findById(Long id) {
        return em.find(Agendar.class, id);
    }

    @Override
    public Agendar updateById(Object object) {
        Agendar agendar = (Agendar) object;
        em.getTransaction().begin();
        Agendar updatedAgendar = em.merge(agendar);
        em.getTransaction().commit();
        return updatedAgendar;
    }

    @Override
    public void delete(Long id) {
        Agendar agendar = findById(id);
        if (agendar != null) {
            em.getTransaction().begin();
            em.remove(agendar);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Agendar> findAll() {
        TypedQuery<Agendar> query = em.createQuery("SELECT a FROM Agendar a", Agendar.class);
        return query.getResultList();
    }

    public void close() {
        em.close();
    }

    public static void closeFactory() {
        emf.close();
    }
}
