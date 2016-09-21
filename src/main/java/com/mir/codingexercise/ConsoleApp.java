package com.mir.codingexercise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

/**
 *
 * Authored by Mir Derakhshan on 13/09/2016.
 */
public class ConsoleApp {

    public static void main (String[] args) {
        String avitarFilename = null;
        BufferedImage avitar = null;

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("\nEnter avitar filename, or type 'q' to quit: ");
            avitarFilename = in.nextLine().trim();
            if ("q".equals(avitarFilename.toLowerCase())) {
                System.exit (0);
            }
            try {
                String avitarInAscii = Image2Ascii.convertImage2String (avitarFilename, 150, 100);
                System.out.println(avitarInAscii);
                break;
            } catch (Exception e) {
                System.out.println("Sorry, " + e.getMessage() + " Please try again. ");
            }
        }
    }
}
