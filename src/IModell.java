
interface IModell 
{
	public void erstelleFeld(int[] feld);
	public void ausgeben();
	public boolean spielende();
	public void erhalteZug(int[] zug);
	public int[] erhalteStellung();
}
