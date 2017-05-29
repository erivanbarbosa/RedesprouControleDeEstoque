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
	
	public Cliente buscarPorCodigo(Cliente cliente) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj ");
		sql.append("FROM cliente ");
		sql.append( "WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, cliente.getCodigo());

		ResultSet resultado = comando.executeQuery();
		
		Cliente retorno = null;
		
		if( resultado.next() ){
			retorno = new Cliente();
			retorno.setCodigo( resultado.getLong("codigo"));
			retorno.setRazaoSocial(resultado.getString("razao_social"));
			retorno.setCnpj(resultado.getString("cnpj"));
		}
		
		return retorno;
	}
	
	public ArrayList<Cliente> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj ");
		sql.append( "FROM cliente " );
		sql.append("ORDER BY codigo ASC ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		while(resultado.next() )
		{
			Cliente c1 = new Cliente();
			c1.setCodigo( resultado.getLong("codigo"));
			c1.setRazaoSocial(resultado.getString("razao_social"));
			c1.setCnpj(resultado.getString("cnpj"));
			
			lista.add(c1);
		}
		
		return lista;
	}
	
	public ArrayList<Cliente> listarPorDescricao(Cliente cliente ) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj " );
		sql.append("FROM cliente ");
		sql.append("WHERE razao_social LIKE ? ");
		sql.append("ORDER BY codigo ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement( sql.toString() );
		comando.setString(1, "%" + cliente.getRazaoSocial() + "%");
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		
		while( resultado.next() )
		{
			Cliente c1 = new Cliente();
			c1.setCodigo(resultado.getLong("codigo"));
			c1.setRazaoSocial(resultado.getString("razao_social"));
			c1.setCnpj(resultado.getString("cnpj"));
			
			lista.add(c1);
		}
		
		return lista;
		
	}
	
	public static void main(String[] args )
	{
		/*
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		c1.setCodigo(1L);
		c2.setCodigo(5L);
		
		ClienteDAO cDAO = new ClienteDAO();
		
		try {
			Cliente r1 = cDAO.buscarPorCodigo(c1);
			Cliente r2 = cDAO.buscarPorCodigo(c2);
			System.out.println("Deu certo");
			
			System.out.println("result 1: " + r1 );
			System.out.println("result 1: " + r2 );

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nao deu certo");
		}*/
		
		/*
		ClienteDAO cDAO = new ClienteDAO();
		try {
			ArrayList<Cliente> lista = cDAO.listar();
			
			for (Cliente c : lista )
			{
				System.out.println(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro");
		}*/
		
		Cliente c1 = new Cliente();
		c1.setRazaoSocial("Arcon");
		
		ClienteDAO cDAO = new ClienteDAO();
		try {
			ArrayList<Cliente> lista = cDAO.listarPorDescricao(c1);
			
			for (Cliente c : lista )
			{
				System.out.println(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro");
		}
	}
}
