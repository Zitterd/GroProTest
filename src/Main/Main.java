package Main;

//Autor: Jonas Hamers
//Datum: 15.04.2015
//Aufgabe: Programmierung eines Softwaresystems, Spiel NIM

import Control.Kontrolle;
import Control.IControl;

public class Main 
{
	
	/**
	 * Übergeben wird der Dateipfad einer ".in"-Datei. Diese Datei wird dem Konstruktor der
	 * Kontroll-Klasse übergeben. Anschließend wird die Simulation von 10 Spielen durchgeführt. 
	 * @param args Dateipfad wird übergeben
	 */
	public static void main(String[] args) 
	{
		String string[] = args[0].split("\\\\");
		IControl kontrolle = new Kontrolle(string[string.length-2]+"\\"+string[string.length-1]);
		kontrolle.berechneZug();
	}

}
