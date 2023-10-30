/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Chambs
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/// NA HORA DE CRIAR UMA TABLE PROS ARQUIVOS NO SQL ELA DEVE SER DO TIPO <BLOB> OU <LONGBLOB>

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
