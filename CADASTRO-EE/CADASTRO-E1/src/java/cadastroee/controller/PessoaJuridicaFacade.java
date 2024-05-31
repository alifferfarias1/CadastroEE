package cadastroee.controller;

import cadastroee.model.PessoaJuridica;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PessoaJuridicaFacade implements PessoaJuridicaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(PessoaJuridica pessoaJuridica) {
        em.persist(pessoaJuridica);
    }

    @Override
    public void edit(PessoaJuridica pessoaJuridica) {
        em.merge(pessoaJuridica);
    }

    @Override
    public void remove(PessoaJuridica pessoaJuridica) {
        em.remove(em.merge(pessoaJuridica));
    }

    @Override
    public PessoaJuridica find(Object id) {
        return em.find(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> findAll() {
        return em.createQuery("SELECT p FROM PessoaJuridica p", PessoaJuridica.class).getResultList();
    }

    @Override
    public List<PessoaJuridica> findRange(int[] range) {
        javax.persistence.Query q = em.createQuery("SELECT p FROM PessoaJuridica p", PessoaJuridica.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.Query q = em.createQuery("SELECT COUNT(p) FROM PessoaJuridica p");
        return ((Long) q.getSingleResult()).intValue();
    }
}
