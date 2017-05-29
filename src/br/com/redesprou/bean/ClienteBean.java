package br.com.redesprou.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.redesprou.dao.ClienteDAO;
import br.com.redesprou.domain.Cliente;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean {
	private Cliente cliente;
	private ListDataModel<Cliente> listaDeClientes;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ListDataModel<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}

	public void setListaDeClientes(ListDataModel<Cliente> listaDeClientes) {
		this.listaDeClientes = listaDeClientes;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			ClienteDAO dao = new ClienteDAO();
			ArrayList<Cliente> lista = dao.listar();
			listaDeClientes = new ListDataModel<Cliente>(lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void prepararNovo(){
		cliente = new Cliente();
	}
	
	public void novo() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.salvar(cliente);
			
			ArrayList<Cliente> lista = dao.listar();
			listaDeClientes = new ListDataModel<Cliente>(lista);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
