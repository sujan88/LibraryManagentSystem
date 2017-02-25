package edu.mum.cs.cs401.dataaccess;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 985333 on 9/13/2016.
 */
public class DataAccessImpl<K,V> implements DataAccess<K,V> {

    Map<K,V> database = new HashMap<K, V>();


    @Override
    public V add(K key, V value) {
        database.put(key,value);
        return value;
    }

    @Override
    public V update(K key, V value) {
        database.put(key,value);
        return value;
    }

    @Override
    public V delete(K key) {

        return database.remove(key);
    }

    @Override
    public V get(K key) {
        return database.get(key);
    }


    public int getValueSize(){
        return database.values().size();
    }

    @Override
    public Map<K, V> getDb() {
        return database;
    }


}
