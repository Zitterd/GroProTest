
public class AlgoSp2 implements IAlgo 
{

	public int[] zieheZug(int[] feld) 
	{
		int reihe = 15;
		int anzahl;
		int rueckgabe[] = new int[feld.length];
		
		do
		{
			reihe = (int)(Math.random()*10);   //Zufällig Reihe 1-9 auswählen in welcher Hölzer liegen
			
		}while(reihe>=feld.length || feld[reihe] == 0);
		
		do{
			anzahl = (int) (Math.random()*10) +1;	//Zufällig eine Anzahl an Hölzern entfernen
		}while(feld[reihe] < anzahl);
		
		for(int i=0; i<feld.length; i++)
		{
			rueckgabe[i] = feld[i];
		}
		rueckgabe[reihe] = feld[reihe]-anzahl;
		return rueckgabe;
	}

}
