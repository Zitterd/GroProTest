package Control;

import Model.AlgoSp1;
import Model.AlgoSp2;
import Model.IAlgo;
import Model.IModell;
import Model.Modell;
import View.EA;
import View.IEA;

public interface IControl 
{
	
	IEA einAus = new EA();
	IModell modell = new Modell();
	IAlgo sp1 = new AlgoSp1();
	IAlgo sp2 = new AlgoSp2();
	
	public void berechneZug();
	
}
