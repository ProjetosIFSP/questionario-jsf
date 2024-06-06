package br.edu.ifsp.pep.dto;

public class VendaDTO {
  private int mes;
  private int ano;
  private double valor;

  public VendaDTO(int mes, int ano, double valor) {
    this.mes = mes;
    this.ano = ano;
    this.valor = valor;
  }

  public int getMes() {
    return mes;
  }

  public void setMes(int mes) {
    this.mes = mes;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
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
    VendaDTO other = (VendaDTO) obj;
    if (ano != other.ano) {
      return false;
    }
    if (mes != other.mes) {
      return false;
    }

    
    return true;
  }
}
