
public class AlgoSp2 implements IAlgo 
{

	public int[] zieheZug(int[] feld) 
	{
		int reihe = 15;
		int anzahl;
		int rueckgabe[] = new int[feld.length];
		
		do
		{
			reihe = (int)(Math.random()*10);   //Zuf�llig Reihe 1-9 ausw�hlen in welcher H�lzer liegen
			
		}while(reihe>=feld.length || feld[reihe] == 0);
		
		do{
			anzahl = (int) (Math.random()*10) +1;	//Zuf�llig eine Anzahl an H�lzern entfernen
		}while(feld[reihe] < anzahl);
		
		for(int i=0; i<feld.length; i++)
		{
			rueckgabe[i] = feld[i];
		}
		rueckgabe[reihe] = feld[reihe]-anzahl;
		return rueckgabe;
	}

}
