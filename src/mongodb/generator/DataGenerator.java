/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodb.generator;

/**
 *
 * @author Pedro
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
        DataFileGenerator fileMongoDB = new DataFileGeneratorMongoDBImpl(registers);

        String[] provinces = {"RS", "RJ", "SP", "SC", "PR", "MG", "MS", "MA", "PE", "CE", "BA"};
        String[] registrationPlates = {"ABC-1234", "AAA-1111", "ZZZ-9999"};
        int[] years = {2010, 2011, 2012, 2013, 2014, 2015};
        int[] models = {2010, 2011, 2012, 2013, 2014, 2015};
        int[] doors = {2, 4};
        String[] transmissions = {"Automático", "Manual"};
        String[] fuels = {"Gasolina", "Álcool", "Diesel"};
        String[] colors = {"Branco", "Prata", "Preto"};
        String[] carModels = {", \"modeloCarro\": { \"marca\": \"RENAULT\", \"nome\": \"Clio RN 1.6 5p\", \"anoModelo\": 2002 } ",
                             ", \"modeloCarro\": { \"marca\": \"RENAULT\", \"nome\": \"Megane Hatch RXE 2.0\", \"anoModelo\": 2012 } ",
                             ", \"modeloCarro\": { \"marca\": \"RENAULT\", \"nome\": \"Twingo 1.0 8V\", \"anoModelo\": 2014 } ",
                             ", \"modeloCarro\": { \"marca\": \"PEUGEOT\", \"nome\": \"206 Soleil 1.6 3p\", \"anoModelo\": 2006 } ",
                             ", \"modeloCarro\": { \"marca\": \"PEUGEOT\", \"nome\": \"3008 Allure 1.6 Turbo 16V 5p Aut.\", \"anoModelo\": 2014 } ",
                             ", \"modeloCarro\": { \"marca\": \"PEUGEOT\", \"nome\": \"307 Rallye 2.0 16V 138cv 5p Aut\", \"anoModelo\": 2015 } ",
                             ", \"modeloCarro\": { \"marca\": \"VW\", \"nome\": \"Fox PRIME/Hghi. IMOTION 1.6 T.Flex 8V 5p\", \"anoModelo\": 2012 } ",
                             ", \"modeloCarro\": { \"marca\": \"VW\", \"nome\": \"Gol 1.6 Mi Power Total Flex 8V 4p\", \"anoModelo\": 2011 } ",
                             ", \"modeloCarro\": { \"marca\": \"VW\", \"nome\": \"Golf 1.6Mi/ 1.6Mi Gener./Black & Silver\", \"anoModelo\": 2009 } ",                             
                             ", \"modeloCarro\": { \"marca\": \"VW\", \"nome\": \"Parati 2.0 Mi/ 2.0 Mi Track & Field\", \"anoModelo\": 2011 } "};
        String[] optionalNames = {"Vidro Elétrico", "Alarme", "DVD"};
        String[] texts = {"Bateria nova", "Inteiro", "Único dono"};
        String[] pathNames = {"\\anuncio\\fotos\\fotoABC.jpg", "\\anuncio\\fotos\\fotoDeFrente.jpg", "\\anuncio\\fotos\\fotoInterna.jpg"};
        
        String province;
        String registrationPlate;
        int year;
        int model;
        int door;
        String transmission;
        String fuel;
        String color;
        double kilometer;
        double value;
        String carModel;
        String[] optionalName;
        String[] text;
        String[] pathName;
        int numberOptionalNames;
        int numberTexts;
        int numberPathNames;
        
        StringBuilder register;
        
        for (int i = 0; i < registers; i++) {
            province = provinces[(int) (Math.random() * provinces.length)];
            registrationPlate = registrationPlates[(int) (Math.random() * registrationPlates.length)];
            year = years[(int) (Math.random() * years.length)];
            model = models[(int) (Math.random() * models.length)];
            door = doors[(int) (Math.random() * doors.length)];
            transmission = transmissions[(int) (Math.random() * transmissions.length)];
            fuel = fuels[(int) (Math.random() * fuels.length)];
            color = colors[(int) (Math.random() * colors.length)];
            kilometer = 1000 + (Math.random() * 100000);
            value = 5000 + (Math.random() * 100000);
            carModel = carModels[(int) (Math.random() * carModels.length)];
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
            register = new StringBuilder("{ \"uf\": \"");
            register.append(province);
            register.append("\", \"placa_carro\": \"");
            register.append(registrationPlate);
            register.append("\", \"ano\": ");
            register.append(year);
            register.append(", \"modelo\": ");
            register.append(model);
            register.append(", \"qtd_portas\": ");
            register.append(door);
            register.append(", \"cambio\": \"");
            register.append(transmission);
            register.append("\", \"combustivel\": \"");
            register.append(fuel);
            register.append("\", \"cor\": \"");
            register.append(color);
            register.append("\", \"km\": ");
            register.append(kilometer);
            register.append(", \"valor\": ");
            register.append(value);
            register.append(carModel);
                    

            for (int j = 0; j < numberOptionalNames; j++) {
                if(j == 0){
                    register.append(", \"opcional\": ");
                    if(numberOptionalNames > 1){
                        register.append("[ ");
                    }
                }
                register.append("\"");
                register.append(optionalName[j]);
                register.append("\"");
                        
                if(j == (numberOptionalNames - 1)){
                    if(numberOptionalNames > 1){
                        register.append(" ]");
                    }
                } else {
                    register.append(", ");
                }
            }

            for (int j = 0; j < numberTexts; j++) {
                if(j == 0){
                    register.append(", \"detalhe\": ");
                    if(numberTexts > 1){
                        register.append("[ ");
                    }
                }
                register.append("\"");
                register.append(text[j]);
                register.append("\"");
                        
                if(j == (numberTexts - 1)){
                    if(numberTexts > 1){
                        register.append(" ]");
                    }
                } else {
                    register.append(", ");
                }
            }
            
            for (int j = 0; j < numberPathNames; j++) {
                if(j == 0){
                    register.append(", \"foto\": ");
                    if(numberPathNames > 1){
                        register.append("[ ");
                    }
                }
                register.append("\"");
                register.append(pathName[j]);
                register.append("\"");
                        
                if(j == (numberPathNames - 1)){
                    if(numberPathNames > 1){
                        register.append(" ]");
                    }
                } else {
                    register.append(", ");
                }
            }

            register.append(" }");
            
            fileMongoDB.write(register.toString());
        }
       
        fileMongoDB.saveDataFile();

    }
}
