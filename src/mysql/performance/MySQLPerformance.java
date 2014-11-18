/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mysql.performance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pedro
 */
public class MySQLPerformance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length == 0){
            System.out.println("Parâmetro não informado!");                            
            System.exit(-1);
        }    
        System.out.println("Parâmetro: " + args[0]);       
        Connection conn = null;
        Statement stmt = null;
        try {            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/pad?" + 
                                        "user=root&password=root");
            stmt = conn.createStatement();            
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
            stmt.executeUpdate("TRUNCATE TABLE detalhes_anuncio");
            stmt.executeUpdate("TRUNCATE TABLE fotos_anuncio");
            stmt.executeUpdate("TRUNCATE TABLE opcionais_anuncio");
            stmt.executeUpdate("TRUNCATE TABLE anuncios");
            stmt.executeUpdate("TRUNCATE TABLE modelos");
            stmt.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
            File f = new File(".\\resources\\MySQLDB" + args[0] + ".txt");
            if (!f.exists()){
                System.out.println("Arquivo de importação não existe! " + f.getAbsolutePath());                            
                System.exit(-1);
            }    
            // Open the file for import
            FileInputStream fileInputStream = new FileInputStream(f);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            // Insert
            // Time start    
            long start = System.currentTimeMillis();
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                stmt.executeUpdate(line);
            }
            bufferedReader.close();            
            // Time end
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("[insert] Time elapsed: " + elapsed + " ms");
            // Update
            // Time start    
            start = System.currentTimeMillis();
            stmt.executeUpdate("UPDATE anuncios SET ano=2006");
            // Time end
            elapsed = System.currentTimeMillis() - start;
            System.out.println("[update] Time elapsed: " + elapsed + " ms");
            // Select
            // Time start    
            start = System.currentTimeMillis();
            ResultSet rs = stmt.executeQuery("SELECT a.id_anuncios, m.marca, m.modelo, a.uf, a.placa_carro, a.qtd_portas, " +
                                     "a.cambio, a.combustivel, a.cor, a.km, a.valor, d.texto " +
                              "FROM anuncios a, modelos m, detalhes_anuncio d " +
                              "WHERE m.id_modelos = a.id_modelo and d.fk_id_anuncio = a.id_anuncios " +
                              "ORDER BY 1");
            while (rs.next()) {
                continue;
            }
            // Time end
            elapsed = System.currentTimeMillis() - start;
            System.out.println("[select] Time elapsed: " + elapsed + " ms");
            // Delete
            // Time start    
            start = System.currentTimeMillis();
            stmt.executeUpdate("DELETE FROM detalhes_anuncio");
            stmt.executeUpdate("DELETE FROM fotos_anuncio");
            stmt.executeUpdate("DELETE FROM opcionais_anuncio");
            stmt.executeUpdate("DELETE FROM anuncios");
            // Time end
            elapsed = System.currentTimeMillis() - start;
            System.out.println("[delete] Time elapsed: " + elapsed + " ms");            
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
                stmt = null;
            }
        }
    }
}
