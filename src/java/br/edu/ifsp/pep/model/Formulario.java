package br.edu.ifsp.pep.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "formulario")
@NamedQueries({
    @NamedQuery(name = "Formulario.findAll", query = "SELECT f FROM Formulario f"),
    @NamedQuery(name = "Formulario.findByUsuario", query = "SELECT f FROM Formulario f WHERE f.usuario = :usuario"),
})
public class Formulario implements java.io.Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "inicio")
  private LocalDate inicio;

  @Column(name = "entrega")
  private LocalDate entrega;

  @Column(name = "titulo", length = 255, nullable = false)
  private String titulo;

  @Column(name = "descricao", length = 1000)
  private String descricao;

  @ManyToOne(optional = false)
  @JoinColumn(name = "usuario", referencedColumnName = "id")
  private Usuario usuario;

  @OneToMany(mappedBy = "formulario")
  private List<Questao> questoes;

  public Formulario() {
  }

  public Formulario(LocalDate inicio, LocalDate entrega, String titulo, String descricao, Usuario usuario) {
    this.inicio = inicio;
    this.entrega = entrega;
    this.titulo = titulo;
    this.descricao = descricao;
    this.usuario = usuario;
  }

  public int getId() {
    return id;
  }

  public LocalDate getInicio() {
    return inicio;
  }

  public LocalDate getEntrega() {
    return entrega;
  }

  public String getInicioFormatted() {
    return inicio.getDayOfMonth() + "/" + String.format("%02d", inicio.getMonthValue()) + "/" + inicio.getYear();
  }

  public String getEntregaFormatted() {
    return entrega.getDayOfMonth() + "/" + String.format("%02d", inicio.getMonthValue()) + "/" + entrega.getYear();
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setInicio(LocalDate inicio) {
    this.inicio = inicio;
  }

  public void setEntrega(LocalDate entrega) {
    this.entrega = entrega;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public List<Questao> getQuestoes() {
    return questoes;
  }

  public void setQuestoes(List<Questao> questoes) {
    this.questoes = questoes;
  }

  @Override
  public String toString() {
    return "Formulario [id=" + id + ", inicio=" + inicio + ", entrega=" + entrega + ", titulo=" + titulo
        + ", descricao="
        + descricao + ", usuario=" + usuario + "]";
  }
}
