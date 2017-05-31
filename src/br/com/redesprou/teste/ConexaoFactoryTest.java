package br.com.redesprou.teste;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import br.com.redesprou.factory.ConexaoFactory;

public class ConexaoFactoryTest {

	@Test
	public void testConectar() throws SQLException {
		Connection conexao = ConexaoFactory.conectar();
		
		assertTrue(conexao.isValid(10));
	}

}
