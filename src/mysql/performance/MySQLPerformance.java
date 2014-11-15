/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mysql.performance;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
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
            // Open the file for import
            FileInputStream fileInputStream = new FileInputStream(".\\resources\\MySQLDB1000.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            // Time start    
            long start = System.currentTimeMillis();
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                stmt.executeUpdate(line);
            }
            bufferedReader.close();            
            // Time end
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Time elapsed: " + elapsed + " ms");
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
