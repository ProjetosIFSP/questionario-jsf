package br.edu.ifsp.pep.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

import br.edu.ifsp.pep.model.Formulario;
import br.edu.ifsp.pep.model.Questao;
import br.edu.ifsp.pep.util.Utilities;
import br.edu.ifsp.pep.dao.FormularioDAO;

@Named
@ViewScoped
public class FormularioController implements java.io.Serializable {
  @Inject
  private FormularioDAO dao;

  @Inject
  private UsuarioController usuarioController;

  private Formulario formulario = new Formulario();

  private Formulario formularioDelete = new Formulario();

  private List<Formulario> formularios;

  @PostConstruct
  public void init() {
    setFormularios();
    unsetFormulario();
  }

  public Formulario getFormulario() {
    return formulario;
  }

  public void setFormulario(Formulario formulario) {
    this.formulario = formulario;
  }

  public Formulario getFormularioDelete() {
    return formularioDelete;
  }

  public void setFormularioDelete(Formulario formularioDelete) {
    this.formularioDelete = formularioDelete;
  }

  public List<Formulario> getFormularios() {
    return formularios;
  }

  public void setFormularios(List<Formulario> formularios) {
    this.formularios = formularios;
  }

  public void unsetFormulario() {
    this.formulario = null;
  }

  public void setFormularios() {
    this.formularios = dao.findByUsuario(usuarioController.getUsuarioLogado());
  }

  public List<Formulario> getFormulariosAtivos() {
    return formularios.stream()
        .filter(f -> Utilities.isAfter(f.getEntrega(), java.time.LocalDate.now())).toList();
  }

  public List<Formulario> getFormulariosInativos() {
    return formularios.stream()
        .filter(f -> Utilities.isBefore(f.getEntrega(), java.time.LocalDate.now())).toList();
  }

  public void newFormulario() {
    formulario = new Formulario();
    formulario.setUsuario(usuarioController.getUsuarioLogado());
    formulario.setTitulo("");
    formulario.setDescricao("");
    formulario.setQuestoes(new java.util.ArrayList<>());
    formulario.setInicio(java.time.LocalDate.now());
    formulario.setEntrega(java.time.LocalDate.now());
  }

  public void addQuestion() {
    if (formulario == null) {
      newFormulario();
    }

    List<Questao> questoes = formulario.getQuestoes();

    if (questoes == null) {
      questoes = new java.util.ArrayList<>();
    }

    questoes.add(new Questao());

    formulario.setQuestoes(questoes);
  }

  public void create() {
    dao.create(formulario);
    setFormularios();
  }

  public void delete(Formulario formulario) {
    try {
      dao.delete(formulario);
    } catch (Exception e) {
      e.printStackTrace();
    }

    setFormularios();
  }

  public void update() {
    dao.update(formulario);
    setFormularios();
  }
}
