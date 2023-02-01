package br.ifpr.execucao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import br.ifpr.dao.ProdutoDAO;
import br.ifpr.modelo.Produto;

public class ProdutoExcluir {
	public static void main(String[] args) throws IOException, NumberFormatException, SQLException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Informe o ID do produto a ser excluído");
		//lê o id do produto
		String id = reader.readLine();
		Integer idInt = Integer.parseInt(id);
		
		//verifica se existe
		ProdutoDAO prodDAO = new ProdutoDAO();
		Produto prod = prodDAO.buscar(idInt);
		if(prod != null) {
			//produto existe
			prodDAO.excluir(idInt);
			System.out.println("Produto "+idInt+" excluído com sucesso.");
		}else {
			System.out.println("O ID "+id+" não pertence a nenhum produto");
		}
	}
	
	

}
