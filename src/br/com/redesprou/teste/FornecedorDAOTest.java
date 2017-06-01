package br.com.redesprou.teste;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.redesprou.dao.FornecedorDAO;
import br.com.redesprou.domain.Fornecedor;

public class FornecedorDAOTest {

	@Test
	public void test() throws SQLException{
		ArrayList<Fornecedor> lista;
		FornecedorDAO dao = new FornecedorDAO();
		
		lista = dao.listar();
		
		for( Fornecedor f : lista )
		{
			System.out.println("Codigo: " + f.getCodigo() );
			
			System.out.println();
		}
		
	}

}
