package br.com.redesprou.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.redesprou.dao.ClienteDAO;
import br.com.redesprou.domain.Cliente;
import br.com.redesprou.util.JSFUtil;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean {
	private Cliente cliente;
	private ArrayList<Cliente> listaDeClientes;
	private ArrayList<Cliente> listaFiltrada;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}

	public void setListaDeClientes(ArrayList<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}

	public ArrayList<Cliente> getListaFiltrada() {
		return listaFiltrada;
	}

	public void setListaFiltrada(ArrayList<Cliente> listaFiltrada) {
		this.listaFiltrada = listaFiltrada;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			ClienteDAO dao = new ClienteDAO();
			listaDeClientes = dao.listar();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void prepararNovo() {
		cliente = new Cliente();
	}

	public void novo() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.salvar(cliente);
			JSFUtil.adicionarMensagemSucesso("Cliente Adicionado Com Sucesso");

			listaDeClientes = dao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.excluir(cliente);

			listaDeClientes = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Cliente Excluido com Sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.editar(cliente);

			listaDeClientes = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}
