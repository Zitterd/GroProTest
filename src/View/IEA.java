package View;
import java.io.FileNotFoundException;


public interface IEA 
{

	String kommentar[] = new String[10]; 
	
	public int[] einlesen(String pfad) throws FileNotFoundException;
	public void ausgeben(int siege1, int siege2, int[][] feld1, int[][] feld2);
	public void fehlerAusgabe();
	
	
}
