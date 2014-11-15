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

        String[] registrationPlates = {"ABC-1234", "AAA-1111", "ZZZ-9999"};
        int[] years = {2010, 2011, 2012, 2013, 2014, 2015};
        int[] models = {2010, 2011, 2012, 2013, 2014, 2015};
        int[] doors = {2, 4};
        String[] transmissions = {"Automático", "Manual"};
        String[] fuels = {"Gasolina", "Álcool", "Diesel"};
        String[] colors = {"Branco", "Prata", "Preto"};
        String[] optionalNames = {"Vidro Elétrico", "Alarme", "DVD"};
        String[] texts = {"Bateria nova", "Inteiro", "Único dono"};
        String[] pathNames = {"\\anuncio\\fotos\\fotoABC.jpg", "\\anuncio\\fotos\\fotoDeFrente.jpg", "\\anuncio\\fotos\\fotoInterna.jpg"};
        
        String registrationPlate;
        int year;
        int model;
        int door;
        String transmission;
        String fuel;
        String color;
        double kilometer;
        double value;
        String[] optionalName;
        String[] text;
        String[] pathName;
        int numberOptionalNames;
        int numberTexts;
        int numberPathNames;
        
        StringBuilder register;
        
        for (int i = 0; i < registers; i++) {
            registrationPlate = registrationPlates[(int) (Math.random() * registrationPlates.length)];
            year = years[(int) (Math.random() * years.length)];
            model = models[(int) (Math.random() * models.length)];
            door = doors[(int) (Math.random() * doors.length)];
            transmission = transmissions[(int) (Math.random() * transmissions.length)];
            fuel = fuels[(int) (Math.random() * fuels.length)];
            color = colors[(int) (Math.random() * colors.length)];
            kilometer = 1000 + (Math.random() * 100000);
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
            register = new StringBuilder("{ \"placa_carro\": \"");
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
                    

            for (int j = 0; j < numberOptionalNames; j++) {
                if(j == 0){
                    register.append(", opcional: ");
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
                    register.append(", detalhe: ");
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
                    register.append(", foto: ");
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
