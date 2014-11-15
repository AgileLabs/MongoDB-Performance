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
        
        String registrationPlate;
        int year;
        int model;
        int door;
        String transmission;
        String fuel;
        String color;
        double kilometer;
        double value;
        
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

            fileMongoDB.write("{ " + 
                    "\"placa_carro\": \"" + registrationPlate + "\", " +
                    "\"ano\": " + year + ", " +          
                    "\"modelo\": " + model + ", " +          
                    "\"qtd_portas\": " + door + ", " +          
                    "\"cambio\": \"" + transmission + "\", " +          
                    "\"combustivel\": \"" + fuel + "\", " +          
                    "\"cor\": \"" + color + "\", " +          
                    "\"km\": " + kilometer + ", " +          
                    "\"valor\": " + value + " }");

        }
        fileMongoDB.saveDataFile();

    }
}
