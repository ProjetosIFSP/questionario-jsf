package br.edu.ifsp.pep.controller;

import java.io.Serializable;
import java.util.List;

import br.edu.ifsp.pep.dao.VendaDAO;
import br.edu.ifsp.pep.model.Chart;
import br.edu.ifsp.pep.model.Venda;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class VendaController implements Serializable {

  @Inject
  private VendaDAO dao;

  private int ano;

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public List<Venda> findAll() {
    return dao.findAll();
  }

  public List<Integer> findAnos() {
    return dao.findAnos();
  }

}
