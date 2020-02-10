package maze;

import java.awt.Color;
import java.util.ArrayList;

public class Generator {
	private Board board;
	private Cell current;
	private ArrayList<Cell> toVisit;

	public Generator(int size) {
		board = new Board(size);
		toVisit = board.getCells();
		current = board.getCell(0, 0);
		toVisit.remove(current);
	}
	
	private void setCurr(Cell next) {
		current.setColor(Color.BLACK);
		toVisit.remove(next);
		Color c = toVisit.isEmpty() ? Color.BLACK : Color.GREEN;
		next.setColor(c);
		current = next;
	}
	
	public boolean done() {
		return toVisit.isEmpty();
	}
	
	public void step() {
		
		Cell next = board.getRandomNeighor(current);
		if( toVisit.contains(next)) {
			board.deleteWall(current,next);
		}
		setCurr(next);
	}
	
	public Board getBoard() {
		return board;
	}

}
