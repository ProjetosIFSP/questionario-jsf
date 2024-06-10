package br.edu.ifsp.pep.dao;

import br.edu.ifsp.pep.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UsuarioDAO {

  @PersistenceContext(unitName = "conexaoPU")
  private EntityManager em;

  public void create(Usuario usuario) {
    em.persist(usuario);
  }

  public List<Usuario> findAll() {
    return em.createNamedQuery("Usuario.findAll", Usuario.class)
        .getResultList();
  }

  public Usuario findUsuario(String username, String pass) {
    try {
      return em.createNamedQuery("Usuario.findUsuario", Usuario.class)
          .setParameter("username", username)
          .setParameter("pass", pass)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

}
