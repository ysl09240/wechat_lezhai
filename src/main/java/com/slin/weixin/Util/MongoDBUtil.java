package com.slin.weixin.Util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * @author SongLin.Yang
 * @data 2016-03-30 11:35
 */
public class MongoDBUtil {
    public static MongoClient mongoClient =  new MongoClient(new ServerAddress("localhost", 27017));
    public static DB db = null;
    public static DBCollection collection = null ;


    public static  DB getDB (){
        if(db == null){
            db = mongoClient.getDB("mongo");
        }
        return db;
    }

    public static DBCollection getCollection(String name){
        if(collection == null){
            collection = db.getCollection(name);
        }
        return collection;
    }


}
