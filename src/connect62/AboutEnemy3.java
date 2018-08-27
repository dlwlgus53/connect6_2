package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AboutEnemy3 {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	ArrayList<Integer> colListRow = new ArrayList<Integer>(0);//row를 담을 리스트
	ArrayList<Integer> colListCol = new ArrayList<Integer>(0);//col을 담을 리스트

	ArrayList<Integer> rowListRow = new ArrayList<Integer>(0);//row를 담을 리스트
	ArrayList<Integer> rowListCol = new ArrayList<Integer>(0);//col을 담을 리스트

	ArrayList<Integer> dia1ListRow = new ArrayList<Integer>(0);//row를 담을 리스트
	ArrayList<Integer> dia1ListCol = new ArrayList<Integer>(0);//col을 담을 리스트

	ArrayList<Integer> dia2ListRow = new ArrayList<Integer>(0);//row를 담을 리스트
	ArrayList<Integer> dia2ListCol = new ArrayList<Integer>(0);//col을 담을 리스트

	AboutEnemy3(int[][] map,double[][]scoreMap,int myColor,FileWriter writer){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
	}

	double[][] execute() throws IOException {
		findTwo3();
		findOverlap3();
		return scoreMap;
	}
	
	void findOverlap3() {
		
	}

	void findTwo3() throws IOException{

		colListRow.clear();
		colListCol.clear();

		rowListRow.clear();
		rowListCol.clear();

		dia1ListRow.clear();
		dia1ListCol.clear();

		dia2ListRow.clear();
		dia2ListCol.clear();

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
		if(isColTarget==true)	targetCount++;
		if(isColTarget==true)	targetCount++;
		if(isColTarget==true)	targetCount++;

		int index = 0;

		if(targetCount>=2) {
			if(isColTarget) {
				while(index<colListRow.size()) {
					if(scoreMap[colListRow.get(index)][colListCol.get(index)]!=-10000&&
							scoreMap[colListRow.get(index)][colListCol.get(index)]>5.1) {
						scoreMap[colListRow.get(index)][colListCol.get(index)]=5.1;
						writer.append("(" + colListRow.get(index) + "," + colListCol.get(index) + ") col About3 "+ 5.1 +"\n");
					}

					index++;
				}
			}
			index =0;

			if(isRowTarget) {
				while(index<rowListRow.size()) {
					if(scoreMap[rowListRow.get(index)][rowListCol.get(index)]!=-10000&&
							scoreMap[rowListRow.get(index)][rowListCol.get(index)]>5.2) {
						scoreMap[rowListRow.get(index)][rowListCol.get(index)]=5.1;
						writer.append("(" + rowListRow.get(index) + "," + rowListCol.get(index) + ") row About3 "+ 5.2 +"\n");
					}

					index++;
				}
			}
			index =0;

			if(isDia1Target) {
				while(index<dia1ListRow.size()) {
					if(scoreMap[dia1ListRow.get(index)][dia1ListCol.get(index)]!=-10000&&
							scoreMap[dia1ListRow.get(index)][dia1ListCol.get(index)]>5.3) {
						scoreMap[dia1ListRow.get(index)][dia1ListCol.get(index)]=5.3;
						writer.append("(" + dia1ListRow.get(index) + "," + dia1ListCol.get(index) + ") dia1 About3 "+ 5.3 +"\n");
					}

					index++;
				}
			}
			index =0;

			if(isDia2Target) {
				while(index<dia2ListRow.size()) {
					if(scoreMap[dia2ListRow.get(index)][dia2ListCol.get(index)]!=-10000&&
							scoreMap[dia2ListRow.get(index)][dia2ListCol.get(index)]>5.1) {
						scoreMap[dia2ListRow.get(index)][dia2ListCol.get(index)]=5.1;
						writer.append("(" + dia2ListRow.get(index) + "," + dia2ListCol.get(index) + ") dia2 About3 "+ 5.4 +"\n");
					}

					index++;
				}
			}
			index =0;

		}





	}

/*
	void findMine() throws IOException {

		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
		int[] colUnit = new int[6];
		int[] rowUnit = new int[6];
		int[] dia1Unit = new int[6];
		int[] dia2Unit = new int[6];

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {

				colUnit=copyToColUnit(colUnit,i,j);//col으로 받아용
				rowUnit=copyToRowUnit(colUnit,i,j);//row으로 받아용
				dia1Unit=copyToDia1Unit(colUnit,i,j);//dia1방향으로 받아용
				dia2Unit=copyToDia2Unit(colUnit,i,j);//dia2방향으로 받아용

				int k=0;

				int colCount=0;
				int rowCount=0;
				int dia1Count=0;
				int dia2Count=0;

				boolean isEnmeyCol=false;
				boolean isEnemyRow=false;
				boolean isEnemyDia1=false;
				boolean isEnemyDia2=false;
//지금 중복되는 3 만들기 하고 있었어.

				isMyCol = myCheckInUnit(colUnit);
				isMyRow = myCheckInUnit(rowUnit);
				isMyDia1 = myCheckInUnit(dia1Unit);
				isMyDia2 = myCheckInUnit(dia2Unit);

				colCount=countUnit(colUnit);
				rowCount=countUnit(rowUnit);
				dia1Count=countUnit(dia1Unit);
				dia2Count=countUnit(dia2Unit);


				int tempi=i;
				int tempj=j;
				int targetNumber=0;
				boolean target[]= new boolean[4];//44가 만들어지려면 33이 먼저 있어야하니까 33이 있는 타겟을 찾아보는거지
				//target0==col target1==row target2==dia1 targe3==dia2

				if(isEnemyCol==false&&colCount==3) target[0]=true; else target[0]=false;
				if(isEnemyRow==false&&rowCount==3) target[1]=true; else target[1]=false;
				if(isEnemyDia1==false&&dia1Count==3) target[2]=true; else target[2]=false;
				if(isEnemyDia2==false&&dia2Count==3) target[3]=true; else target[3]=false;


				int temp;//variable for iterate


				for(temp=0;temp<4;temp++) {
					if(target[temp]==true) targetNumber++;
				}

				if(targetNumber>=2) {

					if(target[0]==true) {//column
						for(tempi=i,tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=100;//
								writer.append("(" + tempi + "," + tempj + ") col4by4 " + 100 +"\n");
							}
						}//this is for targetColumn
					}
					if(target[1]==true) {//row
						for(tempj=j,tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 20점 드립니다~
								writer.append("(" + tempi + "," + tempj + ") row4by4 " + 100 +"\n");
							}
						}

					}
					if(target[2]==true) {//dia1
						for(tempj=j,tempi=i ;tempj<j+6;tempi--,tempj++) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 20점 드립니다~
								writer.append("(" + tempi + "," + tempj + ") dia14by4 " + 100 +"\n");
							}
						}

					}
					if(target[3]==true) {//dai2
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 20점 드립니다~
								writer.append("(" + tempi + "," + tempj + ") dia24by4 " + 100 +"\n");
							}

						}

					}
				}
			}
		}

	}

	*/

	
	boolean colTargetCheck() {
		boolean isTarget = false;

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				int[] unit = new int[6];//6개씩 떼어서 생각

				boolean isMine = false;
			
				unit=copyToColUnit(unit,i,j);

				int tempi = i;
				int tempj = j;
				int count = 0;


				for(int k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
					}
					if(unit[k]==myColor)
						isMine = true;
				}

				if(isMine == false && count == 3) {
					isTarget=true;

					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]==-10000&&tempj>=1) {
							colListRow.add(i);//왼쪽..에만둘게..?
							colListCol.add(tempj-1);
						}
						if(scoreMap[i][tempj]==-10000&&tempj<=map.length-1) {
							colListRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
							colListCol.add(tempj+1);
						}
					}
				}



			}
		}
		return isTarget;
	}
	
	boolean rowTargetCheck() {
		boolean isTarget = false;

		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				int[] unit = new int[6];//6개씩 떼어서 생각

				boolean isMine = false;
			
				unit=copyToRowUnit(unit,i,j);

				int tempi = i;
				int tempj = j;
				int count = 0;


				for(int k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
					}
					if(unit[k]==myColor)
						isMine = true;
				}

				if(isMine == false && count == 3) {
					isTarget=true;

					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]==-10000&&tempi>=1) {
							rowListRow.add(tempi-1);//왼쪽..에만둘게..?
							rowListCol.add(j);
						}
						if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//이고  dia1도 수정해야 하는부분
							rowListRow.add(tempi+1);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
							rowListCol.add(j);
						}
					}
				}



			}
		}
		return isTarget;
	}
	
	
	boolean dia1TargetCheck() {
		boolean isTarget = false;

		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				int[] unit = new int[6];//6개씩 떼어서 생각

				boolean isMine = false;
			
				unit=copyToDia1Unit(unit,i,j);

				int tempi = i;
				int tempj = j;
				int count = 0;


				for(int k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
					}
					if(unit[k]==myColor)
						isMine = true;
				}

				if(isMine == false && count == 3) {
					isTarget=true;

					for(tempi=i, tempj=j;tempi>i-6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi-1>=0) {
							dia1ListRow.add(tempi-1);//대각선 방향 왼쪽 아래돌
							dia1ListCol.add(tempj+1);
						}
						if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempj+1<map.length) {
							dia1ListRow.add(tempi+1);//대각선 방향 오른쪽 위 돌
							dia1ListCol.add(tempj-1);
						}
					}
				}



			}
		}
		return isTarget;
	}
	
	boolean dia2TargetCheck() {
		boolean isTarget = false;

		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				int[] unit = new int[6];//6개씩 떼어서 생각

				boolean isMine = false;
			
				unit=copyToDia2Unit(unit,i,j);

				int tempi = i;
				int tempj = j;
				int count = 0;


				for(int k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
					}
					if(unit[k]==myColor)
						isMine = true;
				}

				if(isMine == false && count == 3) {
					isTarget=true;

					for(tempi=i, tempj=j;tempi>i-6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi-1>=0) {
							dia2ListRow.add(tempi-1);//대각선 방향 왼쪽 아래돌
							dia2ListCol.add(tempj+1);
						}
						if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempj+1<map.length) {
							dia2ListRow.add(tempi+1);//대각선 방향 오른쪽 위 돌
							dia2ListCol.add(tempj-1);
						}
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

	boolean myCheckInUnit(int[]unit) {
		boolean result = false;
		int k=0;
		for(k=0;k<6;k++) {
			if(unit[k]==enemyColor)
				return true;
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





}
