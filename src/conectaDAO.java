
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    Connection conn = null;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root", "GSV052507root");
            System.out.println("SUCESSO DE CONEXAO!");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "FALHA AO TENTAR CARREGAR CLASSE DE CONEXAO!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FALHA AO TENTAR CONECTAR COM O DB, ERRO SQL!");
        }

        return conn;
    }

    public void desconectDB() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("DESCONECTADO DA DB!");
            }
        } catch (SQLException ex) {
            System.out.println("FALHA AO TENTAR DESCONECTAR COM O DB, ERRO SQL!");
        }
    }
}
