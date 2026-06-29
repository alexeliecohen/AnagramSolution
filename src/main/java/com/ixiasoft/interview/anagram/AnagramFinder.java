package com.ixiasoft.interview.anagram;

import java.util.Collection;

/**
 * <h1>
 * 	AnagramFinder
 * </h1>
 * <p>
 * 	The AnagramFinder class, as the name implies, is used for finding anagrams.</br>
 * 	The dataset that will be used to create the "dictionary" contains ~1.6 million strings.
 * </p>
 */
public interface AnagramFinder {
	/**
	 * Initialize the dictionary.
	 * 
	 * @param dictionary contains the collection of strings that will be searched for 
	 * anagrams.
	 */
    void setDictionary(Collection<String> dictionary);
    /**
     * Should return the anagrams contained in the dictionary for the given word.
     * 
     * @param word The word we are looking up anagrams for
     * @return The list of all possible anagrams contained by the dataset defined in 
     * {@link AnagramFinder#setDictionary(Collection)}
     */
    Collection<String> findAnagrams(String word);
    /**
     * Whether two strings are anagrams.
     * 
     * @param firstWord 
     * @param secondWord
     * @return anagram-ness
     */
    boolean isAnagram(String firstWord, String secondWord);
}
