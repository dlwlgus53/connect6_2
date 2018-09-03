package connect62;

import java.io.FileWriter;


public class FindBetter {
	int score;
	int row;
	int col;
	
	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 

	FindBetter(int[][] map,double[][]scoreMap,int myColor){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
	
	
	}
	
	int execute(int row, int col) {
		this.row = row;
		this.col = col;
		score = makeLittleUnitAndScore(row,col);
		return score;

	}
	
	
	
	int[]copyToColUnit(int[]unit, int row, int col){
		if(col<map.length-6+1) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row][col+1];
			unit[k+2]=map[row][col+2];
			unit[k+3]=map[row][col+3];
			unit[k+4]=map[row][col+4];
			unit[k+5]=map[row][col+5];
		}
		return unit;
	}

	int[]copyToRowUnit(int[]unit, int row, int col){
		if(row<map.length-6+1) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row+1][col];
			unit[k+2]=map[row+2][col];
			unit[k+3]=map[row+3][col];
			unit[k+4]=map[row+4][col];
			unit[k+5]=map[row+5][col];
		}

		return unit;
	}

	int[]copyToDia1Unit(int[]unit, int row, int col){
		if(row>=5&& col<=map.length-6) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row-1][col+1];
			unit[k+2]=map[row-2][col+2];
			unit[k+3]=map[row-3][col+3];
			unit[k+4]=map[row-4][col+4];
			unit[k+5]=map[row-5][col+5];
		}
		return unit;
	}

	int[]copyToDia2Unit(int[]unit, int row, int col){
		if(row>5&&col>5) {
			int k=0;
			unit[k] = map[row][col];
			unit[k+1]=map[row-1][col-1];
			unit[k+2]=map[row-2][col-2];
			unit[k+3]=map[row-3][col-3];
			unit[k+4]=map[row-4][col-4];
			unit[k+5]=map[row-5][col-5];
		}
		return unit;
	}
	
	
	int littleUnitCounter(int[]unit) {
		int result=0;
		for(int k=0;k<6;k++) {
			if(unit[k]==myColor) {
				result++;
			}
			if(unit[k]==enemyColor) {
				result=0;
				break;
			}
		}

		return result;
	}

	int makeLittleUnitAndScore(int row,int col){

		int[] littleUnit= new int[6];
		int result=0;

		littleUnit = copyToColUnit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		littleUnit = copyToRowUnit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		littleUnit = copyToDia1Unit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		littleUnit = copyToDia2Unit(littleUnit,row,col);
		result =+littleUnitCounter(littleUnit);

		return result;

	}



	
	
	



}
