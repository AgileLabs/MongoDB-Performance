/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mongodb.generator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public abstract class DataFileGenerator {
    
    private static final String RESOURCE_PATH = ".\\resources\\";
    private static final String FILE_EXTENSION = ".txt";
    
    private File dataFile;
    private FileOutputStream dataFileOutputStream;
    private BufferedOutputStream dataFileBufferedOutputStream;

    public DataFileGenerator(String dataFileName) {
        this.dataFile = new File(RESOURCE_PATH + dataFileName + FILE_EXTENSION);
        try {
            this.dataFileOutputStream = new FileOutputStream(dataFile);
            this.dataFileBufferedOutputStream = new BufferedOutputStream(dataFileOutputStream);
        } catch (FileNotFoundException ex) {
            System.out.println("Não foi possível criar o arquivo " + dataFile.getName());
            Logger.getLogger(DataFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void saveDataFile() {
        try {
            dataFileBufferedOutputStream.close();
        } catch (IOException ex) {
            System.out.println("Não foi possível gravar o arquivo " + dataFile.getName());
            Logger.getLogger(DataFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void write(String register) {
        try {
            dataFileBufferedOutputStream.write(register.getBytes());
            dataFileBufferedOutputStream.write("\n".getBytes() );
        } catch (IOException ex) {
            System.out.println("Não foi possível gravar o registro " + register);
            Logger.getLogger(DataFileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
