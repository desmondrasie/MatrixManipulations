package com.company;

public class Assignment1 {

	public static void printMatrix(int [][] map){
		for (int i = 0; i <map.length; i++){
			System.out.println();
			for (int j = 0; j < map[i].length; j++)
				System.out.print(map[i][j]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println("Initializing Matrix");
		int[][] map = {{1,2,3},{4,5,6},{7,8,9}};
		int [] cell = {1,0};
		printMatrix(map);
		rotateMap(map);
		System.out.println(" ");
		System.out.println("Matrix has been rotated!");
		printMatrix(map);

	}
	public static int [] findPeak(int [][] map) {
		int [] zeroMatrix = {};
		if(map.length == 0) // to account for an input of 0x0 matrix "{}"
			return zeroMatrix;
		
		//basic nested for loop and comparison 
		int counter = 0;
		int [] cell = new int [2];		
		for ( int i = 0; i < map.length; i++) {
			for (int j = 0; j <map.length; j++) {
				int element = map[i][j]; 
				if (element > counter) {
					counter = element;
					cell [0] = i;
					cell [1] = j;
				}
			}	
		}
		return cell;
	}// End of findPeak
	
	public static boolean isSink(int [][] map, int [] cell) {
		
		if (map.length - 1 < cell[0] || map.length - 1 < cell[1]) // filters out cell inputs that exceed matrix dimensions
			return false; 
		if(cell[0] < 0 || cell[1] < 0 ) // filters out negative inputs for the cell [i,j]
			return false;
		
		int [] lowestCell = lowestAdjCell(map,cell);
		int sink = map [cell[0]][cell[1]]; // gets the value of the cell in question
		int lowestValue = map[lowestCell[0]][lowestCell[1]];
		
		if (sink == lowestValue)
			return true;
		
		return false;
	} // End of isSink
	
	public static int [] findLocalSink(int[][] map, int[] startCell) { 
		int [] zeroMatrix = {};
		if(map.length == 0) // to account for an input of 0x0 matrix "{}"
			return zeroMatrix;
		
		int [] adjCell = startCell.clone(); // creates a copy of the startCell array, without reference 
		while (isSink(map,adjCell) == false) // runs when current cell is not a sink 
			adjCell = lowestAdjCell(map,adjCell); // adjCell becomes reused as next sink check cell
												  // process is repeated until a sink is found
		return adjCell;	 
		
	}// End of findLocalSink
	
	public static void rotateMap(int[][] map) {
		
		int [][] a = new int [map.length][map.length]; 
		
		for (int j = map.length - 1; j >= 0; j-- ) { //start at the last column 
			for (int i = 0; i < map.length; i++) { // start at the first row
				a[map.length - 1 - j][i] = map[i][j]; //give the map value to the new array 
			}
		}
		for (int i = 0; i < map.length; i++) //use a for loop to change the actual value of map, instead of the reference 
			for (int j = 0; j < map.length; j++)
				map[i][j] = a[i][j];
				
	}// End of rotateMap
	
	public static int [] lowestAdjCell(int[][] map, int[] startCell) { //helper method used in isSink() & findLocalSink()
		
		int startValue = map[startCell[0]][startCell[1]];
		int lowestValue = map[startCell[0]][startCell[1]];
		int [] cellCounter = startCell.clone();
		
		for (int i = startCell[0] - 1; i <= startCell[0] + 1; i++) {			
			if (i >= 0 && i < map.length ) {
				for (int j = startCell[1] - 1; j <= startCell[1] + 1; j++) {
					if (j >= 0 && j < map.length ) {
						int checkValue = map[i][j];
						if(checkValue < startValue && checkValue < lowestValue) {
							lowestValue = checkValue;
							cellCounter[0] = i;
							cellCounter[1] = j;			
						} 
					} 
				} 	
			} 
		}
		return cellCounter;
	}// End of lowestAdjCell		
}



