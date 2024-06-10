package br.edu.ifsp.pep.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questao")
public class Questao implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @OneToOne(optional = false)
  @JoinColumn(name = "formulario", referencedColumnName = "id", nullable = false)
  private Formulario formulario;

  @Column(name = "enunciado", length = 1000, nullable = false)
  private String enunciado;

  @OneToMany(mappedBy = "questao")
  private List<Alternativa> alternativas;

  public Questao() {
  }

  public Questao(Formulario formulario, String enunciado) {
    this.formulario = formulario;
    this.enunciado = enunciado;
  }

  public int getId() {
    return id;
  }

  public Formulario getFormulario() {
    return formulario;
  }

  public String getEnunciado() {
    return enunciado;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFormulario(Formulario formulario) {
    this.formulario = formulario;
  }

  public void setEnunciado(String enunciado) {
    this.enunciado = enunciado;
  }

  public List<Alternativa> getAlternativas() {
    return alternativas;
  }

  public void setAlternativas(List<Alternativa> alternativas) {
    this.alternativas = alternativas;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + this.id;
    hash = 59 * hash + Objects.hashCode(this.formulario);
    hash = 59 * hash + Objects.hashCode(this.enunciado);
    return hash;
  }
}
