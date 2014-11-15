/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.generator;

import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Murillo
 */
public class DataGenerator {

    private static final int FILE_WITH_10_REGISTERS = 10;
    private static final int FILE_WITH_100_REGISTERS = 100;
    private static final int FILE_WITH_1000_REGISTERS = 1000;
    private static final int FILE_WITH_10000_REGISTERS = 10000;
    private static final int FILE_WITH_100000_REGISTERS = 100000;
    private static final int FILE_WITH_1000000_REGISTERS = 1000000;

    public static void main(String[] args) {
        createFile(FILE_WITH_10_REGISTERS);
        createFile(FILE_WITH_100_REGISTERS);
        createFile(FILE_WITH_1000_REGISTERS);
        createFile(FILE_WITH_10000_REGISTERS);
        createFile(FILE_WITH_100000_REGISTERS);
        createFile(FILE_WITH_1000000_REGISTERS);
    }

    private static void createFile(int registers) {
        DataFileGenerator fileMySQL = new DataFileGeneratorMySQLImpl(registers);

        String[] registrationPlates = {"ABC-1234", "AAA-1111", "ZZZ-9999"};
        int[] years = {2010, 2011, 2012, 2013, 2014, 2015};
        int[] models = {2010, 2011, 2012, 2013, 2014, 2015};
        int[] doors = {2, 4};
        String[] transmissions = {"Automático", "Manual"};
        String[] fuels = {"Gasolina", "Álcool", "Diesel"};
        String[] colors = {"Branco", "Prata", "Preto"};
        String[] optionalNames = {"Vidro Elétrico", "Alarme", "DVD"};
        String[] texts = {"Bateria nova", "Inteiro", "Único dono"};
        String[] provinces = {"RS", "RJ", "SP", "SC", "PR", "MG", "MS", "MA", "PE", "CE", "BA"};
        String[] pathNames = {"\\anuncio\\fotos\\fotoABC.jpg", "\\anuncio\\fotos\\fotoDeFrente.jpg", "\\anuncio\\fotos\\fotoInterna.jpg"};
        
        String registrationPlate;
        int year;
        int model;
        int door;
        String transmission;
        String fuel;
        String province;
        String color;
        int kilometer;
        double value;
        String[] optionalName;
        String[] text;
        String[] pathName;
        int numberOptionalNames;
        int numberTexts;
        int numberPathNames;
        int idModelo;
        
        StringBuilder register;

        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('1', 'RENAULT', 'Clio RN 1.6 5p', '2002');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('2', 'RENAULT', 'Megane Hatch RXE 2.0', '2012');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('3', 'RENAULT', 'Twingo 1.0 8V', '2014');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('4', 'PEUGEOT', '206 Soleil 1.6 3p', '2006');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('5', 'PEUGEOT', '3008 Allure 1.6 Turbo 16V 5p Aut.', '2014');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('6', 'PEUGEOT', '307 Rallye 2.0 16V 138cv 5p Aut', '2015');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('7', 'VW', 'Fox PRIME/Hghi. IMOTION 1.6 T.Flex 8V 5p', '2012');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('8', 'VW', 'Gol 1.6 Mi Power Total Flex 8V 4p', '2011');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('9', 'VW', 'Golf 1.6Mi/ 1.6Mi Gener./Black & Silver', '2009');");
        fileMySQL.write("INSERT INTO modelos (id_modelos, marca, modelo, ano) VALUES ('10', 'VW', 'Parati 2.0 Mi/ 2.0 Mi Track & Field', '2011');");
        // índices no banco
        int ib, jb;
        for (int i = 0; i < registers; i++) {
            ib = i + 1;
            Random rand = new Random();
            registrationPlate = registrationPlates[(int) (Math.random() * registrationPlates.length)];
            year = years[(int) (Math.random() * years.length)];
            //rand.nextInt((max - min) + 1) + min
            idModelo = rand.nextInt((10 - 1) + 1) + 1;                                   
            model = models[(int) (Math.random() * models.length)];
            door = doors[(int) (Math.random() * doors.length)];
            province = provinces[(int) (Math.random() * provinces.length)];
            transmission = transmissions[(int) (Math.random() * transmissions.length)];
            fuel = fuels[(int) (Math.random() * fuels.length)];
            color = colors[(int) (Math.random() * colors.length)];
            kilometer = 1000 + (int)(Math.random() * 100000);
            value = 5000 + (Math.random() * 100000);
            numberOptionalNames = (int) (Math.random() * 4);
            optionalName = new String[numberOptionalNames];
            numberTexts = (int) (Math.random() * 4);
            text = new String[numberTexts];
            numberPathNames = (int) (Math.random() * 4);            
            pathName = new String[numberPathNames];            
            for (int j = 0; j < numberOptionalNames; j++) {
                optionalName[j] = optionalNames[(int) (Math.random() * optionalNames.length)];
            }            
            for (int j = 0; j < numberTexts; j++) {
                text[j] = texts[(int) (Math.random() * texts.length)];
            }            
            for (int j = 0; j < numberPathNames; j++) {
                pathName[j] = pathNames[(int) (Math.random() * pathNames.length)];
            }
            
            register = new StringBuilder();                        
            register.append("INSERT INTO anuncios (id_anuncios, uf, placa_carro, ano, modelo, qtd_portas, cambio, combustivel, cor, km, valor, id_modelo) VALUES (");
            register.append(String.format("'%d', ", ib));
            register.append(String.format("'%s', ", province));
            register.append(String.format("'%s', ", registrationPlate));
            register.append(String.format("'%d', ", year));
            register.append(String.format("'%d', ", model));
            register.append(String.format("'%d', ", door));
            register.append(String.format("'%s', ", transmission));            
            register.append(String.format("'%s', ", fuel));
            register.append(String.format("'%s', ", color));
            register.append(String.format("'%d', ", kilometer));
            register.append(String.format(Locale.ENGLISH, "'%.2f', ", value));
            register.append(String.format("'%d'); ", idModelo));
            fileMySQL.write(register.toString());
                        
            for (int j = 0; j < numberOptionalNames; j++) {   
                jb = j + 1;
                register = new StringBuilder(); 
                register.append("INSERT INTO opcionais_anuncio (fk_id_anuncio, id_opcionais_anuncio, nome_opcional) VALUES (");
                register.append(String.format("'%d', ", ib));                
                register.append(String.format("'%d', ", jb));
                register.append(String.format("'%s'); ", optionalName[j]));
                fileMySQL.write(register.toString());
            }

            for (int j = 0; j < numberTexts; j++) {
                jb = j + 1;
                register = new StringBuilder(); 
                register.append("INSERT INTO detalhes_anuncio (fk_id_anuncio, id_detalhes_anuncio, texto) VALUES (");
                register.append(String.format("'%d', ", ib));                
                register.append(String.format("'%d', ", jb));
                register.append(String.format("'%s'); ", text[j]));
                fileMySQL.write(register.toString());
            }
                
            for (int j = 0; j < numberPathNames; j++) {
                jb = j + 1;
                register = new StringBuilder(); 
                register.append("INSERT INTO fotos_anuncio (fk_id_anuncio, id_fotos_anuncio, caminho_nome) VALUES (");
                register.append(String.format("'%d', ", ib));                
                register.append(String.format("'%d', ", jb));
                register.append(String.format("'%s'); ", pathName[j]));
                fileMySQL.write(register.toString());                        
            }                       
        }       
        fileMySQL.saveDataFile();
    }
}
