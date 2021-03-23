import java.util.ArrayList;

public class Maze {
int size = 0, step = 0, path = 1;
int[][] structure;
String[][] walls;
boolean[][] isVisited;
ArrayList<ArrayList<Integer>> caseWithNeighbour = new ArrayList<ArrayList<Integer>>(); 

	public Maze(int x, int s) {
		// TODO Auto-generated constructor stub
		this.size = x;
		this.step = s;
		this.structure = new int[this.step][this.step];
		this.isVisited = new boolean[this.step][this.step];
		this.walls = new String[this.step][this.step];
		
		for(int i=0; i < this.step; i++) {
			for (int j = 0; j < this.step; j++) {
				this.structure[i][j] = this.path-1;
				this.isVisited[i][j] = false;
				this.walls[i][j] = "null       ";
			}
		}
	}
	
	public void constructor(int indexX, int indexY) {
		ArrayList<String> neighbourDirection = new ArrayList<String>();
		this.structure[indexX][indexY] = this.path;
		this.isVisited[indexX][indexY] = true;
		int neighbourNumber = 0, random = 0;
		
		try {
			if (this.isVisited[indexX-1][indexY] == false) {
				neighbourNumber ++;
				neighbourDirection.add("north");
			}
		}catch(Exception IndexOutOfBoundsException) {}
		
		try {
			if (this.isVisited[indexX+1][indexY] == false) {
				neighbourNumber ++;
				neighbourDirection.add("south");
			}
		}catch(Exception IndexOutOfBoundsException) {}
		
		try {
			if (this.isVisited[indexX][indexY-1] == false) {
				neighbourNumber ++;
				neighbourDirection.add("west");
			}
		}catch(Exception IndexOutOfBoundsException) {}
		
		try {
			if (this.isVisited[indexX][indexY+1] == false) {
				neighbourNumber ++;
				neighbourDirection.add("east");
			}	
		}catch(Exception IndexOutOfBoundsException) {}
		
		if (neighbourNumber > 0) {
			if(neighbourNumber > 1) {
				ArrayList<Integer> dummy = new ArrayList<Integer>();
				dummy.add(indexX);
				dummy.add(indexY);
				this.caseWithNeighbour.add(dummy);
			}
			this.path++;
			random = (int)(Math.random() * neighbourNumber);
			String newCase = neighbourDirection.get(random);
			
			if (newCase == "north") {
				
				constructor(indexX-1,indexY);
			}
			else if (newCase == "south") {
				
				constructor(indexX+1,indexY);
			}
			else if (newCase == "west") {
				
				constructor(indexX,indexY-1);
			}
			else {
				
				constructor(indexX,indexY+1);
			}
			
		}
		else if (this.caseWithNeighbour.size() > 0) {
			int a = this.caseWithNeighbour.size(), i = this.caseWithNeighbour.get(a-1).get(0), j = this.caseWithNeighbour.get(a-1).get(1);
			path = structure[i][j];
			this.caseWithNeighbour.remove(a-1);			
			constructor(i,j);
		}
	}
	
	public void setWalls() {
		
		for(int i=0; i<structure.length;i++) {
			for(int j=0; j<structure.length;j++) {
				int caseValue = structure[i][j];
				if (i > 0 && j > 0 && i < structure.length-1 && j < structure.length-1) {
					
					int caseValueNorth = structure[i-1][j], caseValueSouth = structure[i+1][j], caseValueWest = structure[i][j-1], caseValueEast = structure[i][j+1];
					
					if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueEast)) || ((caseValue + 1 == caseValueSouth) && (caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueEast)) || ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth)) ){
						this.walls[i][j] = "wallN      ";
					}
					else if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueEast)) || ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueNorth)) || ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueEast))){
						this.walls[i][j] = "wallS      ";
					}
					else if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "wallE      ";
					}
					else if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueEast))){
						this.walls[i][j] = "wallW      ";
					}
					
					else if( (caseValue - 1 == caseValueNorth) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue - 1 == caseValueSouth) && (caseValue + 1 != caseValueNorth) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsN    ";
					}
					else if( (caseValue - 1 == caseValueWest) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueNorth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsE    ";
					}
					else if( (caseValue - 1 == caseValueEast) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueNorth) ){
						this.walls[i][j] = "3WallsW    ";
					}
					
					else if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "collidorNS ";
					}
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "collidorEW ";
					}
					
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "cornerLU   ";
					}
					else if( ((caseValue + 1 == caseValueSouth) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "cornerRU   ";
					}
					else if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerRD   ";
					}
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerLD   ";
					}		
					
					
				} else if (i == 0 && j < structure.length-1 && j > 0){
					
					int caseValueSouth = structure[i+1][j], caseValueWest = structure[i][j-1], caseValueEast = structure[i][j+1];
					
					if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueEast)) || ((caseValue + 1 == caseValueSouth) && (caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueEast)) || ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth)) ){
						this.walls[i][j] = "wallN      ";
					}
					
					else if( (caseValue - 1 == caseValueSouth) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsN    ";
					}
					else if( (caseValue - 1 == caseValueWest) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsE    ";
					}
					else if( (caseValue - 1 == caseValueEast) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsW    ";
					}
					
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "collidorEW ";
					}
					
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "cornerLU   ";
					}
					else if( ((caseValue + 1 == caseValueSouth) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "cornerRU   ";
					}

					
				} else if(i == structure.length-1 && j < structure.length-1 && j > 0) {
					
					int caseValueNorth = structure[i-1][j], caseValueWest = structure[i][j-1], caseValueEast = structure[i][j+1];
					
					if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueEast)) || ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueNorth)) || ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueEast))){
						this.walls[i][j] = "wallS      ";
					}

					
					else if( (caseValue - 1 == caseValueNorth) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsS    ";
					}

					else if( (caseValue - 1 == caseValueWest) && (caseValue + 1 != caseValueNorth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsE    ";
					}
					else if( (caseValue - 1 == caseValueEast) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueNorth) ){
						this.walls[i][j] = "3WallsW    ";
					}

					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "collidorEW ";
					}
					
					else if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerRD   ";
					}
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerLD   ";
					}		
				
					
				} else if (j == 0 && i < structure.length-1 && i > 0){
					int caseValueNorth = structure[i-1][j], caseValueSouth = structure[i+1][j], caseValueEast = structure[i][j+1];

					if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueEast))){
						this.walls[i][j] = "wallW      ";
					}
					
					else if( (caseValue - 1 == caseValueNorth) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue - 1 == caseValueSouth) && (caseValue + 1 != caseValueNorth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsN    ";
					}
					else if( (caseValue - 1 == caseValueEast) && (caseValue + 1 != caseValueNorth) && (caseValue + 1 != caseValueSouth) ){
						this.walls[i][j] = "3WallsW    ";
					}


					
					else if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "collidorNS ";
					}

					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "cornerLU   ";
					}
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerLD   ";
					}			
					
				}else if(j == structure.length-1 && i < structure.length-1 && i > 0){
					
					int caseValueNorth = structure[i-1][j], caseValueSouth = structure[i+1][j], caseValueWest = structure[i][j-1];
					


					if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth)) || ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "wallE      ";
					}

					
					else if( (caseValue - 1 == caseValueNorth) && (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue - 1 == caseValueSouth) && (caseValue + 1 != caseValueNorth) && (caseValue + 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsN    ";
					}
					else if( (caseValue + 1 != caseValueSouth) && (caseValue + 1 != caseValueWest) && (caseValue + 1 != caseValueNorth) ){
						this.walls[i][j] = "3WallsW    ";
					}
					
					else if( ((caseValue + 1 == caseValueNorth) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueNorth) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "collidorNS ";
					}
					

					else if( ((caseValue + 1 == caseValueSouth) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "cornerRU   ";
					}
					else if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerRD   ";
					}

				} else if (i == 0 && j == 0) {
					int caseValueSouth = structure[i+1][j], caseValueEast = structure[i][j+1];

					
					if( (caseValue - 1 == caseValueSouth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsN    ";
					}
					else if( (caseValue - 1 == caseValueEast) && (caseValue + 1 != caseValueSouth)){
						this.walls[i][j] = "3WallsW    ";
					}
					else if( (caseValue + 1 == caseValueSouth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsN    ";
					}
					else if( (caseValue + 1 == caseValueEast) && (caseValue + 1 != caseValueSouth)){
						this.walls[i][j] = "3WallsW    ";
					}		
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueSouth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueSouth))){
						this.walls[i][j] = "cornerLU   ";
					}
		
					
					
				} else if(i == 0 && j == structure.length-1) {
					int caseValueSouth = structure[i+1][j], caseValueWest = structure[i][j-1];

					if( (caseValue - 1 == caseValueSouth) && (caseValue + 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue - 1 == caseValueWest) && (caseValue + 1 != caseValueSouth) ){
						this.walls[i][j] = "3WallsE    ";
					}
					else if( (caseValue + 1 == caseValueSouth) && (caseValue - 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue + 1 == caseValueWest) && (caseValue - 1 != caseValueSouth) ){
						this.walls[i][j] = "3WallsE    ";
					}
					

					else if( ((caseValue + 1 == caseValueSouth) && (caseValue - 1 == caseValueWest)) || ((caseValue - 1 == caseValueSouth) && (caseValue + 1 == caseValueWest))){
						this.walls[i][j] = "cornerRU   ";
					}
	
					
					
				} else if (i == structure.length - 1 && j == 0) {
					int caseValueNorth = structure[i-1][j], caseValueEast = structure[i][j+1];

					
					if( (caseValue - 1 == caseValueNorth) && (caseValue + 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue - 1 == caseValueEast) && (caseValue + 1 != caseValueNorth) ){
						this.walls[i][j] = "3WallsW    ";
					}
					else if( (caseValue + 1 == caseValueNorth) && (caseValue - 1 != caseValueEast) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue + 1 == caseValueEast) && (caseValue - 1 != caseValueNorth) ){
						this.walls[i][j] = "3WallsW    ";
					}
					else if( ((caseValue + 1 == caseValueEast) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueEast) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerLD   ";
					}		
										

				} else if (i == structure.length - 1 && j == structure.length - 1) {
					int caseValueNorth = structure[i-1][j], caseValueWest = structure[i][j-1];
										
					if( (caseValue - 1 == caseValueNorth) && (caseValue + 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue - 1 == caseValueWest) && (caseValue + 1 != caseValueNorth)){
						this.walls[i][j] = "3WallsE    ";
					}
					else if( (caseValue + 1 == caseValueNorth) && (caseValue - 1 != caseValueWest) ){
						this.walls[i][j] = "3WallsS    ";
					}
					else if( (caseValue + 1 == caseValueWest) && (caseValue - 1 != caseValueNorth) ){
						this.walls[i][j] = "3WallsE    ";
					}
					
					else if( ((caseValue + 1 == caseValueWest) && (caseValue - 1 == caseValueNorth)) || ((caseValue - 1 == caseValueWest) && (caseValue + 1 == caseValueNorth))){
						this.walls[i][j] = "cornerRD   ";
					}
				}
				
			}
		}
	}
	
	
	
	public String toString() {
		for(int i=0; i < step; i++) {
			for (int j = 0; j < step; j++) {
				if(structure[i][j]<10) {System.out.print(" "+structure[i][j] + " ");}
				else{System.out.print(structure[i][j] + " ");}
			}

			System.out.println("");
		}
		
		for(int i=0; i < step; i++) {
			for (int j = 0; j < step; j++) {
				System.out.print(walls[i][j]);
			}

			System.out.println("");
		}

		
		return "";
	}

}
