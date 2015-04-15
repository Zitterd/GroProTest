import java.io.FileNotFoundException;


public class Kontrolle implements IControl 
{

	boolean zugspieler = true;				//true wenn Spieler 1 an der Reihe
	int siege1 =0;
	int siege2 =0;
	
	public Kontrolle(String pfad)
	{
		//Datei einlesen
		int feld[];
		try {
			feld = einAus.einlesen(pfad);
			modell.erstelleFeld(feld);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public void berechneZug() 
	{
		int zug[];
		
		modell.ausgeben(); // Startstellung ausgeben
		
		for(int k=0; k<10; k++)
		{	
			zugspieler = true;
			while(modell.spielende()==false)
			{
				if(zugspieler == true)
				{
					zugspieler = false;
					//Spieler 1 zieht
					zug = sp1.zieheZug(modell.erhalteStellung());	
					System.out.print(k+1 +".Runde"+ "Spieler1: ");
				}else
				{
					zugspieler = true;
					//Spieler 2 zieht
					zug = sp2.zieheZug(modell.erhalteStellung());
					System.out.print(k+1 +".Runde"+ "Spieler2: ");
				}
				modell.erhalteZug(zug);
				modell.ausgeben();
			}
			//Modell resetten -> Nächste Runde mit gleicher Startverteilung
			modell.reset();
			if(zugspieler==false)
			{
				siege1++;
			}else
			{
				siege2++;
			}
		}
		//Ausgabe 
		einAus.ausgeben(siege1, siege2, modell.getfeld1(), modell.getfeld2());
		
	}
	
}
