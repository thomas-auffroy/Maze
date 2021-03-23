import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
	
	Maze maze = new Maze(700,25);
	
	public Panel() {
		// TODO Auto-generated constructor stub
	}
	
	public void wallNorth(Graphics g, int b, int a) {
		g.drawLine(a*maze.size/maze.step, b*maze.size/maze.step, (a+1)*maze.size/maze.step, b*maze.size/maze.step);	
	}
	
	public void wallSouth(Graphics g, int b, int a) {
		g.drawLine(a*maze.size/maze.step, (b+1)*maze.size/maze.step, (a+1)*maze.size/maze.step, (b+1)*maze.size/maze.step);
	}
	
	public void wallEast(Graphics g, int b, int a) {
		g.drawLine((a+1)*maze.size/maze.step, b*maze.size/maze.step, (a+1)*maze.size/maze.step, (b+1)*maze.size/maze.step);
	}
	public void wallWest(Graphics g, int b, int a) {
		g.drawLine(a*maze.size/maze.step, b*maze.size/maze.step, a*maze.size/maze.step, (b+1)*maze.size/maze.step);
	}
	
	public void paintComponent(Graphics g) {

		g.translate(this.getWidth()/4, this.getHeight()/4);
		maze.constructor(0, 0);
		maze.setWalls();
		
		for(int i = 0; i < maze.walls.length; i++) {
			for(int j = 0; j < maze.walls.length; j++) {
				if (maze.walls[i][j] == "wallN      ") {
					wallNorth(g,i,j);
				}else if (maze.walls[i][j] == "wallS      ") {
					wallSouth(g,i,j);
				}else if (maze.walls[i][j] == "wallE      ") {
					wallEast(g,i,j);
				}else if (maze.walls[i][j] == "wallW      ") {
					wallWest(g,i,j);
					
				}else if (maze.walls[i][j] == "3WallsN    ") {
					wallEast(g,i,j);
					wallWest(g,i,j);
					wallNorth(g,i,j);
				}else if (maze.walls[i][j] == "3WallsS    ") {
					wallSouth(g,i,j);
					wallEast(g,i,j);
					wallWest(g,i,j);
				}else if (maze.walls[i][j] == "3WallsE    ") {
					wallSouth(g,i,j);
					wallNorth(g,i,j);
					wallEast(g,i,j);
				}else if (maze.walls[i][j] == "3WallsW    ") {
					wallSouth(g,i,j);
					wallNorth(g,i,j);
					wallWest(g,i,j);
				
				}else if (maze.walls[i][j] == "collidorNS ") {
					wallWest(g,i,j);
					wallEast(g,i,j);
				}else if (maze.walls[i][j] == "collidorEW ") {
					wallNorth(g,i,j);
					wallSouth(g,i,j);
				
				}else if (maze.walls[i][j] == "cornerLU   ") {
					wallNorth(g,i,j);
					wallWest(g,i,j);
				}else if (maze.walls[i][j] == "cornerRU   ") {
					wallNorth(g,i,j);
					wallEast(g,i,j);	
				}else if (maze.walls[i][j] == "cornerLD   ") {
					wallWest(g,i,j);
					wallSouth(g,i,j);	
				}else if (maze.walls[i][j] == "cornerRD   ") {
					wallEast(g,i,j);
					wallSouth(g,i,j);
					
				}				
			}
		}
						
		
	}

}
