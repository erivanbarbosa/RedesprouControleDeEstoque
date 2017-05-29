package br.com.redesprou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.redesprou.domain.Cliente;
import br.com.redesprou.factory.ConexaoFactory;

public class ClienteDAO {
	public void salvar(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cliente ");
		sql.append("(razao_social, cnpj) ");
		sql.append("VALUES (?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cliente.getRazaoSocial());
		comando.setString(2, cliente.getCnpj());

		comando.executeUpdate();
	}

	public void excluir(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM cliente ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, cliente.getCodigo());

		comando.executeUpdate();
	}

	public void editar(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE cliente ");
		sql.append("SET razao_social = ?, cnpj = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, cliente.getRazaoSocial());
		comando.setString(2, cliente.getCnpj());
		comando.setLong(3, cliente.getCodigo());

		comando.executeUpdate();
	}

	public Cliente buscarPorCodigo(Cliente cliente) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj ");
		sql.append("FROM cliente ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, cliente.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Cliente retorno = null;

		if (resultado.next()) {
			retorno = new Cliente();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setRazaoSocial(resultado.getString("razao_social"));
			retorno.setCnpj(resultado.getString("cnpj"));
		}

		return retorno;
	}

	public ArrayList<Cliente> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj ");
		sql.append("FROM cliente ");
		sql.append("ORDER BY codigo ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();

		ArrayList<Cliente> lista = new ArrayList<Cliente>();

		while (resultado.next()) {
			Cliente c1 = new Cliente();
			c1.setCodigo(resultado.getLong("codigo"));
			c1.setRazaoSocial(resultado.getString("razao_social"));
			c1.setCnpj(resultado.getString("cnpj"));

			lista.add(c1);
		}

		return lista;
	}

	public ArrayList<Cliente> listarPorDescricao(Cliente cliente)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj ");
		sql.append("FROM cliente ");
		sql.append("WHERE razao_social LIKE ? ");
		sql.append("ORDER BY codigo ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + cliente.getRazaoSocial() + "%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Cliente> lista = new ArrayList<Cliente>();

		while (resultado.next()) {
			Cliente c1 = new Cliente();
			c1.setCodigo(resultado.getLong("codigo"));
			c1.setRazaoSocial(resultado.getString("razao_social"));
			c1.setCnpj(resultado.getString("cnpj"));

			lista.add(c1);
		}

		return lista;

	}
}
