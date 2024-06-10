package br.edu.ifsp.pep.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "alternativa")
public class Alternativa implements java.io.Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @JoinColumn(name = "questao", referencedColumnName = "id", nullable = false)
  private Questao questao;

  @Column(name = "descricao", length = 1000, nullable = false)
  private String descricao;

  public Alternativa() {
  }

  public Alternativa(Questao questao, String descricao) {
    this.questao = questao;
    this.descricao = descricao;
  }

  public int getId() {
    return id;
  }

  public Questao getQuestao() {
    return questao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setQuestao(Questao questao) {
    this.questao = questao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}
