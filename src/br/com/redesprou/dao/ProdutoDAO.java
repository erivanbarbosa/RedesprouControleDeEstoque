package br.com.redesprou.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import br.com.redesprou.domain.Fornecedor;
import br.com.redesprou.domain.Produto;
import br.com.redesprou.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produto produto) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, preco, quantidade, fornecedor_codigo) ");
		sql.append("VALUES (?, ?, ?, ?) ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, produto.getDescricao());
		comando.setDouble(2, produto.getPreco());
		comando.setLong(3, produto.getQuantidade());
		comando.setLong(4, produto.getFornecedor().getCodigo());

		comando.executeUpdate();
	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.razao_social, f.cnpj ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fornecedor f ON f.codigo = p.fornecedor_codigo ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produto> itens = new ArrayList<Produto>();

		while (resultado.next()) {
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setRazaoSocial(resultado.getString("f.razao_social"));
			f.setCnpj(resultado.getString("f.cnpj"));

			Produto p = new Produto();
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getLong("p.quantidade"));
			p.setFornecedor(f);

			itens.add(p);
		}

		return itens;
	}

	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, p.getCodigo());

		comando.executeUpdate();
	}

	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append( "UPDATE produto ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedor_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement( sql.toString() );
		
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedor().getCodigo());
		
		comando.executeLargeUpdate();
	}

}
