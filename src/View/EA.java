package View;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class EA implements IEA 
{

	String[] epfad;
	
	public EA()
	{
		
	}
	
	/**
	 * Liest eine Datei ein und überprüft diese auf Korrektheit. Sollte die einzulesene Datei
	 * korrekt sein, wird ein Array mit der gegebenen Startverteilung zurückgegeben.
	 * @param pfad Der Dateiname muss übergebene werden
	 * @return Eindimensionales Array als Startverteilung
	 * @throws FileNotFoundException falls Datei nicht gefunden werden konnte
	 */
	public int[] einlesen(String pfad) throws FileNotFoundException 
	{
		String zeile = "";
		int feld[];	
		FileReader fr = new FileReader(pfad);
		BufferedReader br = new BufferedReader(fr);
		int j = -1;
		int k=0;
		
		epfad = pfad.split("\\.");
		epfad[0] += ".out";
					
		//Prüfung ob Datei korrekt?
			
		try 
		{
			zeile = br.readLine();
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}		
		//Eine Kommentarzeile muss vorhanden sein!
		if(zeile.charAt(0)!='#')
		{
			try{
				fr.close();
				br.close();
			}catch(IOException a)
			{
				
			}
			throw new FileNotFoundException("Kommentarzeile fehlt");
		}	
		try{
		do
		{
			j++;			
			kommentar[j] = zeile;		
			try 
			{
				zeile = br.readLine();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}while(zeile.charAt(0) == '#');
		}catch(NullPointerException e)
		{
			//Zweite Zeile ist null
			try {
				fr.close();
				br.close();
			} catch (IOException f) {
				
				f.printStackTrace();
			}
			throw new FileNotFoundException("Ziffernzeile fehlt");
		}

		//Prüfen ob Ziffernzeile korrekt:
		for(int i=0; i<zeile.length(); i++)
		{
			if(i>17 && zeile.charAt(i) != ' ')
			{
				//Zu viele Reihen
				try {
					fr.close();
					br.close();						
				} catch (IOException b) {
					b.printStackTrace();
				}
				throw new FileNotFoundException("Ziffernzeile-Format nicht eingehalten");
			}
			else if(i%2==0)
			{
				//Zeichen muss Ziffer sein
				if(zeile.charAt(i)-48 <1 || zeile.charAt(i)-48 >9)
				{
					try {
						fr.close();
						br.close();						
					} catch (IOException b) {
						b.printStackTrace();
					}
					throw new FileNotFoundException("Ziffernzeile-Format nicht eingehalten");
				}
			}else
			{
				//Zeichen muss Leerzeichen sein
				if(zeile.charAt(i) != ' ')
				{
					try{
						fr.close();
						br.close();
					}catch(Exception a)
					{
						
					}
					throw new FileNotFoundException("Ziffernzeile-Format nicht eingehalten");
				}
			}
		}
		
		//Zeile enthält nun die Zeile mit den Zahlenwerten
		feld = new int[(zeile.length()/2)+1];		
		for(int i=0; i<zeile.length();i+=2)
		{
			feld[k] = zeile.charAt(i)-48;
			k++;
		}	
		
		//BufferdReader schließen
		try 
		{
			br.close();
			fr.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return feld;
	}

	
	/**
	 * Diese Methode erstellt die Ausgabedatei
	 * @param siege1 Anzahl der Siege von Spieler 1
	 * 		  siege2 Anzahl der Siege von Spieler 2
	 *        feld1  Beispiel-Spiel für ein gewonnenes Spiel von Spieler 1
	 *        feld2  Beispiel-Spiel für ein gewonnenes Spiel von Spieler 2 
	 */
	public void ausgeben(int siege1, int siege2, int[][] feld1, int[][] feld2) 
	{
		Writer fw=null;
		try{
			fw = new FileWriter(epfad[0]);
			
			//Kommentare einfügen
			for(int i=0; i<kommentar.length; i++)
			{
				if(kommentar[i]!=null && kommentar[i].charAt(0)=='#')
				{
					fw.write(kommentar[i] + "\n");
				}
			}
			//Startverteilung ausgeben
			fw.write("Startverteilung: (");
			for(int i=0; i<feld1[0].length; i++)
			{
				if(i!=0)
				{
					fw.write(",");
				}
				fw.write(""+feld1[0][i]);
			}
			fw.write(")"+"\n");
			
			//Gewonnene und verlorene Spiele eintragen
			fw.write("Gewonnene Spiele Spieler 1: " + siege1*10 + "%" + "\n");
			fw.write("Gewonnene Spiele Spieler 2: " + siege2*10 + "%" + "\n");
			
			fw.write("Beispiel eines von Spieler 1 gewonnenen Spiels:" + "\n");
			if(siege1 != 0)
			{
				for(int i=1; i<feld1.length;i++) //länge richtig?
				{
					fw.write("Zug " + i +", ");
					if(i%2==1)
					{
						fw.write("Spieler 1 : ");
					}else
					{
						fw.write("Spieler 2 : ");
					}
					fw.write("(");
					for(int j=0; j<feld1[0].length; j++)
					{
						if(j!=0)
						{
							fw.write(",");
						}
						fw.write(""+feld1[i-1][j]);
					}
					fw.write(") -> (");
					for(int j=0; j<feld1[0].length; j++)
					{
						if(j!=0)
						{
							fw.write(",");
						}
						fw.write(""+feld1[i][j]);
					}
					fw.write(")" +"\n");
				}
			}else
			{
				fw.write("Nicht vorhanden" + "\n");
			}
			
			fw.write("Beispiel eines von Spieler 2 gewonnenen Spiels:" + "\n");
			if(siege2 != 0)
			{
				for(int i=1; i<feld2.length;i++) //länge richtig?
				{
					fw.write("Zug " + i +", ");
					if(i%2==1)
					{
						fw.write("Spieler 1 : ");
					}else
					{
						fw.write("Spieler 2 : ");
					}
					fw.write("(");
					for(int j=0; j<feld2[0].length; j++)
					{
						if(j!=0)
						{
							fw.write(",");
						}
						fw.write(""+feld2[i-1][j]);
					}
					fw.write(") -> (");
					for(int j=0; j<feld2[0].length; j++)
					{
						if(j!=0)
						{
							fw.write(",");
						}
						fw.write(""+feld2[i][j]);
					}
					fw.write(")" +"\n");
				}
			}else
			{
				fw.write("Nicht vorhanden" + "\n");
			}
			
		}catch(IOException e)
		{
			System.out.println("Datei konnte nicht erstellt werden");
		}
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Schreibt eine Fehlermeldung in die Ausgabedatei falls die Eingabedatei formal nicht 
	 * korrekt ist.
	 */
	public void fehlerAusgabe()
	{
		Writer fw=null;
		try{
			fw = new FileWriter(epfad[0]);
			fw.write("Datei besteht aus einem falschen Format!");
		}catch(IOException e)
		{
			System.out.println("Datei konnte nicht erstellt werden");
		}
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
