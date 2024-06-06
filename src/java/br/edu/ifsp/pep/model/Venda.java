package br.edu.ifsp.pep.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "venda")
public class Venda {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "data_venda", nullable = false)
  private Date dataVenda;

  @Column(name = "valor", nullable = false)
  private double valor;

  public Venda() {
  }

  public Venda(Date dataVenda, double valor) {
    this.dataVenda = dataVenda;
    this.valor = valor;
  }

  public Venda(int id, Date dataVenda, double valor) {
    this.id = id;
    this.dataVenda = dataVenda;
    this.valor = valor;
  }

  public Venda(int mes, int ano, double valor) {
    this.dataVenda = Date.valueOf(LocalDate.of(ano, mes, 1));
    this.valor = valor;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getDataVenda() {
    return dataVenda;
  }

  public void setDataVenda(Date dataVenda) {
    this.dataVenda = dataVenda;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
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
    Venda other = (Venda) obj;
    
    if (id != other.id) {
      return false;
    }

    if (dataVenda == null) {
      if (other.dataVenda != null) {
        return false;
      }
    } else if (dataVenda.toLocalDate().getMonthValue() == other.dataVenda.toLocalDate().getMonthValue()
        && dataVenda.toLocalDate().getYear() == other.dataVenda.toLocalDate().getYear()) {
      return false;
    }

    return true;
  }
}
