package com.vominh.example.mapdb;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.mapdb.serializer.SerializerCompressionWrapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Example {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\db\\test.db");

        DB db = null;
        try {
            db = DBMaker.fileDB(path.toString())
                    .cleanerHackEnable()
                    .transactionEnable()
                    .closeOnJvmShutdown()
                    .make();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        var country = db.hashMap("country")
                .keySerializer(new SerializerCompressionWrapper(Serializer.STRING))
                .valueSerializer(new SerializerCompressionWrapper(Serializer.STRING))
                .createOrOpen();


        System.out.println(country.get("vn"));
        System.out.println(country.get("de"));
//        for(var entry : country.entrySet()){
//            System.out.println(entry.ge)
//        }

        country.put("vn", "Viet Nam");
        country.put("de", "German");
        try {
            for (final Map.Entry<String, Object> entry : db.getAll().entrySet()) {
                final String name = entry.getKey();
                final Object value = entry.getValue();
                if (value instanceof Map) {
                    System.out.println(name + "/" + value.getClass());
                    inspectMap(name, (Map<?, ?>) value);
                } else {
                    System.err.println(String.format("Unexpected type (%s) for '%s'.", value.getClass(), name));
                }
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }



        db.commit();
        db.close();

    }

    private static <K, V> void inspectMap(final String name, final Map<K, V> map) {
        System.out.println(name);
        for (final Map.Entry<K, V> entry : map.entrySet()) {
            final K key = entry.getKey();
            final V value = entry.getValue();
            System.out.println(String.format("    %s = %s [%s, %s]", key, value, key.getClass(), value.getClass()));
        }
    }
}
