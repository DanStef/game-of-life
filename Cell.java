import java.util.ArrayList;
import java.util.Random;

public class Cell {
	int i;
	int j;
	boolean val;
	ArrayList<Cell> vecini = new ArrayList<>();
	
	
	public Cell(int i, int j) {
		super();
		this.i = i;
		this.j = j;
		Random r = new Random();
		val = r.nextBoolean();
	}
	
}
