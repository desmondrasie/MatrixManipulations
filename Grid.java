package com.company;

public class Grid {

	private int[][] grid;
	
	public Grid(int n) {
		grid = new int[n][n];
	}
	
	public void print() {
		String s;
		for(int i = 0; i < grid.length; i++) {
			s = "[";
			for(int j = 0; j < grid.length-1; j++) {
				s += grid[i][j] + ",";
			}
			System.out.println(s + grid[i][grid.length-1] + "]");
		}
	}
	
	//will fill the closest empty column to the selected column, prioritizing left then right
	// ex: check col, check col - 1, check col + 1, check col - 2, check col + 2 etc...
	public void dropAt(int num, int col) { 
		
		if(col < 0 || col >= grid.length) 
			return;
		int counter = 0;
		
		while(counter < grid.length) {
			
			if(col - counter >= 0) {
				for(int i = grid.length - 1; i >= 0; i-- ) {
					if(grid[i][col - counter] == 0) {
						grid[i][col - counter] = num;
						return;
					}
				}
			}
			if(col + counter <= grid.length -1) {
				for(int i = grid.length - 1; i >= 0; i-- ) {
					if(grid[i][col + counter] == 0) {
						grid[i][col + counter] = num;
						return;
					}
				}
			}
			counter++;
		}
			
	} // End of dropAt Method
	
	public static void main(String[] args) {
		
		Grid game = new Grid(2);
		
		game.dropAt(2,1);
		game.dropAt(2,1);
		game.dropAt(2,0);
		game.dropAt(2,0);

		game.print();
		
	}

	
}
