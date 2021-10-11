package util;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringUtilTest {

    private static final String RANDOM_DEFAULT_STRING = initRandomString();

    private static String initRandomString() {
        byte[] randomByte = new byte[7];
        new Random().nextBytes(randomByte);
        return new String(randomByte);
    }

    @Test
    public void TEST_1_defaultStr() {
        Assert.assertEquals(StringUtil.defaultStr("", RANDOM_DEFAULT_STRING), RANDOM_DEFAULT_STRING);
        Assert.assertEquals(StringUtil.defaultStr(null, RANDOM_DEFAULT_STRING), RANDOM_DEFAULT_STRING);
        Assert.assertEquals(StringUtil.defaultStr(RANDOM_DEFAULT_STRING, null), RANDOM_DEFAULT_STRING);
    }

    @Test
    public void TEST_2_SubStringToBefore() {
        Assert.assertEquals(StringUtil.subStringToBefore("123/456/789", "/"), "123");
        Assert.assertEquals(StringUtil.subStringToBefore("123/456/789", "/", 1), "123/");
    }

    @Test
    public void TEST_3_SubStringToAfter() {
        Assert.assertEquals(StringUtil.subStringToAfter("123/456/789", "/"), "456/789");
        Assert.assertEquals(StringUtil.subStringToAfter("123/456/789", "/", 0), "/456/789");
    }

    @Test
    public void TEST_4_LastSubStringToBefore() {
        Assert.assertEquals(StringUtil.lastSubStringToBefore("123/456/789", "/"), "123/456");
        Assert.assertEquals(StringUtil.lastSubStringToBefore("123/456/789", "/", 1), "123/456/");
    }

    @Test
    public void TEST_5_LastSubStringToAfter() {
        Assert.assertEquals(StringUtil.lastSubStringToAfter("123/456/789", "/"), "789");
        Assert.assertEquals(StringUtil.lastSubStringToAfter("123/456/789", "/", 0), "/789");
    }
}