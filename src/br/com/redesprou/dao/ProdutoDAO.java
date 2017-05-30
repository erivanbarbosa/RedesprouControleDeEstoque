package br.com.redesprou.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

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
}
