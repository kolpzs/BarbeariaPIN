package model.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.DisponibilidadeEntity;

public class DisponibilidadeRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public DisponibilidadeRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public DisponibilidadeEntity create(Object object) {
        DisponibilidadeEntity disponibilidadeEntity = (DisponibilidadeEntity) object;
        em.getTransaction().begin();
        em.persist(disponibilidadeEntity);
        em.getTransaction().commit();
        return disponibilidadeEntity;
    }

    @Override
    public DisponibilidadeEntity findById(Long id) {
        return em.find(DisponibilidadeEntity.class, id);
    }

    @Override
    public DisponibilidadeEntity updateById(Object object) {
        DisponibilidadeEntity disponibilidadeEntity = (DisponibilidadeEntity) object;
        em.getTransaction().begin();
        DisponibilidadeEntity updatedDisponibilidadeEntity = em.merge(disponibilidadeEntity);
        em.getTransaction().commit();
        return updatedDisponibilidadeEntity;
    }

    @Override
    public void delete(Long id) {
        DisponibilidadeEntity disponibilidadeEntity = findById(id);
        if (disponibilidadeEntity != null) {
            em.getTransaction().begin();
            em.remove(disponibilidadeEntity);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<DisponibilidadeEntity> findAll() {
        TypedQuery<DisponibilidadeEntity> query = em.createQuery("SELECT d FROM Disponibilidade d", DisponibilidadeEntity.class);
        return query.getResultList();
    }
}
