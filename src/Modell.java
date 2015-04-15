
public class Modell implements IModell
{
	

	public int[][] zuege;
	public int zugzaehler=0; //Anzahl der gespielten Züge
	private boolean sieg1 = false;
	private boolean sieg2 = false;
	public int[][] erstersieg1;
	public int[][] erstersieg2;
	
	
	public Modell()
	{
		
	}
	
	
	public void erstelleFeld(int[] feld) 
	{			
		zuege = new int[90][feld.length];	//Maximal 90 zuege möglich (9Reihen*10Hölzer)
		for(int i=0; i<feld.length; i++)
		{
			zuege[0][i]= feld[i];
		}
	}
	
	//Gibt die aktuelle Stellung aus
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
	
	
	
	//Gibt true zurück falls Spiel aus ist und alle Feldinhalte gleich 0 sind
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
	public void erhalteZug(int[] zug) 
	{
		zugzaehler++;
		for(int i=0; i<zuege[0].length;i++)
		{
			zuege[zugzaehler][i] = zug[i];
		}

	}
	
	
	//Beispielsieg von Spieler 1
	public int[][] getfeld1()
	{
		return erstersieg1;
	}
	
	
	//Beispielsieg von Spieler 2
	public int[][] getfeld2()
	{
		return erstersieg2;
	}


	//Aktuelle Verteilung
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
	public void reset()
	{
		if(zugzaehler%2==0)
		{
			//gerade Anzahl von Zügen -> Spieler 2 hat gewonnen, hatte er schon mal gewonnen?
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
		//zuege zurücksetzen
		for(int i=1; i<zugzaehler ; i++)
		{	
			for(int j=0; j<zuege[0].length ; j++)
			{	
				zuege[i][j] = 0; //Feld zurück auf null stellen
			}
		}
		zugzaehler =0;	
	}
}
