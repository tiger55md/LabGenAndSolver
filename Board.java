package maze;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
	
	public static final int[][] NEIGHBORS = {{0,-1},{0,1},{-1,0},{1,0}}; // TOP WALL, DOWN WALL, LEFT WALL, RIGHT WALL
	
	private int size;
	private Cell[][] cells;

	public Board(int size) {
		this.size = size;
		cells = new Cell[size][size];
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i,j);
			}
		}
	}
	
	public ArrayList<Cell> getCells(){
		ArrayList<Cell> cells1 = new ArrayList<Cell>();
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells1.add(cells[i][j]);
			}
		}
		return cells1;
	}
	
	public Cell getCell(int x, int y) {
		if( !(0 <= x && x < size ) || !(0 <= y && y < size)) {
			return null;
		}
		return cells[x][y];
	}
	
	protected Cell getRandomNeighor(Cell cell) {
		Cell randomCell = null;
		while( randomCell == null) {
			int randomNeigh = new Random().nextInt(NEIGHBORS.length);
			int rx = (int) (cell.getX() + NEIGHBORS[randomNeigh][0]);
			int ry = (int) (cell.getY() + NEIGHBORS[randomNeigh][1]);
			randomCell = getCell(rx,ry);
		}
		return randomCell;
	}
	
	protected void deleteWall(Cell a, Cell b) {
		int[] delta = {b.getX() - a.getX(), b.getY() - a.getY()};
		int[] invDelta = {-(b.getX() - a.getX()), -(b.getY() - a.getY())};
		for (int i = 0; i < NEIGHBORS.length; i++) {
			if ( Arrays.equals(NEIGHBORS[i], delta) ) {
				a.deleteWall(i);
			} else if( Arrays.equals(NEIGHBORS[i], invDelta)){
				b.deleteWall(i);
			}
		}
	}
	
	protected void render(Graphics2D g, int MazeSize) {
		int cellSize = MazeSize / size;
		
		for(int i = 0; i < cells.length; i++) {
			for(int j = 0; j < cells[i].length; j++) {
				cells[i][j].render(g, i*cellSize, j*cellSize, cellSize);
			}
		}
	}
	
	

}
