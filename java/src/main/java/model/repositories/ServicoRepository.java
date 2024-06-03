package model.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.ServicoEntity;

public class ServicoRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public ServicoRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public ServicoEntity create(Object object) {
        ServicoEntity servicoEntity = (ServicoEntity) object;
        try {
            em.getTransaction().begin();
            em.persist(servicoEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return servicoEntity;
    }

    @Override
    public ServicoEntity findById(Long id) {
        return em.find(ServicoEntity.class, id);
    }

    @Override
    public ServicoEntity updateById(Object object) {
        ServicoEntity servicoEntity = (ServicoEntity) object;
        try {
            em.getTransaction().begin();
            em.merge(servicoEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return servicoEntity;
    }

    @Override
    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            ServicoEntity servicoEntity = em.find(ServicoEntity.class, id);
            if (servicoEntity != null) {
                em.remove(servicoEntity);
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
    public List<ServicoEntity> findAll() {
        TypedQuery<ServicoEntity> query = em.createQuery("SELECT s FROM Servico s", ServicoEntity.class);
        return query.getResultList();
    }
}
