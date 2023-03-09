package com.vominh.java.core.reflection;

import com.vominh.java.core.reflection.dto.Country;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DiscoverObject {
    private static List<String> SKIP_PROPERTIES = Arrays.asList("serialVersionUID");

    public static void main(String[] args) {
        discovery(Country.class);
    }

    private static void discovery(Class<?> type) {
        Field[] fields = type.getDeclaredFields();
        Arrays.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field f1, Field f2) {
                return f1.getName().compareTo(f2.getName());
            }
        });

        System.out.println("Discovery for " + type.getName());
        for (Field f : fields) {

            if (SKIP_PROPERTIES.contains(f.getName())) {
                continue;
            }

            System.out.println("- " + f.getName() + "/" + f.getType().getSimpleName());

            if (f.getType().isPrimitive()) {
                continue;
            }

            if (isCollection(f)) {
                Class<?> genericType = null;
                try {
                    System.out.println(extractGenericType(f.getGenericType().getTypeName()));
                    genericType = Class.forName(extractGenericType(f.getGenericType().getTypeName()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                discovery(genericType);
            } else {
                if (isAcceptType(f)) {
                    discovery(f.getType());
                }
            }
        }
    }

    private static boolean isAcceptType(Field field) {
        return !field.getType().getPackage().getName().startsWith("java");
    }

    private static boolean isCollection(Field field) {
        return field.getType().getPackage().getName().startsWith("java.util");
    }

    private static String extractGenericType(String fullGenericType) {
        int firstIndex = fullGenericType.indexOf("<");
        int lastIndex = fullGenericType.indexOf(">");
        return fullGenericType.substring(firstIndex + 1, lastIndex);
    }

}
