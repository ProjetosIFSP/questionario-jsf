package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Formulario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class FormularioDAO {
  @PersistenceContext(unitName = "conexaoPU")
  private EntityManager em;

  public void create(Formulario usuario) {
    em.persist(usuario);
  }

  public Formulario findAll(String token) {
    return em.createNamedQuery("Formulario.findAll", Formulario.class)
        .setParameter("token", token)
        .getSingleResult();
  }
}
