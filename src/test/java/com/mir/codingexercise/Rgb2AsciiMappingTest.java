package com.mir.codingexercise;

import com.mir.codingexercise.Rgb2AsciiMapping;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Authored by Mir Derakhshan on 13/09/2016.
 */
public class Rgb2AsciiMappingTest {

    @Test(expected = IllegalArgumentException.class)
    public void ConstructorRgb2AsciiMapTableShouldRaiseAnExceptionIfMapTableIsNull () {
        Rgb2AsciiMapping mapTable = new Rgb2AsciiMapping(null, '@');
    }

    @Test
    public void givenAMapTableThenMethodLookupTableShouldReturnCorrectAsciiMapping () {
        Rgb2AsciiMapping mapTableTestData =  new Rgb2AsciiMapping(
                new LinkedHashMap<Integer, Character>() {
                    {
                        put(240, ' ');
                        put(215, '.');
                        put(190, ':');
                    }},
                '@');
        assertEquals(mapTableTestData.lookupMapTable(240), ' ');
        assertEquals(mapTableTestData.lookupMapTable(241), ' ');

        assertEquals(mapTableTestData.lookupMapTable(215), '.');
        assertEquals(mapTableTestData.lookupMapTable(239), '.');

        assertEquals(mapTableTestData.lookupMapTable(190), ':');
        assertEquals(mapTableTestData.lookupMapTable(214), ':');

        assertEquals(mapTableTestData.lookupMapTable(189), '@');
        assertEquals(mapTableTestData.lookupMapTable(0), '@');
        assertEquals(mapTableTestData.lookupMapTable(-1), '@');
        assertEquals(mapTableTestData.lookupMapTable(-240), '@');
    }


}