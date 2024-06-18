package br.edu.ifsp.pep.dao;

import java.util.List;

import br.edu.ifsp.pep.model.Formulario;
import br.edu.ifsp.pep.model.Usuario;
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

  public void update(Formulario usuario) {
    em.merge(usuario);
  }

  public void delete(Formulario usuario) {
    em.remove(em.merge(usuario));
  }

  public Formulario findAll(String token) {
    return em.createNamedQuery("Formulario.findAll", Formulario.class)
        .setParameter("token", token)
        .getSingleResult();
  }

  public List<Formulario> findByUsuario(Usuario usuario) {
    return em.createNamedQuery("Formulario.findByUsuario", Formulario.class)
        .setParameter("usuario", usuario)
        .getResultList();
  }
}
