/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.mongodbtest;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author hcadavid
 */
public class MongoDBTest {

    public static void main(String a[]){
        findAndPrintData();
    }
    
    public static void findAndPrintData(){

        MongoClientURI uri = new MongoClientURI("mongodb://scott:tiger@ds137281.mlab.com:37281/example");
                
        MongoClient client = new MongoClient(uri);

        MongoDatabase db = client.getDatabase(uri.getDatabase());
        
        MongoCollection<Document> coll = db.getCollection("users");
        
        BasicDBObject dbo=new BasicDBObject();
        dbo.append("id", 123);
        
        FindIterable<Document> res=coll.find(dbo);
        
        MongoCursor<Document> docit=res.iterator();
        
        while (docit.hasNext()){
            Document doc=docit.next();
            System.out.println(doc.get("name"));
        }
        
        client.close();        

    }
    
    public static void insertData(String jsonObject){

        MongoClientURI uri = new MongoClientURI("mongodb://scott:tiger@ds137281.mlab.com:37281/example");
                
        MongoClient client = new MongoClient(uri);

        MongoDatabase db = client.getDatabase(uri.getDatabase());

        MongoCollection<Document> coll = db.getCollection("users");

        Document master=Document.parse("{id:123,name:'John',phones:[{type:'cel',num:2223},"
                + "{type:'cel',num:2223},"
                + "{type:'fix',num:2232231} ]}");
        
        coll.insertOne(master);
        
        client.close();
        
    }
    
    

}
