package model.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.Servico;

public class ServicoRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public ServicoRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public Servico create(Object object) {
        Servico servico = (Servico) object;
        try {
            em.getTransaction().begin();
            em.persist(servico);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return servico;
    }

    @Override
    public Servico findById(Long id) {
        return em.find(Servico.class, id);
    }

    @Override
    public Servico updateById(Object object) {
        Servico servico = (Servico) object;
        try {
            em.getTransaction().begin();
            em.merge(servico);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return servico;
    }

    @Override
    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            Servico servico = em.find(Servico.class, id);
            if (servico != null) {
                em.remove(servico);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Servico> findAll() {
        TypedQuery<Servico> query = em.createQuery("SELECT s FROM Servico s", Servico.class);
        return query.getResultList();
    }
}
