package com.mir.codingexercise;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Authored by Mir Derakhshan on 13/09/2016.
 *
 * This class provides the ability to convert an image (such as an avitar) to an ascii character string that can then be
 * displayed in a text based application
 *
 */
public class Image2Ascii {
    // This map table will be used to map a pixel RGB value to an ascii character
    private static final Rgb2AsciiMapping MAPPING = new Rgb2AsciiMapping (
            new LinkedHashMap<Integer, Character>() {
                {
                    put(240, ' ');  // case when rgbValue >= 240
                    put(215, '.');  // case when 215 <= rgbValue < 240
                    put(190, ':');  // case when 190 <= rgbValue < 215
                    put(165, '-');  // etc.
                    put(140, '=');  // etc.
                    put(115, '+');
                    put(95, '.');
                    put(70, '#');
                    put(45, '%');
                }
            },
            '@' // the default char
            );

    /**
     * Converts a given image file to an ascii character string
     *
     * @param imageFilename the image filename to be converted
     * @param width the width of the ascii image
     * @param height the height of the ascii image
     * @return the ascii string representation of the image
     * @throws IllegalArgumentException raised when validation on the input parameters fails
     */
    public static String convertImage2String (String imageFilename, int width, int height)  {
        if (imageFilename == null || imageFilename.length() == 0) {
            throw new IllegalArgumentException("image filename parameter is an empty string.");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("width and height must be non-zero positive integers.");
        }

        BufferedImage img = null;
        try {
            img = ImageIO.read (new File (imageFilename));
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        if (img == null) {
            throw new IllegalArgumentException("File is not an image!");
        }
        return convertImage2String(img, width, height);
    }

    /**
     * This is the main method that does the work. It converts an instance of a class BufferedImage to an ascii string
     * representation of the given width and height.
     *
     * @param img the image to convert to a
     * @param width the width of the ascii image representation
     * @param height the height  of the ascii image representation
     * @return the ascii string representation of the image
     * @throws IllegalArgumentException when validation on the input parameters fails
     */
    private static String convertImage2String (BufferedImage img, int width, int height)  {
        if (img == null ||width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid argument(s).");
        }

        // resize the image to the given width and height
        BufferedImage resizedImage = resize (img, width, height);

        // convert the resized image to a string of ascii characters
        StringBuilder asciiImage = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {
                Color pixcol = new Color (resizedImage.getRGB(x, y));
                char ascii = rgbValueToAscii(pixcol.getRed(), pixcol.getGreen(), pixcol.getBlue());
                sb.append(ascii);
            }
            if (sb.toString().trim().isEmpty()) {
                continue;
            }
            else {
                asciiImage.append (sb);
                if (y < height - 1)
                    asciiImage.append("\n");
            }
        }
        return asciiImage.toString();
    }

    /**
     * Resizes an image to the given absolute width and height whilst ensuring that the aspect ratio is maintained
     *
     * @param origImg Path of the original image
     * @param newWidth absolute width in pixels
     * @param newHeight absolute height in pixels
     * @return BufferedImage object for the resized image
     */
    public static BufferedImage resize (BufferedImage origImg, int newWidth, int newHeight) {
        // create resized image object of the given width and height
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, origImg.getType());

        // scale the input image to the output image, maintaining the aspect ratio
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(origImg, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return resizedImage;
    }

    /**
     * Maps a given a RGB triple to an Ascii character using the mapping table
     *
     * @param red the red integer component of the RGB
     * @param green the green integer component of the RGB
     * @param blue the blue integer component of the RGB
     * @return the corresponding ascii char found in the mapping table
     */
    public static char rgbValueToAscii(int red, int green, int blue) {
        // First convert a color RGB to a grayscale RGB value (this is using an alogorthhm recommended on the web)
        final int pixval = (int) ((red * 0.30) + (blue * 0.59) + (green * 0.11));

        // Map the grayscale RGB value to an ascii character using the map table
        char mappedChar = MAPPING.lookupMapTable (pixval);

        return mappedChar;
    }

}