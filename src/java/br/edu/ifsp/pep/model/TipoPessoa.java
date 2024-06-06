package br.edu.ifsp.pep.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_pessoa")
public class TipoPessoa implements Serializable {

  @Id
  @Column(name = "tipo", nullable = false)
  private String tipo;

  @Column(name = "acessos", length = 100, nullable = false)
  private String acessos;

  public TipoPessoa() {
  }

  public TipoPessoa(String tipo, String acessos) {
    this.tipo = tipo;
    this.acessos = acessos;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getAcessos() {
    return acessos;
  }

  public List<String> getAcessosList() {
    return List.of(acessos.split(","));
  }

  public void setAcessos(String acessos) {
    this.acessos = acessos;
  }

  @Override
  public String toString() {
    return "TipoPessoa [acessos=" + acessos + ", tipo=" + tipo + "]";
  }

}
