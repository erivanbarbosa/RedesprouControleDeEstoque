package br.com.redesprou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.redesprou.domain.Cliente;
import br.com.redesprou.factory.ConexaoFactory;

public class ClienteDAO {
	public void salvar(Cliente cliente) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO cliente ");
		sql.append("(razao_social, cnpj) ");
		sql.append("VALUES (?, ?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement( sql.toString() );
		comando.setString(1, cliente.getRazaoSocial());
		comando.setString(2, cliente.getCnpj());
		
		comando.executeUpdate();
	}
	
	public void excluir(Cliente cliente) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM cliente ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement( sql.toString() );
		comando.setLong(1, cliente.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void editar(Cliente cliente ) throws SQLException
	{
		StringBuilder sql = new StringBuilder();
		sql.append( "UPDATE cliente ");
		sql.append("SET razao_social = ?, cnpj = ? ");
		sql.append("WHERE codigo = ? ");
		
Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement( sql.toString() );
		comando.setString(1, cliente.getRazaoSocial());
		comando.setString(2, cliente.getCnpj());
		comando.setLong(3, cliente.getCodigo());
		
		comando.executeUpdate();
	}
	
	public static void main(String[] args )
	{
		/*****
		Cliente cliente = new Cliente();
		cliente.setRazaoSocial("Globo SA");
		cliente.setCnpj("11111111111");
		
		Cliente cliente2 = new Cliente();
		cliente2.setRazaoSocial("Arcom Distribuidora");
		cliente2.setCnpj("1232343");
		
		ClienteDAO cDAO = new ClienteDAO();
		try {
			cDAO.salvar(cliente);
			cDAO.salvar(cliente2);
			System.out.println("Deu certo");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deu errado");
		}
	}*/
	
		/*
		Cliente c1 = new Cliente();
		c1.setCodigo( 2L);
		
		ClienteDAO cDAO = new ClienteDAO();
		try {
			cDAO.excluir(c1);
			System.out.println("Deu certo");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deu errado");
		}*/
		
		Cliente c1 = new Cliente();
		c1.setCodigo(3L);
		c1.setRazaoSocial("Arcon Distribuidora");
		c1.setCnpj("123234355");
		
		ClienteDAO cDAO = new ClienteDAO();
		try {
			cDAO.editar(c1);
			System.out.println("Deu certo");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Deu errado");
		}
} 
}
