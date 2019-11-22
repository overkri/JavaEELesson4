package CleanUp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class MaslakovTest {
    @Test
    public void simpleTest() throws IllegalAccessException {
        final CleanUp cleanUp = new CleanUp();
        final Simple simple = new Simple();
        final HashSet<String> fields = new HashSet<String>() {{
            add("intValue");
            add("floatValue");
            add("shortValue");
            add("longValue");
            add("strValue");
            add("intValue2");
        }};
        cleanUp.cleanUpObject(simple, fields, fields);
        Assert.assertEquals(0, simple.getIntValue());
        Assert.assertEquals(0f, simple.getFloatValue(), 0);
        Assert.assertEquals(0, simple.getShortValue());
        Assert.assertEquals(0L, simple.getLongValue());
        Assert.assertNull(simple.getStrValue());
        Assert.assertNull(simple.getIntValue2());
    }

    private static class Simple {
        private int intValue = 42;
        public float floatValue = 10.2f;
        public short shortValue = 7;
        public long longValue = 8L;
        private String strValue = "value";
        public Integer intValue2 = 45;

        public int getIntValue() {
            return intValue;
        }

        public float getFloatValue() {
            return floatValue;
        }

        public short getShortValue() {
            return shortValue;
        }

        public long getLongValue() {
            return longValue;
        }

        public String getStrValue() {
            return strValue;
        }

        public Integer getIntValue2() {
            return intValue2;
        }
    }
}