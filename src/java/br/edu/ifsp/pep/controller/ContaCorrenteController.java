package br.edu.ifsp.pep.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.StreamedContent;

import br.edu.ifsp.pep.model.ContaCorrente;
import br.edu.ifsp.pep.util.Report;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@RequestScoped
public class ContaCorrenteController {
  public StreamedContent gerarRelatorioContaCorrente() {

    // Obter a lista de contas correntes
    List<ContaCorrente> lista = obterContaCorrente();
    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

    InputStream is = getClass().getResourceAsStream("/br/edu/ifsp/pep/reports/relatorio-contas.jasper");

    return Report.gerar(is, null, dataSource);

  }

  public List<ContaCorrente> obterContaCorrente() {
    List<ContaCorrente> contas = new ArrayList<>();
    contas.add(new ContaCorrente(1, 1000.0));
    contas.add(new ContaCorrente(2, 2000.0));
    contas.add(new ContaCorrente(3, 3000.0));
    contas.add(new ContaCorrente(4, 4000.0));
    contas.add(new ContaCorrente(5, 5000.0));
    contas.add(new ContaCorrente(6, 6000.0));
    contas.add(new ContaCorrente(7, 7000.0));

    return contas;
  }
}
