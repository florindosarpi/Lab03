package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> paroleInglesi = new LinkedList<>();
	List<String> paroleItaliane = new LinkedList<>();
	
	public void loadDictionary(String language) {
		if (language.equalsIgnoreCase("English")) {
			try {
				FileReader fr = new FileReader("English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
				// Aggiungere parola alla struttura dati
					paroleInglesi.add(word);
				}
				br.close();
				} catch (IOException e){
				System.out.println("Errore nella lettura del file");
				} 
			}
			
			else if(language.equalsIgnoreCase("Italian")) {
				try {
					FileReader fr = new FileReader("Italian.txt");
					BufferedReader br = new BufferedReader(fr);
					String word;
					while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati
						paroleItaliane.add(word);
					}
					br.close();
					} catch (IOException e){
					System.out.println("Errore nella lettura del file");
					}
			}
		}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		for (String s : inputTextList) {
			s.toLowerCase().replaceAll("[.,\\\\/#!$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", "");
			
		}
	}
		
	}


