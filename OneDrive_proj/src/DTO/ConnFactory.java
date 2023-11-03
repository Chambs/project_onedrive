
package DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/// NA HORA DE CRIAR UMA TABLE PROS ARQUIVOS DE AUDIO E IMAGEM NO SQL ELA DEVE SER DO TIPO <BLOB> OU <LONGBLOB>
/// E A TABLE DOS ARQUIVOS DE TEXTO DEVE SER DO TIPO <LONGTEXT>

public class ConnFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/proj_db";
    private static final String USER = "root"; // usuario bd
    private static final String PASS = "root"; // senha bd
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String ERROBD = "Erro na conexão com o Banco de Dados: ";
    
    private static Connection conn = null;
    
    public static Connection getConn(){
        
        //// Driver
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            throw new RuntimeException(ERROBD + e);
        }
        
        //// SQL
        try {
            if(conn == null){
                conn = DriverManager.getConnection(URL,USER,PASS);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(ERROBD + e);
        }
    }
       
}
