package CleanUp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CleanUpTest {
    private  int one = 1;
    private  byte two = 2;
    private  long three = 3;
    private  boolean four = true;
    private  char five = 'A';
    private  String six = "fasd";

    @Test
    public void cleanUpObject() {
        HashSet<String> str1 = new HashSet<>();
        Set<String> str2 = new HashSet<>();
        str1.add("one");
        str1.add("six");
        str2.add("two");
        str2.add("five");
        CleanUp cl = new CleanUp();
        try {
            cl.cleanUpObject(this, str1, str2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(one, 0);
        Assert.assertEquals(two, 2);
        Assert.assertEquals(three, 3);
        Assert.assertTrue(four);
        Assert.assertEquals(five, 'A');
        assertNull(six);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cleanUpFail() {
        HashSet<String> str1 = new HashSet<>();
        Set<String> str2 = new HashSet<>();
        str1.add("one");
        str1.add("six");
        str2.add("two");
        str2.add("e");
        CleanUp cl = new CleanUp();
        try {
            cl.cleanUpObject(this, str1, str2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void cleanUpHashMap() {
        HashSet<String> str1 = new HashSet<>();
        Set<String> str2 = new HashSet<>();
        str1.add("one");
        str1.add("six");
        str2.add("two");
        str2.add("five");
        HashMap<String, Integer> hashmap = new HashMap<>();
        hashmap.put("one", 1);
        hashmap.put("two", 2);
        hashmap.put("three", 3);
        hashmap.put("four", 4);
        hashmap.put("five", 5);
        hashmap.put("six", 6);
        CleanUp cl = new CleanUp();
        try {
            cl.cleanUpObject(hashmap, str1, str2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assertFalse(hashmap.containsKey("one"));
        Assert.assertEquals(two, 2);
        Assert.assertEquals(three, 3);
        Assert.assertTrue(four);
        assertFalse(hashmap.containsKey("six"));

    }
}