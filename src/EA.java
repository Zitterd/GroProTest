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
			
		//Eine Kommentarzeile muss vorhanden sein!
		try 
		{
			zeile = br.readLine();
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}			
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
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return feld;
	}

	
	
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

}
