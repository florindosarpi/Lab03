package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	List<String> paroleInglesi = new LinkedList<>();
	List<String> paroleItaliane = new LinkedList<>();
	String selectedLanguage;
	List<RichWord> parole = new LinkedList<>();
	List<RichWord> paroleD = new LinkedList<>();
	
	public void loadDictionary(String language) {
		if(language != null)
		{
			if (language.equalsIgnoreCase("English")) {
				try {
					FileReader fr = new FileReader(".\\src\\main\\resources\\English.txt");
					BufferedReader br = new BufferedReader(fr);
					String word;
					while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati
						paroleInglesi.add(word);
					}
					br.close();
					} catch (IOException e){
					System.out.println("Errore nella lettura del file inglese: " + e.getMessage());
					} 
				}
				
				else if(language.equalsIgnoreCase("Italian")) {
					try {
						FileReader fr = new FileReader(".\\src\\main\\resources\\Italian.txt");
						BufferedReader br = new BufferedReader(fr);
						String word;
						while ((word = br.readLine()) != null) {
						// Aggiungere parola alla struttura dati
							paroleItaliane.add(word);
						}
						br.close();
						} catch (IOException e){
						System.out.println("Errore nella lettura del file italiano: " + e.getMessage());
						}
				}
			}
		else
		{
			System.out.println("Errore nella lettura dei file: language è null");
		}
		
		}
	
	public void setLanguage(String selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		for (String s : inputTextList) {
			s.toLowerCase().replaceAll("[\\\\?.,\\\\/#!$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\\\"]", "");
			s.toLowerCase().replaceAll("[/?]", "");
			if (this.selectedLanguage.equalsIgnoreCase("Italian")) {
				if(paroleItaliane.contains(s))
					parole.add(new RichWord(s, true));
				else 
					parole.add(new RichWord (s, false));
			}
			if (this.selectedLanguage.equalsIgnoreCase("English")) {
				if(paroleInglesi.contains(s))
					parole.add(new RichWord(s, true));
				else 
					parole.add(new RichWord (s, false));
			}
		}
		return parole;
		
		
	}
	
	public List<RichWord> spellCheckTextDichotomic (List<String> inputTextList){
		for (String s : inputTextList) {
			s.toLowerCase().replaceAll("[\\\\?.,\\\\/#!$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\\\"]", "");
			s.toLowerCase().replaceAll("[/?]", "");
			
			if (this.selectedLanguage.equalsIgnoreCase("Italian")) {
				
				int j = paroleItaliane.size()-1;
				int i = 0; int m; int pos = -1;
				do { 
					m = (i + j)/2;
					if(paroleItaliane.get(m).equalsIgnoreCase(s))  
						pos = m;
					else if (paroleItaliane.get(m).compareTo(s)<1)
						i = m + 1;
					else
						j = m - 1;
				    } while(i <= j && pos == -1);
				if (pos!=-1)
					paroleD.add(new RichWord(s,true));
				else 
					paroleD.add(new RichWord(s,false));
				
				
			}
			if (this.selectedLanguage.equalsIgnoreCase("English")) {
				int j = paroleInglesi.size()-1;
				int i = 0; int m; int pos = -1;
				do { 
					m = (i + j)/2;
					if(paroleInglesi.get(m).equalsIgnoreCase(s))  
						pos = m;
					else if (paroleInglesi.get(m).compareTo(s)<1)
						i = m + 1;
					else
						j = m - 1;
				    } while(i <= j && pos == -1);
				if (pos!=-1)
					paroleD.add(new RichWord(s,true));
				else 
					paroleD.add(new RichWord(s,false));
			}
			
		}
		
		return paroleD;
	}
	
	
	public String getWrongWords () {
		String ptemp = "";
		for (RichWord r : paroleD) {
			if (r.isCorretta()== false) {
				ptemp = ptemp + r.getParola() +"\n";
			}
		}
		return ptemp;
	}
	
	public int getErrors() {
		int itemp = 0;
		for (RichWord r : paroleD) {
			if (r.isCorretta()== false) {
				itemp = itemp + 1;
			}
		}
		return itemp;
	}
		
	}


