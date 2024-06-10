package br.edu.ifsp.pep.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class ThemeController implements java.io.Serializable {

  private boolean dark = false;

  public boolean getDark() {
    return dark;
  }

  public void setDark(boolean dark) {
    this.dark = dark;
  }
}
