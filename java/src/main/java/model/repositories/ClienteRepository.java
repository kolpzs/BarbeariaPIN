package model.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.Cliente;

public class ClienteRepository implements BasicCrud {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoBarbearia");
    private EntityManager em;

    public ClienteRepository() {
        this.em = emf.createEntityManager();
    }

    @Override
    public Cliente create(Object object) {
        Cliente cliente = (Cliente) object;
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        return cliente;
    }

    @Override
    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    @Override
    public Cliente updateById(Object object) {
        Cliente cliente = (Cliente) object;
        em.getTransaction().begin();
        Cliente updatedCliente = em.merge(cliente);
        em.getTransaction().commit();
        return updatedCliente;
    }

    @Override
    public void delete(Long id) {
        Cliente cliente = findById(id);
        if (cliente != null) {
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Cliente> findAll() {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        return query.getResultList();
    }
}
