package br.com.redesprou.teste;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import br.com.redesprou.dao.ProdutoDAO;
import br.com.redesprou.domain.Fornecedor;
import br.com.redesprou.domain.Produto;

public class ProdutoDAOTest {

	@Test
	public void salvar() throws SQLException {
		Produto p = new Produto();
		Fornecedor f = new Fornecedor();
		f.setCodigo( 1L );
		
		p.setDescricao("novalgina 10 comprimidos");
		p.setPreco(2.45);
		p.setQuantidade(13L);
		p.setFornecedor(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}

}
