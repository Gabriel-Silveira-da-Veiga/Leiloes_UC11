/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        try {
        conn = new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES(?,?,?);";
        
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        prep.execute();
        
        if(conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("DESCONECTADO DA DB!");
        }
        
        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO ao cadastrar o produto no banco de dados");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "select * from produtos;";
            
            prep = conn.prepareStatement(sql);
            ResultSet result = prep.executeQuery();
            
            while (result.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(result.getInt("id"));
                produto.setNome(result.getString("nome"));
                produto.setValor(result.getInt("valor"));
                produto.setStatus(result.getString("status"));
                
                listagem.add(produto);
            }
            
            if(conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("DESCONECTADO DA DB!");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar produtos!");
        }
        return listagem;
    }
    
    public void venderProduto (int id) {
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?;";
            
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            
            prep.execute();
            
            if(conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("DESCONECTADO DA DB!");
            }
        
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar vender produto!");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        try {
            conn = new conectaDAO().connectDB();
            
            String sql = "select * from produtos where status = 'Vendido';";
            
            prep = conn.prepareStatement(sql);
            ResultSet result = prep.executeQuery();
            
            while (result.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                
                produto.setId(result.getInt("id"));
                produto.setNome(result.getString("nome"));
                produto.setValor(result.getInt("valor"));
                produto.setStatus(result.getString("status"));
                
                listagem.add(produto);
            }
            
            if(conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("DESCONECTADO DA DB!");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar produtos!");
        }
        return listagem;
    }
}

