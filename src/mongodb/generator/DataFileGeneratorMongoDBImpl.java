/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mongodb.generator;


public class DataFileGeneratorMongoDBImpl extends DataFileGenerator {

    private static final String DATA_FILE_PREFIX = "MongoDB";

    public DataFileGeneratorMongoDBImpl(int registers) {
        super(DATA_FILE_PREFIX + registers);
    }

}