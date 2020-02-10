package maze;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MazePanel {
	
	private int dims;
	private Board board;

	public MazePanel(int dim, Board board) {
		this.dims = dim;
		this.board = board;
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		board.render(g2,dims);
		
	}

}
