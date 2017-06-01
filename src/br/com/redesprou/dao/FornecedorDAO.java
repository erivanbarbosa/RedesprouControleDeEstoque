package br.com.redesprou.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.redesprou.domain.Fornecedor;
import br.com.redesprou.factory.ConexaoFactory;

public class FornecedorDAO {
	public ArrayList<Fornecedor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, razao_social, cnpj ");
		sql.append("FROM fornecedor ");
		sql.append("ORDER BY codigo ASC ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fornecedor> itens = new ArrayList<Fornecedor>();

		while (resultado.next()) {
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getLong("codigo"));
			f.setRazaoSocial(resultado.getString("razao_social"));
			f.setCnpj(resultado.getString("cnpj"));

			itens.add(f);
		}

		return itens;
	}
}
