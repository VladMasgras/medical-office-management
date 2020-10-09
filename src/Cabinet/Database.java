package Cabinet;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static Database instance;

    private Database(){

    }

    public static Database getDatabase(){
        if (instance == null){
            synchronized (Database.class){
                if (instance == null)
                    instance = new Database();
            }
        }
        return instance;
    }

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> read(String path, Class<T> type) throws Exception {
        List<T> records = new ArrayList<T>();
        Constructor<T>[] constructors = (Constructor<T>[])type.getDeclaredConstructors();
        Constructor<T> constructor = constructors[1];

        Class<?>[] types = (constructor).getParameterTypes();

        for (String line : Files.readAllLines(Paths.get(path))) {
            Object[] arg = new Object[types.length];
            String[] values = line.split(",");
            for (int i = 0; i < types.length; i++) {
                switch (types[i].getSimpleName()) {
                    case "String":
                        arg[i] = values[i];
                        break;
                    case "int":
                        arg[i] = Integer.valueOf(values[i]);
                        break;
                    case "boolean":
                        arg[i] = Boolean.valueOf(values[i]);
                        break;
                    case "float":
                        arg[i] = Float.valueOf(values[i]);
                }
            }
            records.add(constructor.newInstance(arg));
        }
        return records;
    }

    public <T> void write(List<T> records, String path) throws Exception {
        if (records.size() > 0) {
            Field[] fields = records.get(0).getClass().getSuperclass().getDeclaredFields();
            Field[] fieldsClass = records.get(0).getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
            }
            for (Field field : fieldsClass)
                field.setAccessible(true);
            try (PrintWriter writer = new PrintWriter(path)) {
                for (T record : records) {
                    for (int i = 0; i < fields.length; i++) {
                        Object value = fields[i].get(record);
                        if (value != null) {
                            writer.print(value);
                        }
                        writer.print(",");
                    }
                    for (int i = 0; i < fieldsClass.length - 1; i++) {
                        Object value = fieldsClass[i].get(record);
                        if (value != null) {
                            writer.print(value);
                        }
                        writer.print(",");
                    }
                    Object value = fieldsClass[fieldsClass.length - 1].get(record);
                    if (value != null) {
                        writer.println(value);
                    }
                }
            }
            for (Field field : fields) {
                field.setAccessible(false);
            }
            for (Field field : fieldsClass)
                field.setAccessible(false);
        }
    }

}
