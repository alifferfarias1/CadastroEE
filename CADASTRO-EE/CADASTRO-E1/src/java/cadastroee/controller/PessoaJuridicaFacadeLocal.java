/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */package cadastroee.controller;

import cadastroee.model.PessoaJuridica;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class PessoaJuridicaService implements PessoaJuridicaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(PessoaJuridica pessoaJuridica) {
        JPAUtil.persistEntity(em, pessoaJuridica);
    }

    @Override
    public void edit(PessoaJuridica pessoaJuridica) {
        JPAUtil.mergeEntity(em, pessoaJuridica);
    }

    @Override
    public void remove(PessoaJuridica pessoaJuridica) {
        JPAUtil.removeEntity(em, pessoaJuridica);
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
