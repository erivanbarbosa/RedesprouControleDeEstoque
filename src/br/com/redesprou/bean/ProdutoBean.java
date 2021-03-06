package br.com.redesprou.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.redesprou.dao.FornecedorDAO;
import br.com.redesprou.dao.ProdutoDAO;
import br.com.redesprou.domain.Fornecedor;
import br.com.redesprou.domain.Produto;
import br.com.redesprou.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {
	private ArrayList<Produto> listaDeProdutos;
	private ArrayList<Produto> listaFiltrada;
	private Produto produto;
	private ArrayList<Fornecedor> comboFornecedores;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<Fornecedor> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedor> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public ArrayList<Produto> getListaFiltrada() {
		return listaFiltrada;
	}

	public void setListaFiltrada(ArrayList<Produto> listaFiltrada) {
		this.listaFiltrada = listaFiltrada;
	}

	public ArrayList<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(ArrayList<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public void prepararListagem() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listaDeProdutos = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		try {
			produto = new Produto();

			FornecedorDAO dao = new FornecedorDAO();

			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void novo() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.salvar(produto);
			JSFUtil.adicionarMensagemSucesso("Produto Gravado com Sucesso!");

			listaDeProdutos = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			
			dao.excluir(produto);
			
			listaDeProdutos = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produo Excluido com Sucesso");
		} catch(SQLException e ) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro( e.getMessage() );
		}
	}
	
	public void prepararEditar() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			
			comboFornecedores = dao.listar();
		} catch(SQLException e )
		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void editar(){
		try {
			ProdutoDAO dao = new ProdutoDAO();
			
			dao.editar(produto);
			
			listaDeProdutos = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso( "Produto Editado Com Sucesso!");
		} catch(SQLException e ){
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro( e.getMessage() );
		}
	}
}
