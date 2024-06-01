package cadastroee.controller;

import cadastroee.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PessoaFacade implements PessoaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    public void create(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public void edit(Pessoa pessoa) {
        em.merge(pessoa);
    }

    public void remove(Pessoa pessoa) {
        em.remove(em.merge(pessoa));
    }

    public Pessoa find(Object id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public List<Pessoa> findRange(int[] range) {
        javax.persistence.Query q = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.Query q = em.createQuery("SELECT COUNT(p) FROM Pessoa p");
        return ((Long) q.getSingleResult()).intValue();
    }
}
