package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PessoaDAO {

    @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void create(Pessoa pessoa) {
        em.persist(pessoa);
    }

    public List<Pessoa> buscarTodos() {
        return em.createNamedQuery("Pessoa.buscarTodos", Pessoa.class)
                .getResultList();
    }

    public Pessoa buscarPorLoginESenha(String login, String senha) {
        try {
            return em.createNamedQuery("Pessoa.buscarPorLoginESenha", Pessoa.class)
                    .setParameter("login", login)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
