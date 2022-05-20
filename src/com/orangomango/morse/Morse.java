package com.orangomango.morse;

import java.util.*;

public class Morse {
	private static final long DOT = 200l;
	private static final long DASH = 3*DOT;
	private static final long SIGNAL_GAP = DOT;
	private static final long CHARACTER_GAP = 3*DOT;
	private static final long WORD_GAP = 7*DOT;
	private static final Map<Character, long[]> ALPHABET;
	
	static {
		ALPHABET = new HashMap<>();
		ALPHABET.put('A', new long[]{DOT, DASH});
		ALPHABET.put('B', new long[]{DASH, DOT, DOT, DOT});
		ALPHABET.put('C', new long[]{DASH, DOT, DASH, DOT});
		ALPHABET.put('D', new long[]{DASH, DOT, DOT});
		ALPHABET.put('E', new long[]{DOT});
		ALPHABET.put('F', new long[]{DOT, DOT, DASH ,DOT});
		ALPHABET.put('G', new long[]{DASH, DASH, DOT});
		ALPHABET.put('H', new long[]{DOT, DOT, DOT, DOT});
		ALPHABET.put('I', new long[]{DOT, DOT});
		ALPHABET.put('J', new long[]{DOT, DASH, DASH, DASH});
		ALPHABET.put('K', new long[]{DASH, DOT, DASH});
		ALPHABET.put('L', new long[]{DOT, DASH, DOT, DOT});
		ALPHABET.put('M', new long[]{DASH, DASH});
		ALPHABET.put('N', new long[]{DASH, DOT});
		ALPHABET.put('O', new long[]{DASH, DASH, DASH});
		ALPHABET.put('P', new long[]{DOT, DASH, DASH, DOT});
		ALPHABET.put('Q', new long[]{DASH, DASH, DOT, DASH});
		ALPHABET.put('R', new long[]{DOT, DASH, DOT});
		ALPHABET.put('S', new long[]{DOT, DOT, DOT});
		ALPHABET.put('T', new long[]{DASH});
		ALPHABET.put('U', new long[]{DOT, DOT, DASH});
		ALPHABET.put('V', new long[]{DOT, DOT, DOT, DASH});
		ALPHABET.put('W', new long[]{DOT, DASH, DASH});
		ALPHABET.put('X', new long[]{DASH, DOT, DOT, DASH});
		ALPHABET.put('Y', new long[]{DASH, DOT, DASH, DASH});
		ALPHABET.put('Z', new long[]{DASH, DASH, DOT, DOT});
		ALPHABET.put('1', new long[]{DOT, DASH, DASH, DASH, DASH});
		ALPHABET.put('2', new long[]{DOT, DOT, DASH, DASH, DASH});
		ALPHABET.put('3', new long[]{DOT, DOT, DOT, DASH, DASH});
		ALPHABET.put('4', new long[]{DOT, DOT, DOT, DOT, DASH});
		ALPHABET.put('5', new long[]{DOT, DOT, DOT, DOT, DOT});
		ALPHABET.put('6', new long[]{DASH, DOT, DOT, DOT, DOT});
		ALPHABET.put('7', new long[]{DASH, DASH, DOT, DOT, DOT});
		ALPHABET.put('8', new long[]{DASH, DASH, DASH, DOT, DOT});
		ALPHABET.put('9', new long[]{DASH, DASH, DASH, DASH, DOT});
		ALPHABET.put('0', new long[]{DASH, DASH, DASH, DASH, DASH});
	}
	
	public static long[] stringToMorse(String word){
		String[] words = word.toUpperCase().split(" ");
		List<Long> collector = new ArrayList<>();
		for (int i = 0; i < words.length; i++){
			appendMorseWord(words[i], collector);
			if (i != words.length-1){
				collector.add(WORD_GAP);
			}
		}
		return listToLongArray(collector);
	}
	
	private static void appendMorseWord(String word, List<Long> collector){
		char[] characters = word.toCharArray();
		for (int i = 0; i < characters.length; i++){
			long[] morse = ALPHABET.get(characters[i]);
			if (morse == null){
				throw new IllegalArgumentException("Not a morse character: "+characters[i]);
			}
			appendMorseCharacter(morse, collector);
			if (i != word.length()-1){
				collector.add(CHARACTER_GAP);
			}
		}
	}
	
	private static void appendMorseCharacter(long[] morse, List<Long> collector){
		for (int i = 0; i < morse.length; i++){
			long time = morse[i];
			collector.add(time);
			if (i != morse.length-1){
				collector.add(SIGNAL_GAP);
			}
		}
	}
	
	private static long[] listToLongArray(List<Long> collector){
		long[] out = new long[collector.size()+1];
		int index = 0;
		out[index++] = 0;
		for (Long l : collector){
			out[index++] = l;
		}
		return out;
	}
}
