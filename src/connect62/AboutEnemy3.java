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



	void findTwo3() throws IOException{
		//모든 3을 막을 필요는 없지만 3이 두개일때 반드시 하나는 막아야 함.
		
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
		//타겟이 맞는가 아닌가 체크!
		
		int targetCount=0;

		if(isColTarget==true)	targetCount++;
		if(isRowTarget==true)	targetCount++;
		if(isDia1Target==true)	targetCount++;
		if(isDia2Target==true)	targetCount++;
		//타겟이 몇개인지 체크
		int index = 0;
		//두개이상이면 일을 시작해야하지..
		
		if(targetCount>=2) {
			
			if(isColTarget) {
				while(index<colListRow.size()) {
					if(scoreMap[colListRow.get(index)][colListCol.get(index)]!=-10000&&
							scoreMap[colListRow.get(index)][colListCol.get(index)]>5.1) {
						scoreMap[colListRow.get(index)][colListCol.get(index)]=5.1;
						writer.append("(" + colListRow.get(index) + "," + colListCol.get(index) + ") col double3 "+ 5.1 +"\n");
					}

					index++;
				}
			}
			index =0;

			if(isRowTarget) {
				while(index<rowListRow.size()) {
					if(scoreMap[rowListRow.get(index)][rowListCol.get(index)]!=-10000&&
							scoreMap[rowListRow.get(index)][rowListCol.get(index)]>5.2) {
						scoreMap[rowListRow.get(index)][rowListCol.get(index)]=5.2;
						writer.append("(" + rowListRow.get(index) + "," + rowListCol.get(index) + ") row double3 "+ 5.2 +"\n");
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
						writer.append("(" + dia1ListRow.get(index) + "," + dia1ListCol.get(index) + ") dia1 double3 "+ 5.3 +"\n");
					}

					index++;
				}
			}
			index =0;

			if(isDia2Target) {
				while(index<dia2ListRow.size()) {
					if(scoreMap[dia2ListRow.get(index)][dia2ListCol.get(index)]!=-10000&&
							scoreMap[dia2ListRow.get(index)][dia2ListCol.get(index)]>5.4) {
						scoreMap[dia2ListRow.get(index)][dia2ListCol.get(index)]=5.4;
						writer.append("(" + dia2ListRow.get(index) + "," + dia2ListCol.get(index) + ") dia2 double3 "+ 5.4 +"\n");
					}

					index++;
				}
			}
			index =0;

		}

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

					int k=0;

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

					int tempi=i;
					int tempj=j;
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
							scoreMap[i][j]=6;
							writer.append("(" + i + "," +j + ") enemyAbout overlap3 "+ 6 +"\n");
						//	System.out.println("i : j " + i + " " + j + "target3Number : " + target3Number);
						}
					}
					//조건에 맞으면 중간에 돌 놓아버리기, 6점
				}
			}
		}

	}




	boolean colTargetCheck() {//타겟이 맞나 아닌가 체크
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

					for(tempi=i, tempj=j;tempi>i-6;tempj--,tempi--) {
						if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempi-1>=0) {
							dia2ListRow.add(tempi-1);//대각선 방향 왼쪽 아래돌
							dia2ListCol.add(tempj-1);
						}
						if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi+1<map.length) {
							dia2ListRow.add(tempi+1);//대각선 방향 오른쪽 위 돌
							dia2ListCol.add(tempj+1);
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





}
