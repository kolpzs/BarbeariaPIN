package model.repositories;

import model.entities.FuncionarioEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class FuncionarioRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public FuncionarioRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public FuncionarioEntity create(Object object) {
        FuncionarioEntity funcionarioEntity = (FuncionarioEntity) object;
        em.getTransaction().begin();
        em.persist(funcionarioEntity);
        em.getTransaction().commit();
        return funcionarioEntity;
    }

    @Override
    public FuncionarioEntity findById(Long id) {
        return em.find(FuncionarioEntity.class, id);
    }

    @Override
    public FuncionarioEntity updateById(Object object) {
        FuncionarioEntity funcionarioEntity = (FuncionarioEntity) object;
        em.getTransaction().begin();
        FuncionarioEntity updatedFuncionarioEntity = em.merge(funcionarioEntity);
        em.getTransaction().commit();
        return updatedFuncionarioEntity;
    }

    @Override
    public void delete(Long id) {
        FuncionarioEntity funcionarioEntity = findById(id);
        if (funcionarioEntity != null) {
            em.getTransaction().begin();
            em.remove(funcionarioEntity);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<FuncionarioEntity> findAll() {
        TypedQuery<FuncionarioEntity> query = em.createQuery("SELECT f FROM Funcionario f", FuncionarioEntity.class);
        return query.getResultList();
    }

    public FuncionarioEntity findByNome(String nome) {
        try {
            TypedQuery<FuncionarioEntity> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.nome = :nome", FuncionarioEntity.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void close() {
        em.close();
    }

    public static void closeFactory() {
        emf.close();
    }
}
