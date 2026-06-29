package com.ixiasoft.interview.anagram;

import java.util.*;


/**
 * 1. set the dictionary be a map of set,
 * 2. define the key to be based on the alphabetical ordering of the inserted string, since all anagrams are the same
 * string with characters arranged in a different order
 * 3. We will have created a form of index for fast retrieval
 **/


public class AnagramFinderImpl implements AnagramFinder {
    private final Map<String, Set<String>> anagramDict = new HashMap<>();

    private String formatString(String entry) {
        return entry.toLowerCase().replaceAll("\\s+", "");
    }

    private String createDictKeyFromEntry(String entry) {
        char[] s = formatString(entry).toCharArray();
        Arrays.sort(s);
        return new String(s);
    }

    @Override
    public void setDictionary(Collection<String> dictionary) {
        if (dictionary == null) {
            return;
        }

        for (String entry : dictionary) {
            String formattedEntry = formatString(entry);
            String dictKeyFromEntry = createDictKeyFromEntry(entry);
            if (!anagramDict.containsKey(dictKeyFromEntry)) {
                anagramDict.put(dictKeyFromEntry, new HashSet<>(Collections.singleton(formattedEntry)));
            } else {
                anagramDict.get(dictKeyFromEntry).add(formattedEntry);
            }
        }
    }

    @Override
    public Collection<String> findAnagrams(String word) {
        Set<String> anagramListResult =
                word == null
                        ? null
                        : anagramDict.get(createDictKeyFromEntry(word));
        return anagramListResult == null ? Collections.<String>emptySet() : anagramListResult;
    }


    @Override
    public boolean isAnagram(String firstWord, String secondWord) {
        //We dont need a map for checking for equality, simply sort alphabetically
        return firstWord == null || secondWord == null
                ? Objects.equals(firstWord,secondWord)
                : createDictKeyFromEntry(firstWord).equals(createDictKeyFromEntry(secondWord));
    }
}
