package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FindTwoEnemy3 {
	
	

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	ArrayList<Integer> targetRow = new ArrayList<Integer>(0);//타겟을 담을 리스트(row)
	ArrayList<Integer> targetCol = new ArrayList<Integer>(0);//타겟을 담을 리스트(col)
	ArrayList<Integer> targetWay = new ArrayList<Integer>(0);//방향을 담을 예정 1.col 2.row.3.dia1 4. dia2
	int score1;
	int score2;
	
	




	FindTwoEnemy3(int[][] map,double[][]scoreMap,int myColor,FileWriter writer){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
	}

	double[][] execute() throws IOException {
		find();
		return scoreMap;
	}

	void find() throws IOException{
		targetRow.clear();
		targetCol.clear();

		boolean isColTarget = false;
		boolean isRowTarget = false;
		boolean isDia1Target = false;
		boolean isDia2Target = false;

		isColTarget = colTargetCheck();
		isRowTarget = rowTargetCheck();
		isDia1Target = dia1TargetCheck();
		isDia2Target = dia2TargetCheck();

		int targetCount=0;

		if(isColTarget==true)	targetCount++;
		if(isRowTarget==true)	targetCount++;
		if(isDia1Target==true)	targetCount++;
		if(isDia2Target==true)	targetCount++;

		if(targetCount>=2) {
			int[][] result1 = new int[2][2];
			int[][] result2 = new int[2][2];
			result1 = findResult(0);
			result2 = findResult(1);
			
				
			if(score1>score2) {
				if(scoreMap[result1[0][0]][result1[0][1]]>5) {
					scoreMap[result1[0][0]][result1[0][1]]=5;
					writer.append("(" + result1[0][0] + "," + result1[0][1] + ") findtwoenemy "+ 5 +"\n");
				}
				if(scoreMap[result1[1][0]][result1[1][1]]>5) {
					scoreMap[result1[1][0]][result1[1][1]]=5;
					writer.append("(" + result1[1][0] + "," + result1[1][1] + ") findtwoenemy "+ 5 +"\n");
				}
			}
			
			if(score2>score1) {
				if(scoreMap[result2[0][0]][result2[0][1]]>5) {
					scoreMap[result2[0][0]][result2[0][1]]=5;
					writer.append("(" + result2[0][0] + "," + result2[0][1] + ") findtwoenemy "+ 5 +"\n");
				}
				if(scoreMap[result2[1][0]][result2[1][1]]>5) {
					scoreMap[result2[1][0]][result2[1][1]]=5;
					writer.append("(" + result2[1][0] + "," + result2[1][1] + ") findtwoenemy "+ 5 +"\n");
				}
			}
		}
		
	}


	int[][]findResult(int targetNumber){
		int[][]result = new int[2][2];
		int[] start = new int[2];
		int[] end = new int[2];
		ArrayList<Integer> endRow = new ArrayList<Integer>(0);//타겟을 담을 리스트(row)
		ArrayList<Integer> endCol = new ArrayList<Integer>(0);//타겟을 담을 리스트(row)
		ArrayList<Integer> startRow = new ArrayList<Integer>(0);
		ArrayList<Integer> startCol = new ArrayList<Integer>(0);//타겟을 담을 리스트(row)

		int tempi= targetRow.get(targetNumber);
		int tempj = targetCol.get(targetNumber);
		int way = targetWay.get(targetNumber);
		int lastRow=0;
		int lastCol=0;

		endRow.clear();
		endCol.clear();

		if(way==0) {//가로방향이면
			for(int k=0;k<6;k++) {
				if(tempj+k<map.length&&map[tempi][tempj+k]==enemyColor) {
					lastRow = tempi;
					lastCol = tempj+k;
				}
				if(tempj+k<map.length&&map[tempi][tempj+k]==0) {
					endRow.add(tempi);
					endCol.add(tempj+k);
				}
			}



			int index = 0;
			int max =0;

			for(index = 0;index<endRow.size();index++) {
				startRow.clear();
				startCol.clear();
				int j=0;
				for(j=1;j<6;j++) {
					if(endCol.get(index)-j>=0&&endCol.get(index)-j<lastCol&&map[endRow.get(index)][endCol.get(index)-j]==0) {
						startRow.add(endRow.get(index));
						startCol.add(endCol.get(index)-j);
					}
				}
				int score=0;
				
				for(int k=0;k<startRow.size();k++) {
					start[0] = startRow.get(k);
					start[1] = startCol.get(k);
					
					score =+ makeLittleUnitAndScore(end[0],end[1]);
					score =+ makeLittleUnitAndScore(start[0],start[1]);

					if(score>max) {
						max = score;
						result[0][0] = start[0]; result[0][1]= start[1]; result[1][0] = end[0]; result[1][1]=end[1];
					}
				}




			}

		}
		
		if(way==1) {//세로방향이면
			for(int k=0;k<6;k++) {
				if(tempi+k<map.length&&map[tempi+k][tempj]==enemyColor) {
					lastRow = tempi+k;
					lastCol = tempj;
				}
				if(tempi+k<map.length&&map[tempi+k][tempj]==0) {
					endRow.add(tempi+k);
					endCol.add(tempj);
				}
			}



			int index = 0;
			int max =0;

			for(index = 0;index<endRow.size();index++) {
				startRow.clear();
				startCol.clear();
				int j=0;
				for(j=1;j<6;j++) {
					if(endRow.get(index)-j>=0&&endRow.get(index)-j<lastRow&&map[endRow.get(index)-j][endCol.get(index)]==0) {
						startRow.add(endRow.get(index)-j);
						startCol.add(endCol.get(index));
					}
				}
				int score=0;
				score =+ makeLittleUnitAndScore(end[0],end[1]);
			
				for(int k=0;k<startRow.size();k++) {
					start[0] = startRow.get(k);
					start[1] = startCol.get(k);
					score =+ makeLittleUnitAndScore(start[0],start[1]);
					if(score>max) {
						max = score;
						result[0][0] = start[0]; result[0][1]= start[1]; result[1][0] = end[0]; result[1][1]=end[1];
					}
				}




			}

		}
		
		if(way==2) {//대각선1 방향일때
			for(int k=0;k<6;k++) {
				if(tempi-k>=0&&tempj+k<map.length&&map[tempi-k][tempj+k]==enemyColor) {
					lastRow = tempi-k;
					lastCol = tempj+k;
				}
				if(tempi-k>=0&&tempj+k<map.length&&map[tempi-k][tempj+k]==0) {
					endRow.add(tempi-k);
					endCol.add(tempj+k);
				}
			}



			int index = 0;
			int max =0;

			for(index = 0;index<endRow.size();index++) {
				startRow.clear();
				startCol.clear();
				int j=0;
				for(j=1;j<6;j++) {
					if(endRow.get(index)-j>=0&&endCol.get(index)+j<map.length&&
							endRow.get(index)-j<lastRow&&map[endRow.get(index)-j][endCol.get(index)+j]==0) {
						startRow.add(endRow.get(index)-j);
						startCol.add(endCol.get(index)+j);
					}
				}
				int score=0;
				score =+ makeLittleUnitAndScore(end[0],end[1]);
				
				for(int k=0;k<startRow.size();k++) {
					start[0] = startRow.get(k);
					start[1] = startCol.get(k);
					score =+ makeLittleUnitAndScore(start[0],start[1]);
					if(score>max) {
						max = score;
						result[0][0] = start[0]; result[0][1]= start[1]; result[1][0] = end[0]; result[1][1]=end[1];
					}
				}




			}

		}
		
		int max =0;
		if(way==3) {//대각선2 방향일때
			for(int k=0;k<6;k++) {
				if(tempi+k<map.length&&tempj+k<map.length&&map[tempi+k][tempj+k]==enemyColor) {
					lastRow = tempi-k;
					lastCol = tempj+k;
				}
				if(tempi+k<map.length&&tempj+k<map.length&&map[tempi+k][tempj+k]==0) {
					endRow.add(tempi-k);
					endCol.add(tempj+k);
				}
			}



			int index = 0;
			

			for(index = 0;index<endRow.size();index++) {
				startRow.clear();
				startCol.clear();
				int j=0;
				for(j=1;j<6;j++) {
					if(endRow.get(index)+j<map.length&&endCol.get(index)+j<map.length&&
							endRow.get(index)+j<lastRow&&map[endRow.get(index)+j][endCol.get(index)+j]==0) {
						startRow.add(endRow.get(index)+j);
						startCol.add(endCol.get(index)+j);
					}
				}
				int score=0;
				score =+ makeLittleUnitAndScore(end[0],end[1]);
				
				for(int k=0;k<startRow.size();k++) {
					start[0] = startRow.get(k);
					start[1] = startCol.get(k);
					score =+ makeLittleUnitAndScore(start[0],start[1]);
					if(score>max) {
						max = score;
						result[0][0] = start[0]; result[0][1]= start[1]; result[1][0] = end[0]; result[1][1]=end[1];
					}
				}




			}

		}
	if(targetNumber==0)
		this.score1 = max;
	else
		this.score2 = max;
	
		return result;
	}







	boolean colTargetCheck() {//타겟이 맞나 아닌가 체크
		boolean isTarget = false;


		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {
					int tempi = i;
					int tempj =j;
					int[] unit = new int[6];//6개씩 떼어서 생각
					boolean isMine = false;
					unit=copyToColUnit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++,tempj++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}
					if(isMine == false && count == 3) {
						targetRow.add(tempi);
						targetCol.add(tempj);
						targetWay.add(0);//가로
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
	}

	boolean rowTargetCheck() {//타겟이 맞나 아닌가 체크
		boolean isTarget = false;


		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==enemyColor) {
					int tempi = i;
					int tempj =j;
					int[] unit = new int[6];//6개씩 떼어서 생각
					boolean isMine = false;
					unit=copyToRowUnit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++,tempj++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}
					if(isMine == false && count == 3) {
						targetRow.add(tempi);
						targetCol.add(tempj);
						targetWay.add(1);//세로
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
	}


	boolean dia1TargetCheck() {//타겟이 맞나 아닌가 체크
		boolean isTarget = false;


		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {
					int tempi = i;
					int tempj =j;
					int[] unit = new int[6];//6개씩 떼어서 생각
					boolean isMine = false;
					unit=copyToDia1Unit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++,tempj++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow.add(tempi);
						targetCol.add(tempj);
						targetWay.add(2);//대각선1
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
	}

	boolean dia2TargetCheck() {//타겟이 맞나 아닌가 체크
		boolean isTarget = false;



		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {
				if(map[i][j]==enemyColor) {
					int tempi = i;
					int tempj =j;
					int[] unit = new int[6];//6개씩 떼어서 생각
					boolean isMine = false;
					unit=copyToDia2Unit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++,tempj++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow.add(tempi);
						targetCol.add(tempj);
						targetWay.add(3);//대각선2
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
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
