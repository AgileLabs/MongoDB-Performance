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
import com.mongodb.DBCursor;
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
        if (args.length == 0) {
            System.out.println("Parâmetro não informado!");
            System.exit(-1);
        }
        System.out.println("Parâmetro: " + args[0]);

        MongoClient mongoClient = new MongoClient();
        //MongoClient mongoClient = new MongoClient( "54.172.218.64" , 27017 );
        DB db = mongoClient.getDB("myDatabase");

        DBCollection collection = db.getCollection("ads");
        collection.drop();

        BulkWriteOperation builder = collection.initializeUnorderedBulkOperation();

        FileInputStream fileInputStream = new FileInputStream(".\\resources\\MongoDB" + args[0] + ".txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        // Insert
        // Time start    
        long start = System.currentTimeMillis();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            DBObject bson = (DBObject) JSON.parse(line);
            builder.insert(bson);
        }
        bufferedReader.close();
        builder.execute();
        //Time end
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[insert] Time elapsed: " + elapsed + " ms");

        // Update
        // Time start    
        start = System.currentTimeMillis();
        collection.updateMulti(new BasicDBObject(), new BasicDBObject("$set", new BasicDBObject().append("ano", 2006)));
        // Time end
        elapsed = System.currentTimeMillis() - start;
        System.out.println("[update] Time elapsed: " + elapsed + " ms");

        // Select
        // Time start    
        start = System.currentTimeMillis();
        BasicDBObject keys = new BasicDBObject();
        keys.put("_id", 1);
        keys.put("modeloCarro.marca", 1);
        keys.put("modeloCarro.nome", 1);
        keys.put("uf", 1);
        keys.put("placa_carro", 1);
        keys.put("qtd_portas", 1);
        keys.put("cambio", 1);
        keys.put("combustivel", 1);
        keys.put("cor", 1);
        keys.put("km", 1);
        keys.put("valor", 1);
        keys.put("detalhe", 1);
        BasicDBObject sort = new BasicDBObject("_id", 1);

        DBCursor cursor = collection.find(new BasicDBObject(), keys).sort(sort);
        while (cursor.hasNext()) {
            cursor.next();
        }
        // Time end
        elapsed = System.currentTimeMillis() - start;
        System.out.println("[select] Time elapsed: " + elapsed + " ms");

        // Delete
        // Time start    
        start = System.currentTimeMillis();
        collection.remove(new BasicDBObject());
        // Time end
        elapsed = System.currentTimeMillis() - start;
        System.out.println("[delete] Time elapsed: " + elapsed + " ms");
    }

}
