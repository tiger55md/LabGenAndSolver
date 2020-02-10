package maze;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cell {
	private int x;
	private int y;
	private boolean[] walls = {true,true,true,true}; // TOP WALL, DOWN WALL, LEFT WALL, RIGHT WALL
	private Color color = Color.RED;
	

	public Cell(int x,int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	protected void deleteWall(int index) {
		walls[index] = false;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean[] wall(){
		return walls;
	}
	
	public void render(Graphics2D g, int xi, int yi, int cellSize) {
		int wallW = cellSize/10;
		g.setColor(color);
		g.fillRect(xi, yi, cellSize, cellSize);
		g.setColor(Color.WHITE);
		if(walls[0]) g.fillRect(xi, yi, cellSize, wallW);
		if(walls[1]) g.fillRect(xi, yi+cellSize, cellSize, wallW);
		if(walls[2]) g.fillRect(xi, yi, wallW, cellSize);
		if(walls[3]) g.fillRect(xi + cellSize, yi, wallW, cellSize);
	}

}
