package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class CheckNScore {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer;
	
	CheckNScore(int[][] map,double[][]scoreMap,int myColor, FileWriter writer) throws IOException{
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

	}
	
	public double scoreMust(double base, double d) {
		double a = (int)(base/10)*10 +d;//modify
		return a;
	}

	public boolean checkMust(int i, int j, double score) {
		boolean result = false;
		if(map[i][j]==0&&(scoreMap[i][j]%10==0||scoreMap[i][j]%10>score)){
			result = true;
		}

		return result;
	}

	public boolean check(int i, int j) {
		boolean result = true;
		if(map[i][j]!=0)
			result = false;
		return result;
	}
	

}
