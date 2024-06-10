package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.dao.UsuarioDAO;
import br.edu.ifsp.pep.model.Usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class UsuarioController implements java.io.Serializable {
  @Inject
  private UsuarioDAO dao;

  @Inject
  private ThemeController themeController;

  private Usuario usuarioLogado;
  private Usuario usuario = new Usuario();

  public String login() {
    if (usuario != null) {
      this.usuarioLogado = dao.findUsuario(usuario.getUsername(), usuario.getPass());
      if (this.usuarioLogado != null) {
        this.usuario = new Usuario();
        themeController.setDark(true);
        return "/index";
      }
    }
    return null;
  }

  public void logout() {
    this.usuarioLogado = null;
    this.usuario = new Usuario();
  }

  public Usuario getUsuarioLogado() {
    return usuarioLogado;
  }

  public void setUsuarioLogado(Usuario usuarioLogado) {
    this.usuarioLogado = usuarioLogado;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }
}
