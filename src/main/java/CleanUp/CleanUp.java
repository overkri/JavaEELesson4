package CleanUp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public class CleanUp {
    public void cleanUpHashMap(Object object, Set<String> fieldsToClean, Set<String> fieldsToOutput) throws IllegalAccessException {
        if ((object instanceof HashMap)) {
            for (String str : fieldsToClean) {
                if (((HashMap) object).containsKey(str)) {
                    ((HashMap) object).remove(str);
                }
            }
            for (String stro : fieldsToOutput) {
                if (((HashMap) object).containsKey(stro)) {
                    System.out.println(((HashMap) object).get(stro));
                }
            }
        } else {
            cleanUpObj(object, fieldsToClean, fieldsToOutput);
        }
    }


    private void cleanUpObj(Object object, Set<String> fieldsToClean, Set<String> fieldsToOutput) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        Set<String> fieldNames = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toSet());
        if (!(fieldNames.containsAll(fieldsToClean)) || !(fieldNames.containsAll(fieldsToOutput))) {
            throw new IllegalArgumentException();
        }
        for (Field field : fields) {
            field.setAccessible(true);
            if (fieldsToClean.contains(field.getName())) {
                Class t = field.getType();
                switch (t.toString()) {
                    case "byte":
                    case "short":
                    case "int":
                        field.setInt(object, 0);
                        break;
                    case "float":
                        field.setFloat(object, 0);
                        break;
                    case "long":
                        field.setLong(object, 0);
                        break;
                    case "double":
                        field.setDouble(object, 0);
                        break;
                    case "boolean":
                        field.setBoolean(object, false);
                        break;
                    case "char":
                        field.setChar(object, ' ');
                        break;
                    default:
                        field.set(object, null);
                        break;

                }
            }
            if (fieldsToOutput.contains(field.getName())) {
                Class t = field.getType();
                switch (t.toString()) {
                    case "byte":
                        System.out.println(field.getByte(object));
                        break;
                    case "short":
                        System.out.println(field.getShort(object));
                        break;
                    case "int":
                        System.out.println(field.getInt(object));
                        break;
                    case "float":
                        System.out.println(field.getFloat(object));
                        break;
                    case "long":
                        System.out.println(field.getLong(object));
                        break;
                    case "double":
                        System.out.println(field.getDouble(object));
                        break;
                    case "boolean":
                        System.out.println(field.getBoolean(object));
                        break;
                    case "char":
                        System.out.println(field.getChar(object));
                        break;
                    default:
                        System.out.println(field.get(object));
                        break;

                }
            }
        }
    }
}



