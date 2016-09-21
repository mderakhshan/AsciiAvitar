package com.mir.codingexercise;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Authored by Mir Derakhshan on 13/09/2016.
 *
 * This class is used to specify the mapping of an RGB value to an ascii character
 *
 */
public class Rgb2AsciiMapping {
    // The map table used for mapping an RGB value to an ascii character. Note that it is ordered.
    private LinkedHashMap<Integer, Character>  mapTable;
    // The default character used when no match is found in the map table
    private char defaultChar;

    public Rgb2AsciiMapping(LinkedHashMap<Integer, Character> map, char defaultChar) {
        if (map == null) {
            throw new IllegalArgumentException("Map table is null!");
        }
        this.mapTable = map;
        this.defaultChar = defaultChar;
    }

    public char lookupMapTable (int rgbValue) {
        char mappedChar = this.getDefaultChar();
        for (Integer key : this.getMapTable().keySet())  {
            if ((int) rgbValue >= key) {
                mappedChar = getMapTable().get (key);
                break;
            }
        }
        return mappedChar;
    }

    public Map<Integer, Character> getMapTable() {
        return mapTable;
    }

    public char getDefaultChar() {
        return defaultChar;
    }
}
