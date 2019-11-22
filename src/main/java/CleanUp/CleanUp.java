package CleanUp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CleanUp class is used for  seting fields in object to zero declared in set "fieldsToClean"
 * and print values of fields declared in set "fieldsToOutput *
 */
public class CleanUp {
    /**
     *Methods checks for instance of object, if it hashmap, it uses CleanHashMap methof
     * otherwise CleanObject
     * @param object an object you want to change
     * @param fieldsToClean set, where listed fields to nullify
     * @param fieldsToOutput set, where listed fields to print
     * @throws IllegalAccessException exception if fields are private
     */
    public void cleanUpObject(Object object, Set<String> fieldsToClean, Set<String> fieldsToOutput)
            throws IllegalAccessException {
        if (object instanceof HashMap) {
            cleanUpHashMap(object, fieldsToClean, fieldsToOutput);
        } else {
            cleanObj(fieldsToClean, fieldsToOutput, object);
        }
    }

    /**
     *Methods nullifies fields or prints them, depending on set in object
     * @param fieldsToClean set, where listed fields to nullify
     * @param fieldsToOutput set, where listed fields to print
     * @param object an object you want to change
     * @throws IllegalAccessException exception if fields are private
     */
    private void cleanObj(Set<String> fieldsToClean, Set<String> fieldsToOutput, Object object)
            throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        Set<String> fieldNames = Arrays.stream(fields)
                .map(Field::getName)
                .collect(Collectors.toSet());
        if (!(fieldNames.containsAll(fieldsToClean)) || !(fieldNames.containsAll(fieldsToOutput))) {
            throw new IllegalArgumentException("Set contains fields, that's not present in original object.");
        }
        for (Field field : fields) {
            field.setAccessible(true);
            if (fieldsToClean.contains(field.getName())) {
                Class t = field.getType();
                switch (t.toString()) {
                    case "byte":
                        field.setShort(object, (byte) 0);
                        break;
                    case "short":
                        field.setShort(object, (short) 0);
                        break;
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
/*
     *Methods nullifies fields or prints them, depending on set in hashmap
     * @param fieldsToClean set, where listed fields to nullify
     * @param fieldsToOutput set, where listed fields to print
     * @param object an object you want to change
     * @throws IllegalAccessException exception if fields are private
     */
    private void cleanUpHashMap(Object object, Set<String> fieldsToClean, Set<String> fieldsToOutput) {
        for (String stringstoclean : fieldsToClean) {
            ((HashMap) object).remove(stringstoclean);
        }
        for (String stringstoutput : fieldsToOutput) {
            if (!((HashMap) object).containsKey(stringstoutput)) {
                continue;
            }
            System.out.println(((HashMap) object).get(stringstoutput));
        }

    }
}



