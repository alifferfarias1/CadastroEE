package cadastroee.controller;

import cadastroee.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProdutoService implements ProdutoFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(Produto produto) {
        em.persist(produto);
    }

    @Override
    public void edit(Produto produto) {
        em.merge(produto);
    }

    @Override
    public void remove(Produto produto) {
        em.remove(em.merge(produto));
    }

    @Override
    public Produto find(Object id) {
        return em.find(Produto.class, id);
    }

    @Override
    public List<Produto> findAll() {
        return em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    @Override
    public List<Produto> findRange(int[] range) {
        return em.createQuery("SELECT p FROM Produto p", Produto.class)
                 .setFirstResult(range[0])
                 .setMaxResults(range[1] - range[0])
                 .getResultList();
    }

    @Override
    public int count() {
        return ((Long) em.createQuery("SELECT COUNT(p) FROM Produto p")
                        .getSingleResult())
                        .intValue();
    }
}

