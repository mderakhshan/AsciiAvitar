package com.mir.codingexercise;

import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Authored by Mir Derakhshan on 13/09/2016.
 */
public class Image2AsciiTest {

    //
    // The main tests checking the core functionality is working as expected
    //
    @Test
    public void shouldReturnExpectedResultForImageOfTypeJPG () {
        // A test for a Jpeg image file
        Assert.assertTrue(test(getTestResourceFullPath("kiefer-sutherland.jpg"),
                getTestResourceFullPath("kiefer-sutherland.txt")));
    }
    @Test
    public void shouldReturnExpectedResultForBlackAndWhiteImageOfTypeJPG () {

        // A test for a black and white Jpeg image file
        Assert.assertTrue(test(getTestResourceFullPath("charlie-chaplin.jpg"),
                getTestResourceFullPath("charlie-chaplin.txt")));
    }
    @Test
    public void shouldReturnExpectedResultForImageOfTypePNG () {
        // A test for a png image file
        Assert.assertTrue(test (getTestResourceFullPath("snowman.png"),
                                getTestResourceFullPath("snowman.txt")));
    }

    //
    // Tests to verify the validation for input arguments are working as expected
    //
    @Test (expected = IllegalArgumentException.class)
    public void shouldRaiseAnExceptionIfImageFileNotAnImage () {
        String str = Image2Ascii.convertImage2String(getTestResourceFullPath("snowman.txt"), 100, 150);
    }
    @Test (expected = IllegalArgumentException.class)
    public void shouldRaiseAnExceptionIfImageFileNotExists () {
        String str = Image2Ascii.convertImage2String("C:\\file does not exist", 100, 150);
    }
    @Test (expected = IllegalArgumentException.class)
    public void shouldRaiseAnExceptionIfImageFileIsNull () {
        String str = Image2Ascii.convertImage2String("", 100, 150);
    }
    @Test (expected = IllegalArgumentException.class)
    public void souldRaiseAnExceptionIfWidthIsZero () {
        String str = Image2Ascii.convertImage2String (getTestResourceFullPath("snowman.png"), 0, 150);
    }
    @Test (expected = IllegalArgumentException.class)
    public void souldRaiseAnExceptionIfWidthNotAPostiveInteger ()  {
        String str = Image2Ascii.convertImage2String(getTestResourceFullPath("snowman.png"), -1, 150);
    }
    @Test (expected = IllegalArgumentException.class)
    public void souldRaiseAnExceptionIfHeightisZero () {
        String str = Image2Ascii.convertImage2String(getTestResourceFullPath("snowman.png"), 100, 0);
    }
    @Test (expected = IllegalArgumentException.class)
    public void souldRaiseAnExceptionIfHeightNotAPostiveInteger ()  {
        String str = Image2Ascii.convertImage2String(getTestResourceFullPath("snowman.png"), 100, -1);
    }

    /**
     * Utility methods for the tests
     */
    private boolean test (String testAvitarFilename, String expectedResultFilename) {
        String actualResult = null;
        String expectedResult = null;
        try {
            actualResult = Image2Ascii.convertImage2String (testAvitarFilename, 150, 100);
            expectedResult =  new String(Files.readAllBytes(Paths.get(expectedResultFilename))).replace("\r", "");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if (expectedResult.equals (actualResult)) {
            return true;
        }
        else {
            return false;
        }
    }

    private String getTestResourceFullPath(String resource)  {
        String path = null;
        URL testResource = this.getClass().getClassLoader().getResource(resource);
        try {
            path = Paths.get(testResource.toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return path;
    }
}