package com.lm.test.core.service.Impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEncoder implements Callable<String> {
	private String text;
	private Codifications code;

	private String toBinary() {
		StringBuilder result = new StringBuilder();
		for (char ch : this.text.toCharArray()) {
			result.append(Integer.toBinaryString(ch)).append(" ");
		}
		result.append(" (Binario)");
		return result.toString();
	}

	
	private String toMorse() {
		StringBuilder result = new StringBuilder();
		text = text.toUpperCase();
		for (int i = 0; i < text.length(); i++) {
			String v = String.valueOf(text.substring(i, i + 1));
			result.append(morseCode.getOrDefault(v, v));
		}
		result.append(" (Morse)");
		return result.toString();
	}

		private static Map<String, String> morseCode  = new HashMap<>();


	
	private static Map<String, String> batCode = new HashMap<>();
	private static char[] letters = { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	private static String[] morseLetters = { "    ", 
			". ___", "___ . . .", "___ . ___ .", "___ . .", ".", ". . ___ .",
			"___ ___ .", ". . . .", ". .", ". ___ ___ ___", "___ . ___", ". ___ . .", "___ ___", "___ .", "___ ___ ___",
			". ___ ___ .", "___ ___ . ___", ". ___ .", ". . .", "_", ". . ___", ". . . ___", ". ___ ___", "___ . . ___",
			"___ . ___ ___", "___ ___ . .", ". ___ ___ ___ ___", ". . ___ ___ ___", ". . . ___ ___", ". . . . ___",
			". . . . .", "___ . . . .", "___ ___ . . .", "___ ___ ___ . .", "___ ___ ___ ___ .",
			"___ ___ ___ ___ ___" };
	{
		batCode.put("M", "0");
		batCode.put("U", "1");
		batCode.put("R", "2");
		batCode.put("C", "3");
		batCode.put("I", "4");
		batCode.put("E", "5");
		batCode.put("L", "6");
		batCode.put("A", "7");
		batCode.put("G", "8");
		batCode.put("O", "9");
	    morseCode.put("A", ".-");
	    morseCode.put("B", "-...");
	    morseCode.put("C", "-.-.");
	    morseCode.put("CH", "----");
	    morseCode.put("D", "-..");
	    morseCode.put("E", ".");
	    morseCode.put("F", "..-.");
	    morseCode.put("G", "--.");
	    morseCode.put("H", "....");
	    morseCode.put("I", "..");
	    morseCode.put("J", ".---");
	    morseCode.put("K", "-.-");
	    morseCode.put("L", ".-..");
	    morseCode.put("M", "--");
	    morseCode.put("N", "-.");
	    morseCode.put("Ñ", "--.--");
	    morseCode.put("O", "---");
	    morseCode.put("P", ".--.");
	    morseCode.put("Q", "--.-");
	    morseCode.put("R", ".-.");
	    morseCode.put("S", "...");
	    morseCode.put("T", "-");
	    morseCode.put("U", "..-");
	    morseCode.put("V", "...-");
	    morseCode.put("W", ".--");
	    morseCode.put("X", "-..-");
	    morseCode.put("Y", "-.--");
	    morseCode.put("Z", "--..");
	}

	private String toBat() {
		StringBuilder result = new StringBuilder();
		text = text.toUpperCase();
		for (int i = 0; i < text.length(); i++) {
			String v = String.valueOf(text.substring(i, i + 1));
			result.append(batCode.getOrDefault(v, v));
		}
		result.append(" (Murciélago)");
		return result.toString();
	}

	@Override
	public String call() throws Exception {
		String result = "";
		switch (code) {
		case BAT:
			result = this.toBat();
			break;
		case BINARY:
			result = this.toBinary();
			break;
		case MORSE:
			result = this.toMorse();
			break;
		default:
			result = text;
			break;
		}
		return result;
	}
}
