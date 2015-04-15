
interface IControl 
{
	
	IEA einAus = new EA();
	Modell modell = new Modell();
	AlgoSp1 sp1 = new AlgoSp1();
	AlgoSp2 sp2 = new AlgoSp2();
	
	public void berechneZug();
	
}
