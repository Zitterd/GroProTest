package Model;

public interface IModell 
{
	public void erstelleFeld(int[] feld);
	public void ausgeben();
	public boolean spielende();
	public void erhalteZug(int[] zug);
	public int[] erhalteStellung();
	public void reset();
	public int[][] getfeld1();
	public int[][] getfeld2();
}
