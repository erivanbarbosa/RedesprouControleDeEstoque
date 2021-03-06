package br.com.redesprou.teste;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import jdk.nashorn.internal.ir.annotations.Ignore;

import org.junit.Test;

import br.com.redesprou.dao.ProdutoDAO;
import br.com.redesprou.domain.Fornecedor;
import br.com.redesprou.domain.Produto;

public class ProdutoDAOTest {

	@Test
	@org.junit.Ignore
	public void salvar() throws SQLException {
		Produto p = new Produto();
		Fornecedor f = new Fornecedor();
		f.setCodigo(1L);

		p.setDescricao("novalgina 10 comprimidos");
		p.setPreco(2.45);
		p.setQuantidade(13L);
		p.setFornecedor(f);

		ProdutoDAO dao = new ProdutoDAO();
		// dao.salvar(p);
	}

	@Test
	@org.junit.Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();

		for (Produto p : lista) {
			System.out.println("Codigo do produto: " + p.getCodigo());
			System.out.println("Descri��o do produto: " + p.getDescricao());
			System.out.println("Pre�o do produto: " + p.getPreco());
			System.out.println("Quantidade do produto: " + p.getQuantidade());
			System.out.println("Codigo do fornecedor "
					+ p.getFornecedor().getCodigo());
			System.out.println("Razao Social do fornecedor: "
					+ p.getFornecedor().getRazaoSocial());
			System.out.println("CNPJ do fornecedor: "
					+ p.getFornecedor().getCnpj());
			System.out.println();
		}
	}

	@Test
	@org.junit.Ignore
	public void excluir() throws SQLException {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCodigo(1L);

		Produto produto = new Produto();
		produto.setCodigo(3L);
		produto.setDescricao("Doralgina 100g");
		produto.setQuantidade(10L);
		produto.setPreco(2.56);
		produto.setFornecedor(fornecedor);

		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(produto);
	}

	@Test
	public void editar() throws SQLException {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCodigo(1L);

		Produto produto = new Produto();
		produto.setCodigo(3L);
		produto.setDescricao("Doralgina 200g");
		produto.setQuantidade(10L);
		produto.setPreco(2.56);
		produto.setFornecedor(fornecedor);

		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(produto);
	}

}
