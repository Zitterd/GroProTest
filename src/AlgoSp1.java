
public class AlgoSp1 implements IAlgo 
{

	public int[] zieheZug(int[] feld) 
	{
		int rueckgabe[] = new int[feld.length];
		int anzahlBelegterReihen = 0;
		int anzahlHoelzer = 0;
		int reihe=0;							//reihe merken, welche geändert werden soll
		boolean einsZweiDreiX[] = new boolean[3]; //kommen die werte 1,2,3 drin vor? true = ja
		int hilfsfeld[] = new int[3];		//3:speichert die 3 belegten reihen ab, gespeichert werden die reihennummern
											//2:speichert die 2 belegten reihen ab ""				
			
		for(int i=0; i<feld.length; i++)
		{
			rueckgabe[i] = feld[i];
			if(feld[i] != 0)
			{
				anzahlBelegterReihen++;
				anzahlHoelzer += feld[i];
			}
		}
		
		//Spiel mit einer Reihe
		if(anzahlBelegterReihen == 1)
		{
			for(int i=0; i<feld.length; i++)
			{
				if(feld[i] != 0)
				{
					reihe = i;
					rueckgabe[reihe]=0;		//Alle Hölzer entnehmen
				}
			}
		//Spiel mit zwei Reihen
		}else if(anzahlBelegterReihen == 2)
		{
			int zaehler=0;
			//Reihennummern der Reihen merken, welche ungleich 0 sind
			for(int i=0; i<feld.length; i++)
			{
				if(feld[i] != 0)
				{
					hilfsfeld[zaehler]=i;
					zaehler++;
				}
			}
			
			//Sind beide Reihen gleich, eins entnehmen (schlechte Situation)
			if(feld[hilfsfeld[0]] == feld[hilfsfeld[1]] )
			{
				rueckgabe[hilfsfeld[0]] = feld[hilfsfeld[0]]-1;
			}else
			{
				//Sind die Reihen unterschiedlich, die größere gleich der kleineren setzen -> Sieg
				if(feld[hilfsfeld[0]] > feld[hilfsfeld[1]])
				{
					rueckgabe[hilfsfeld[0]] = feld[hilfsfeld[1]];
				}else
				{
					rueckgabe[hilfsfeld[1]] = feld[hilfsfeld[0]];
				}
			}
		//Spiel mit drei Reihen
		}else if(anzahlBelegterReihen == 3)
		{
			int zaehler=0;
			//Reihennummern der Reihen merken, welche ungleich 0 sind
			for(int i=0; i<feld.length; i++)
			{
				if(feld[i] != 0)
				{
					hilfsfeld[zaehler]=i;		// Feld mit 3 Werten wird erschaffen
					zaehler++;
				}
			}
			//Sind zwei Werte gleich, wird der anderen (dritten) Reihe, alle Hölzer entnommen -> Sieg
			if(feld[hilfsfeld[0]] == feld[hilfsfeld[1]] )
			{
				rueckgabe[hilfsfeld[2]]=0;
			}else if(feld[hilfsfeld[0]] == feld[hilfsfeld[2]])
			{
				rueckgabe[hilfsfeld[1]]=0;
			}else if(feld[hilfsfeld[2]] == feld[hilfsfeld[1]])
			{
				rueckgabe[hilfsfeld[0]]=0;
			}else
			{
				//keine (x,x,y)-Stellung vorhanden:
				//prüfen ob (3,2,1) Stellung möglich
				einsZweiDreiX[0]=false;
				einsZweiDreiX[1]=false;
				einsZweiDreiX[2]=false;
				
				for(int i=0; i<feld.length; i++)
				{
					if(feld[i] == 1 && einsZweiDreiX[0]==false && (einsZweiDreiX[1]==false || einsZweiDreiX[2]==false))
					{
						einsZweiDreiX[0]=true;
					}else if(feld[i] == 2 && einsZweiDreiX[1]==false && (einsZweiDreiX[0]==false || einsZweiDreiX[2]==false))
					{
						einsZweiDreiX[1]=true;
					}else if(feld[i] == 3 && einsZweiDreiX[2]==false && (einsZweiDreiX[0]==false || einsZweiDreiX[1]==false))
					{
						einsZweiDreiX[2]=true;
					}else
					{
						reihe = i;								//verbliebene Reihe merken
					}
				}
				//(1,2,x)-Stellung -> Andere Reihe auf 3 verkürzen
				if(einsZweiDreiX[0]==true && einsZweiDreiX[1]==true && feld[reihe]>3)
				{
					rueckgabe[reihe] = 3;
				//(1,3,x)-Stellung -> Andere Reihe auf 2 verkürzen
				}else if(einsZweiDreiX[0]==true && einsZweiDreiX[2]==true && feld[reihe]>2)
				{
					rueckgabe[reihe] = 2;
				//(2,3,x)-Stellung -> Andere Reihe auf 1 verkürzen
				}else if(einsZweiDreiX[1]==true && einsZweiDreiX[2]==true && feld[reihe]>1)
				{
					rueckgabe[reihe] = 1;
				}else
				{
					//Keine gewünschte Stellung möglich -> entweder eins oder zwei entnehmen
					//Wenn keine geeignete Stellung vorliegt, bei gerade Anzahl an Hölzern 2 entnehmen
					//sonst nur eins (um gerade Anzahl zu erreichen und um möglich viele Spielzüge zu spielen)
					do
					{
						reihe = (int)(Math.random()*10);   //Zufällig Reihe 1-9 auswählen
					}while(reihe>=feld.length || feld[reihe] == 0 || (feld[reihe]<2 && anzahlHoelzer%2==0));
					if(anzahlHoelzer%2 == 0)
					{
						rueckgabe[reihe] = feld[reihe]-2;
					}else
					{
						rueckgabe[reihe] = feld[reihe]-1;
					}
				}	
			}
		// Spiel mit 4 Reihen	
		}else if(anzahlBelegterReihen == 4)
		{
			einsZweiDreiX[0]=false;
			einsZweiDreiX[1]=false;
			einsZweiDreiX[2]=false;
			//(1,2,3,x)-Stellung vorhanden?
			for(int i=0; i<feld.length; i++)
			{
				if(feld[i] == 1 && einsZweiDreiX[0]==false)
				{
					einsZweiDreiX[0]=true;
				}else if(feld[i] == 2 && einsZweiDreiX[1]==false)
				{
					einsZweiDreiX[1]=true;
				}else if(feld[i] == 3 && einsZweiDreiX[2]==false)
				{
					einsZweiDreiX[2]=true;
				}else
				{
					reihe = i;								//verbliebene reihe merken
				}
			}
			if(einsZweiDreiX[0]==true && einsZweiDreiX[1]==true &&  einsZweiDreiX[2]==true )
			{
				//(1,2,3,x) Stellung vorhanden -> andere Reihe auf 0 setzen
				rueckgabe[reihe] = 0;
			}else
			{   
				//Keine gewünschte Stellung möglich -> entweder eins oder zwei entnehmen
				do
				{
					reihe = (int)(Math.random()*10);   //Zufällig Reihe 1-9 auswählen
				}while(reihe>=feld.length || feld[reihe] == 0 || (feld[reihe]<2 && anzahlHoelzer%2==0));
				if(anzahlHoelzer%2 == 0)
				{
					rueckgabe[reihe] = feld[reihe]-2;
				}else
				{
					rueckgabe[reihe] = feld[reihe]-1;
				}
			}
		}else
		//mehr als 5 reihen belegt, entweder eins oder zwei entnehmen
		{
			do
			{
				reihe = (int)(Math.random()*10);   //Zufällig Reihe 1-9 auswählen
			}while(reihe>=feld.length || feld[reihe] == 0 || (feld[reihe]<2 && anzahlHoelzer%2==0));
			if(anzahlHoelzer%2 == 0)
			{
				rueckgabe[reihe] = feld[reihe]-2;
			}else
			{
				rueckgabe[reihe] = feld[reihe]-1;
			}
		}
		
		return rueckgabe;
	}

}
