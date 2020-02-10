package maze;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
	private static final String NAME = "MAZE";
	private static final int WINDOW_S = 1000;
	private static final int MAZE_SIZE = 15;
	private static final int NS_PER_UPDATE =  1000000000/1;
	private static final Dimension dim = new Dimension(1000,1000);
	
	private boolean running = false;
	private JFrame frame;
	private MazePanel mp;
	private Solver solver;
	private Generator maze = new Generator(MAZE_SIZE);
	
	
	
	public Main() {
		setMinimumSize(dim);
		setMaximumSize(dim);
		setPreferredSize(dim);
		
		frame = new JFrame( NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 1500);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		mp = new MazePanel(1000, maze.getBoard());
		
		
	}
	
	public synchronized void start() {
		running = true;
		new Thread( this ).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTimeNs = System.nanoTime();
		long lastSecondNs = System.nanoTime();
		
		long fpsCounter = 0;
		double delta = 0;
		while(running) {
			long curr = System.nanoTime();
			delta += (curr - lastTimeNs / NS_PER_UPDATE);
			lastTimeNs = curr;
			
			if( curr - lastSecondNs > 1000000000) {
				lastSecondNs = curr;
				System.out.println(fpsCounter);
				fpsCounter = 0;
			}
			
			if(delta >= 1) {
				delta -= 1;
				fpsCounter += 1;
				update();
				render();
			}
			
		}
		
	}
	
	public void update() {
		if(!maze.done()) {
			maze.step();
		}
		else {
			if(solver == null) {
				solver = new Solver(maze.getBoard(), maze.getBoard().getCell(MAZE_SIZE-1,MAZE_SIZE-1));
			}
			if(!solver.done()) {
				solver.step();
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		mp.render(g);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Main().start();

	}

}
