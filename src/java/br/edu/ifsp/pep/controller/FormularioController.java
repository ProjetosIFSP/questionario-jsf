package br.edu.ifsp.pep.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import br.edu.ifsp.pep.model.Formulario;

@Named
@SessionScoped
public class FormularioController implements java.io.Serializable {
  private Formulario formulario;

  public Formulario getFormulario() {
    return formulario;
  }

  public void setFormulario(Formulario formulario) {
    this.formulario = formulario;
  }
}
