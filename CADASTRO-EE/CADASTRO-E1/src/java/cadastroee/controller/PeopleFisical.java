package cadastroee.controller;

import cadastroee.model.PessoaFisica;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PessoaFisicaService implements PessoaFisicaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(PessoaFisica pessoaFisica) {
        em.persist(pessoaFisica);
    }

    @Override
    public void edit(PessoaFisica pessoaFisica) {
        em.merge(pessoaFisica);
    }

    @Override
    public void remove(PessoaFisica pessoaFisica) {
        em.remove(em.merge(pessoaFisica));
    }

    @Override
    public PessoaFisica find(Object id) {
        return em.find(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> findAll() {
        return em.createQuery("SELECT p FROM PessoaFisica p", PessoaFisica.class).getResultList();
    }

    @Override
    public List<PessoaFisica> findRange(int[] range) {
        javax.persistence.Query q = em.createQuery("SELECT p FROM PessoaFisica p", PessoaFisica.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.Query q = em.createQuery("SELECT COUNT(p) FROM PessoaFisica p");
        return ((Long) q.getSingleResult()).intValue();
    }
}
