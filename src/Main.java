//Autor: Jonas Hamers
//Datum: 15.04.2015
//Aufgabe: Programmierung eines Softwaresystems, Spiel NIM


public class Main 
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String string[] = args[0].split("\\\\");
		Kontrolle kontrolle = new Kontrolle(string[string.length-2]+"\\"+string[string.length-1]);
		kontrolle.berechneZug();
	}

}
