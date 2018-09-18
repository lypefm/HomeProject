/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import util.exception.SystemError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author jadir
 */
public class ConexionFactory {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "rajidc";
    

    public static Connection getConexao() throws SystemError {
        if(conexao == null){
            try {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
               } catch (ClassNotFoundException ex) {
                throw new SystemError("Falha na conexão com o banco de dados", ex); 
            } catch (SQLException ex) {
               throw new SystemError("Driver do banco de dados não encontrado", ex); 
            }
        }
        return conexao;
    }
    public static void desconectar() throws SystemError{
        if(conexao != null){
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new SystemError("Falha ao desconectar", ex); 
            }
            conexao = null;
        }
    }
}
