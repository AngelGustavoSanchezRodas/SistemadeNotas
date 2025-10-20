/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://svr-prog2.postgres.database.azure.com/dinamita";
    private static final String USER = "usr_root_db";
    private static final String PASSWORD = "UMG_$2025Pro2";
    
    public static Connection getConexion() throws SQLException 
    {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de PostgreSQL");
            e.printStackTrace();
            return null;
        }
    }
    
}
