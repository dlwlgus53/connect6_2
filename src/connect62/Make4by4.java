package connect62;

import java.util.ArrayList;

public class Make4by4 {

	int[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;

	Make4by4(int[][] map,int[][]scoreMap,int myColor){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
	}

	int[][] execute() {
		findMine();
		return scoreMap;
	}


	void findMine() {

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

				boolean isEnemyCol=false;
				boolean isEnemyRow=false;
				boolean isEnemyDia1=false;
				boolean isEnemyDia2=false;

				isEnemyCol = enemyCheckInUnit(colUnit);
				isEnemyRow = enemyCheckInUnit(rowUnit);
				isEnemyDia1 = enemyCheckInUnit(dia1Unit);
				isEnemyDia2 = enemyCheckInUnit(dia2Unit);

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
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=100;//
						}//this is for targetColumn
					}
					if(target[1]==true) {//row
						for(tempj=j,tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 20점 드립니다~
						}

					}
					if(target[2]==true) {//dia1
						for(tempj=j,tempi=i ;tempj<j+6;tempi--,tempj++) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 20점 드립니다~
						}

					}
					if(target[3]==true) {//dai2
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 20점 드립니다~
						}

					}
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

	boolean enemyCheckInUnit(int[]unit) {
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
