package maze;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

public class Solver {
	
	private Board board;
	private Cell end;
	private ArrayList<Cell> toVisit;
	private Stack<Cell> path;

	public Solver(Board board, Cell end) {
		this.board = board;
		this.end = end;
		path = new Stack<Cell>();
		path.push(board.getCell(0, 0));
		toVisit = board.getCells();
		toVisit.remove(path.lastElement());
		
		path.lastElement().setColor(Color.BLUE);
		end.setColor(Color.BLUE);
	}
	
	public boolean done() {
		return path.lastElement().equals(end);
	}
	
	public void step() {
		ArrayList<Cell> paths = getPaths(path.lastElement());
		if(paths.isEmpty()) {
			path.pop().setColor(Color.GRAY);
			return;
		}
		else if(paths.size() == 1) {
			setCurrent(paths.get(0));
		}
		
		Cell next = null;
		int minDistance = end.getX() + end.getY();
		for(Cell c: paths) {
			if(Manhattan(c) < minDistance) {
				next = c;
			}
		}
		setCurrent(next);
		
	}
	
	private ArrayList<Cell> getPaths(Cell c){
		ArrayList<Cell> result = new ArrayList<Cell>();
		boolean[] walls = c.wall();
		for(int i = 0; i < board.NEIGHBORS.length; i++) {
			if(!walls[i]) {
				Cell temp = board.getCell(c.getX() + board.NEIGHBORS[i][0], c.getY() + board.NEIGHBORS[i][1]);
				if(toVisit.contains(temp)) {
					result.add(temp);
				}
			}
		}
		return result;
	}
	
	private int Manhattan(Cell c) {
		return ((int) Math.abs(end.getX() - c.getX()) + (int) Math.abs(end.getY() - c.getY()));
	}
	
	private void setCurrent(Cell c) {
		path.lastElement().setColor(Color.GREEN);
		c.setColor(Color.BLUE);
		path.push(c);
		toVisit.remove(c);
		
		
	}

}
