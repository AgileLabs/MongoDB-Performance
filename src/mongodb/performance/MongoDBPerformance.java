/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mongodb.performance;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

/**
 *
 * @author Pedro
 */
public class MongoDBPerformance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, FileNotFoundException, IOException {
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("myDatabase");
        
        DBCollection collection = db.getCollection("ads");
        collection.drop();
        
        BulkWriteOperation builder = collection.initializeUnorderedBulkOperation();
        
        FileInputStream fileInputStream = new FileInputStream(".\\resources\\MongoDB100000.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            DBObject bson = (DBObject) JSON.parse(line);
            builder.insert(bson);
        }
        bufferedReader.close();
        BulkWriteResult result = builder.execute();
        System.out.println("Resultado: " + result);
    }
    
}
