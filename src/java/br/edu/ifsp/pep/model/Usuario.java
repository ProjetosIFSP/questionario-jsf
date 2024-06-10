package br.edu.ifsp.pep.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findUsuario", query = "FROM Usuario u WHERE u.username = :username AND u.pass = :pass")
})
public class Usuario implements java.io.Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "nome", length = 255, nullable = false)
  private String nome;

  @Column(name = "username", length = 255, unique = true, nullable = false)
  private String username;

  @Column(name = "pass", length = 255, nullable = false)
  private String pass;

  @OneToMany(mappedBy = "usuario")
  private List<Formulario> formularios;

  public Usuario() {
  }

  public Usuario(String nome, String username, String pass) {
    this.nome = nome;
    this.username = username;
    this.pass = pass;
  }

  public List<Formulario> getFormularios() {
    return formularios;
  }

  public void setFormularios(List<Formulario> formularios) {
    this.formularios = formularios;
  }

  public List<Formulario> getFormulariosAtivos() {
    List<Formulario> formulariosAtivos;
    if (formularios != null) {
      formulariosAtivos = formularios;
    } else {
      return null;
    }
    formulariosAtivos.removeIf(formulario -> formulario.getEntrega().isBefore(java.time.LocalDate.now()));
    return formulariosAtivos;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getUsername() {
    return username;
  }

  public String getPass() {
    return pass;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  @Override
  public String toString() {
    return "Usuario [id=" + id + ", nome=" + nome + ", username=" + username + ", pass=" + pass + "]";
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
    Usuario other = (Usuario) obj;
    if (id != other.id) {
      return false;
    }

    if (!Objects.equals(username, other.username)) {
      return false;
    }

    return true;
  }
}
