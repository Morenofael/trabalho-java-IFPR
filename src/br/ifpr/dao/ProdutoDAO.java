package br.ifpr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ifpr.modelo.Produto;
import br.ifpr.util.Conexao;

public class ProdutoDAO {
	
	public void inserir(Produto prod) throws SQLException {
		String sql = 
		"INSERT INTO produtos (descricao, unidade_medida)" +
		" VALUES (?, ?);";
		
		Connection conn = Conexao.getConexao();
		
		PreparedStatement ps = 
				conn.prepareStatement(sql);
		ps.setString(1, prod.getDescricao());
		ps.setString(2, prod.getUnidadeMedida());
		
		ps.executeUpdate();
		
		conn.close();
	}
	
	public List<Produto> listar() throws SQLException {
		String sql = 
			"SELECT id_produto, descricao, unidade_medida" +
			" FROM produtos";
		
		Connection conn = Conexao.getConexao();
		
		PreparedStatement ps = 
				conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<Produto> lista = new ArrayList<>();
		
		while(rs.next()) {
			Produto prod = new Produto();
			prod.setIdProduto(rs.getInt("id_produto"));
			prod.setDescricao(rs.getString("descricao"));
			prod.setUnidadeMedida(rs.getString("unidade_medida"));
			
			lista.add(prod);
		}
		
		conn.close();
		
		return lista;		
	}
	
	public Produto buscar(Integer id) throws SQLException {
		Produto prod = null;
		
		String sql = 
				"SELECT id_produto, descricao, unidade_medida" +
				" FROM produtos WHERE id_produto = ?";
		
		Connection conn = Conexao.getConexao();
		
		PreparedStatement ps = 
				conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		//Verificar se encontrou algum registro (produto)
		if(rs.next()) {
			//Criar e retornar um produto
			prod = new Produto();
			prod.setIdProduto(rs.getInt("id_produto"));
			prod.setDescricao(rs.getString("descricao"));
			prod.setUnidadeMedida(rs.getString("unidade_medida"));
			
		} 
		
		conn.close();
		
		return prod; //Se não encontrado, prod sera null	
	}
	public void excluir(Integer idProduto) throws SQLException {
		String sql = "DELETE FROM produtos WHERE id_produto = ?";
		Connection conn = Conexao.getConexao();
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, idProduto);
		ps.executeUpdate();
		conn.close();
	}
}
