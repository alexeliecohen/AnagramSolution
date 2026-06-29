package com.ixiasoft.interview.anagram;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AnagramFinderTest {
    private static AnagramFinder af = new AnagramFinderImpl();

    @BeforeClass
    public static void setupAnagramFinder() throws IOException {
        URL dictionaryPath = Thread.currentThread().getContextClassLoader().getResource("dictionary.txt");
        LinkedList<String> dictionary = new LinkedList<>(FileUtils.readLines(new File(dictionaryPath.getPath()), "utf-8"));
        af.setDictionary(dictionary);
    }

    @Test
    public void testFindAnagrams() {
        List<String> result = new ArrayList<>(af.findAnagrams("mary"));

        Assert.assertEquals(11, result.size());

        Assert.assertTrue(result.contains("amyr"));
        Assert.assertTrue(result.contains("army"));
        Assert.assertTrue(result.contains("mary"));
        Assert.assertTrue(result.contains("mayr"));
        Assert.assertTrue(result.contains("myar"));
        Assert.assertTrue(result.contains("myra"));
        Assert.assertTrue(result.contains("ramy"));
        Assert.assertTrue(result.contains("raym"));
        Assert.assertTrue(result.contains("ryam"));
        Assert.assertTrue(result.contains("yarm"));
        Assert.assertTrue(result.contains("ymar"));
    }

    @Test
    public void testFindAnagramsWithWhiteSpaceAndCasing() {
        List<String> result = new ArrayList<>(af.findAnagrams(" Ra  y m "));

        Assert.assertEquals(11, result.size());

        Assert.assertTrue(result.contains("amyr"));
        Assert.assertTrue(result.contains("army"));
        Assert.assertTrue(result.contains("mary"));
        Assert.assertTrue(result.contains("mayr"));
        Assert.assertTrue(result.contains("myar"));
        Assert.assertTrue(result.contains("myra"));
        Assert.assertTrue(result.contains("ramy"));
        Assert.assertTrue(result.contains("raym"));
        Assert.assertTrue(result.contains("ryam"));
        Assert.assertTrue(result.contains("yarm"));
        Assert.assertTrue(result.contains("ymar"));
    }

    @Test
    public void testFindAnagramsCollisions(){
        List<String> result = new ArrayList<>(af.findAnagrams("adeinr"));

        Assert.assertEquals(51, result.size());
    }

    @Test
    public void testFindAnagramsCollisionsWithSpacingAndCasing(){
        List<String> result = new ArrayList<>(af.findAnagrams("INR  ade"));

        Assert.assertEquals(51, result.size());
    }

    @Test
    public void testFindAnagramInvalidInput(){
        List<String> result = new ArrayList<>(af.findAnagrams("."));

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAnagramNullInput(){
        List<String> result = new ArrayList<>(af.findAnagrams(null));

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAnagramNoAnagrams(){
        List<String> result = new ArrayList<>(af.findAnagrams("NoWayTheresAnAnagramForThis"));

        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testAnagramFinderNullBuild(){
        AnagramFinder finder = new AnagramFinderImpl();
        finder.setDictionary(null);

        Assert.assertTrue(finder.findAnagrams("ShouldBeAnEmptyList").isEmpty());
    }

    @Test
    public void testIsAnagramTrue(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertTrue(finder.isAnagram("hydroxydeoxycorticosterones", "hydroxydesoxycorticosterone"));
    }

    @Test
    public void testIsAnagramFalse(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertFalse(finder.isAnagram("sdafkljsdajksdfajhsafhspaiojhsdaf", "dfsgsddgghsdjgasgdfitysdfariyfvve"));
    }

    @Test
    public void testIsAnagramBothNull(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertTrue(finder.isAnagram(null, null));
    }

    @Test
    public void testIsAnagramOneNull(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertFalse(finder.isAnagram(null, "dfsgsddgghsdjgasgdfitysdfariyfvve"));
        Assert.assertFalse(finder.isAnagram("dfsgsddgghsdjgasgdfitysdfariyfvve", null));
    }

    @Test
    public void testIsAnagramEmptyStrings(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertTrue(finder.isAnagram("", ""));
    }

    @Test
    public void testIsAnagramOneEmptyString(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertFalse(finder.isAnagram("", "sdafkljsdajksdfajhsafhspaiojhsdaf"));
        Assert.assertFalse(finder.isAnagram("sdafkljsdajksdfajhsafhspaiojhsdaf", ""));
    }

    @Test
    public void testIsAnagramTrueIfCaseDoNotMatch(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertTrue(finder.isAnagram("hydroxYdeoxycorticOsterones", "HYDROXYdesoxycorticosterone"));
    }

    @Test
    public void testIsAnagramTrueIfStringContainsWhitespace(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertTrue(finder.isAnagram("  hydr oxydeoxy  corticoster ones", "hydroxydesoxy corticosterone "));
    }

    @Test
    public void testIsAnagramTrueIfCaseDoNotMatchAndContainsWhitespace(){
        AnagramFinder finder = new AnagramFinderImpl();

        Assert.assertTrue(finder.isAnagram("h  ydr oxYdeoxycorticOs terones  ", "  HYDRO XYdesoxyco rticos  teron  e  "));
    }

}
