package model.repositories;

import model.entities.Funcionario;

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
    public Funcionario create(Object object) {
        Funcionario funcionario = (Funcionario) object;
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
        return funcionario;
    }

    @Override
    public Funcionario findById(Long id) {
        return em.find(Funcionario.class, id);
    }

    @Override
    public Funcionario updateById(Object object) {
        Funcionario funcionario = (Funcionario) object;
        em.getTransaction().begin();
        Funcionario updatedFuncionario = em.merge(funcionario);
        em.getTransaction().commit();
        return updatedFuncionario;
    }

    @Override
    public void delete(Long id) {
        Funcionario funcionario = findById(id);
        if (funcionario != null) {
            em.getTransaction().begin();
            em.remove(funcionario);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Funcionario> findAll() {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        return query.getResultList();
    }

    public Funcionario findByNome(String nome) {
        try {
            TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.nome = :nome", Funcionario.class);
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
