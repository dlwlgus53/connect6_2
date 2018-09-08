package connect62;

import java.io.FileWriter;
import java.io.IOException;


public class AboutEnemy3 {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 


	AboutEnemy3(int[][] map,double[][]scoreMap,int myColor,FileWriter writer){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
	}

	double[][] execute() throws IOException {

		findOverlap3();
		return scoreMap;
	}




	void findOverlap3() throws IOException {//동시에 3이되는 부분 찾기


		int[] colUnit = new int[6];
		int[] rowUnit = new int[6];
		int[] dia1Unit = new int[6];
		int[] dia2Unit = new int[6];

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==0) {//비어있는 칸을 중심으로 가로 세로 대각선에 만약에 놓였을때 위험해 질수 있는 칸을 찾아서 그곳을 메우는 것이 목적

					colUnit=copyToColUnit(colUnit,i,j);//col으로 받아용
					rowUnit=copyToRowUnit(rowUnit,i,j);//row으로 받아용
					dia1Unit=copyToDia1Unit(dia1Unit,i,j);//dia1방향으로 받아용
					dia2Unit=copyToDia2Unit(dia2Unit,i,j);//dia2방향으로 받아용
					//비어있는 칸 중심으로 유닛을 떼어냄

				

					int colCount=0;
					int rowCount=0;
					int dia1Count=0;
					int dia2Count=0;

					boolean isMineCol=false;
					boolean isMineRow=false;
					boolean isMineDia1=false;
					boolean isMineDia2=false;

					isMineCol = mineCheckInUnit(colUnit);
					isMineRow = mineCheckInUnit(rowUnit);
					isMineDia1 = mineCheckInUnit(dia1Unit);
					isMineDia2 = mineCheckInUnit(dia2Unit);
					//유닛안에 내꺼있나 체크

					colCount=countUnit(colUnit);
					rowCount=countUnit(rowUnit);
					dia1Count=countUnit(dia1Unit);
					dia2Count=countUnit(dia2Unit);
					//유닛에 상대 방 돌 갯수 체크

					
					int target3Number=0;
					int target2Number=0;


					boolean target3[]= new boolean[4];//일단 3이 있나 찾아보자.
					boolean target2[]= new boolean[4];//그리고2가 있나 찾아보자.

					//0==col 1==row 2==dia1 3==dia2//이 순서야 알겠지.

					if(isMineCol==false&&colCount==3) target3[0]=true; else target3[0]=false;
					if(isMineRow==false&&rowCount==3) target3[1]=true; else target3[1]=false;
					if(isMineDia1==false&&dia1Count==3) target3[2]=true; else target3[2]=false;
					if(isMineDia2==false&&dia2Count==3) target3[3]=true; else target3[3]=false;
					//내꺼없고, 상대방 3개 있는거 검사

					if(isMineCol==false&&colCount==2) target2[0]=true; else target2[0]=false;
					if(isMineRow==false&&rowCount==2) target2[1]=true; else target2[1]=false;
					if(isMineDia1==false&&dia1Count==2) target2[2]=true; else target2[2]=false;
					if(isMineDia2==false&&dia2Count==2) target2[3]=true; else target2[3]=false;
					//내꺼없고, 상대방 2개 있는거 검사

					int temp;

					/*for(temp = 0;temp<4;temp++) {
						System.out.println(temp +" target3 "+ target3[temp]);
						System.out.println(temp +" target2 "+ target2[temp]);
					}*/

					for(temp=0;temp<4;temp++) {
						if(target3[temp]==true) target3Number++;
					}

					for(temp=0;temp<4;temp++) {
						if(target2[temp]==true) target2Number++;
					}


					//System.out.println("i : j " + i + " " + j + "target3Number : " + target3Number);

					if((target3Number>=1&&target2Number>=1)||(target3Number>=2)) {
						if(scoreMap[i][j]>6) {
							scoreMap[i][j]+=6;
							writer.append("(" + i + "," +j + ") enemyAbout overlap3 "+ 6 +"\n");
							//	System.out.println("i : j " + i + " " + j + "target3Number : " + target3Number);
						}
					}
					//조건에 맞으면 중간에 돌 놓아버리기, 6점
				}
			}
		}

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

	boolean mineCheckInUnit(int[]unit) {
		boolean result = false;
		int k=0;
		for(k=0;k<6;k++) {
			if(unit[k]==myColor)
				result =  true;
		}
		return result;
	}

	int countUnit(int[]unit) {
		int count=0;
		for(int k=0;k<6;k++) {
			if(unit[k]==enemyColor)	count++;
		}
		return count;
	}


	double scoreMust(double base, double d) {
		double a = (int)(base/10)*10 +d;//modify
		return a;
	}

	boolean checkMust(int i, int j, double score) {
		boolean result = false;
		if(map[i][j]==0&&(scoreMap[i][j]%10==0||scoreMap[i][j]%10>score)){
			result = true;
		}

		return result;
	}

	boolean check(int i, int j) {
		boolean result = true;
		if(map[i][j]!=0)
			result = false;
		return result;
	}



}
