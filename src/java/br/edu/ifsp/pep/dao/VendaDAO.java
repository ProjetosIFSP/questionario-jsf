package br.edu.ifsp.pep.dao;

import java.util.List;

import br.edu.ifsp.pep.dto.VendaDTO;
import br.edu.ifsp.pep.model.Venda;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class VendaDAO {
  @PersistenceContext(unitName = "conexaoPU")
    private EntityManager em;

    public void create(Venda venda) {
        em.persist(venda);
    }

    public List<Venda> findAll() {
        return em.createNamedQuery("Pessoa.findAll", Venda.class)
                .getResultList();
    }

    public List<Integer> findAnos() {
        return em.createQuery("SELECT DISTINCT " 
         + "FUNCTION('YEAR', v.dataVenda) AS ano " 
         + "FROM Venda v ORDER BY 1", int.class)
                .getResultList();
    }

    public List<VendaDTO> findByAno(int ano) {
        return em.createQuery("SELECT NEW br.edu.ifsp.pep.dto.VendaDTO(" 
         + "FUNCTION('MONTH', v.dataVenda), "
         + "FUNCTION('YEAR', v.dataVenda), "
         + "SUM(v.valor)) " 
         + "FROM Venda v " 
         + "WHERE FUNCTION('YEAR', v.dataVenda) = :ano " 
         + "GROUP BY FUNCTION('MONTH', v.dataVenda) " 
         + "ORDER BY 1",
         VendaDTO.class)
                .setParameter("ano", ano)
                .getResultList();
    }
}
