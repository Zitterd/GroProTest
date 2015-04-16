package Model;

public class Modell implements IModell
{
	

	public int[][] zuege;
	public int zugzaehler=0; //Anzahl der gespielten Z�ge
	private boolean sieg1 = false;
	private boolean sieg2 = false;
	public int[][] erstersieg1;
	public int[][] erstersieg2;
	
	
	public Modell()
	{
		
	}
	
	/**
	 * Diese Methode erstellt ein Feld mit der gegebenen Startverteilung.
	 * @param feld �bergeben wird die Startverteilung
	 */
	public void erstelleFeld(int[] feld) 
	{			
		zuege = new int[90][feld.length];	//Maximal 90 zuege m�glich (9Reihen*10H�lzer)
		for(int i=0; i<feld.length; i++)
		{
			zuege[0][i]= feld[i];
		}
	}
	
	//Gibt die aktuelle Stellung aus
	/**
	 * Diese Methode gibt die aktuelle Spielstellung aus.
	 */
	public void ausgeben() 
	{
		
		System.out.println("Spielstellung:");
		for(int i=0; i<zuege[0].length; i++)
		{
			System.out.print("Reihe " + i + ": ");
			for(int j=0; j<zuege[zugzaehler][i];j++)
			{	
				System.out.print("| ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	
	
	//Gibt true zur�ck falls Spiel aus ist und alle Feldinhalte gleich 0 sind
	/**
	 * Diese Methode pr�ft ob das Spiel zuende ist, also ob alle Feldinhalte des Arrays
	 * gleich 0 sind.
	 * @return boolean True wenn das Spiel aus ist. 
	 */
	public boolean spielende() 
	{	
		for(int i=0; i<zuege[0].length; i++)
		{
			if(zuege[zugzaehler][i] != 0)
			{
				return false;
			}
		}	
		return true;
	}
	
	//zug wird notiert
	/**
	 * Diese Methode notiert den letzt gespielten Zug, um alle Z�ge zu speichern in einem Spiel.
	 * @param zug Int Array f�r den gespielten Zug
	 */
	public void erhalteZug(int[] zug) 
	{
		zugzaehler++;
		for(int i=0; i<zuege[0].length;i++)
		{
			zuege[zugzaehler][i] = zug[i];
		}

	}
	
	
	//Beispielsieg von Spieler 1
	/**
	 * Enth�lt den ersten Sieg von Spieler 1 wenn vorhanden.
	 * @return Zweidimensionales Array mit den gespeicherten Z�gen.
	 */
	public int[][] getfeld1()
	{
		return erstersieg1;
	}
	
	
	//Beispielsieg von Spieler 2
	/**
	 * Enth�lt den ersten Sieg von Spieler 2 wenn vorhanden.
	 * @return Zweidimensionales Array mit den gespeicherten Z�gen.
	 */
	public int[][] getfeld2()
	{
		return erstersieg2;
	}


	//Aktuelle Verteilung
	/**
	 * Gibt die aktuelle Spielststellung zur�ck.
	 * @return rueckgabe Eindimensionales Array f�r das gegebene Spielfeld.
	 */
	public int[] erhalteStellung() 
	{		
		int rueckgabe[] = new int[zuege[0].length];
		
		for(int i=0; i<rueckgabe.length; i++)
		{
			rueckgabe[i] = zuege[zugzaehler][i];
		}
		
		return rueckgabe;
	}
	
	
	//Feld zuege auf Startverteilung resetten
	/**
	 * Resettet das aktuelle Spiel auf die Startverteilung.
	 */
	public void reset()
	{
		if(zugzaehler%2==0)
		{
			//gerade Anzahl von Z�gen -> Spieler 2 hat gewonnen, hatte er schon mal gewonnen?
			if(sieg2 == false)
			{
				sieg2 = true;	
				erstersieg2 = new int[zugzaehler+1][zuege[0].length];
				for(int j=0; j<zugzaehler;j++)
				{
					for(int k=0; k<zuege[0].length; k++)
					{
						erstersieg2[j][k] = zuege[j][k];
					}
				}
			}
		}else
		{
			if(sieg1 == false)
			{
				sieg1 = true;
				erstersieg1 = new int[zugzaehler+1][zuege[0].length];
				for(int j=0; j<zugzaehler;j++)
				{
					for(int k=0; k<zuege[0].length; k++)
					{
						erstersieg1[j][k] = zuege[j][k];
						
					}
				}
			}
		}		
		//zuege zur�cksetzen
		for(int i=1; i<zugzaehler ; i++)
		{	
			for(int j=0; j<zuege[0].length ; j++)
			{	
				zuege[i][j] = 0; //Feld zur�ck auf null stellen
			}
		}
		zugzaehler =0;	
	}
}
