package br.edu.ifsp.pep.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa")
@NamedQueries({
    @NamedQuery(name = "Pessoa.buscarPorLoginESenha", query = "FROM Pessoa p WHERE p.login = :login AND p.senha = :senha")
})
public class Pessoa implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "login", length = 20, nullable = false, unique = true)
  private String login;

  @Column(name = "senha", length = 20, nullable = false)
  private String senha;

  @Column(name = "nome", length = 50, nullable = false)
  private String nome;

  @ManyToOne(optional = false)
  @JoinColumn(name = "tipo", nullable = false)
  private TipoPessoa tipo;

  public Pessoa() {
  }

  public Pessoa(String login, String senha, String nome) {
    this.login = login;
    this.senha = senha;
    this.nome = nome;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public TipoPessoa getTipo() {
    return tipo;
  }

  public void setTipo(TipoPessoa tipo) {
    this.tipo = tipo;
  }


  @Override
  public String toString() {
    return "Pessoa{" + "id=" + id + ", login=" + login + ", senha=" + senha + ", nome=" + nome + ", tipo=" + tipo + '}';
  }

  @Override
  public int hashCode() {
    final int hash = 7;
    int result = 83;
    result = hash * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Pessoa other = (Pessoa) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }
}
