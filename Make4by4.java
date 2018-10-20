package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class Make4by4 extends CheckNScore{

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 


	Make4by4 (int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
	}

	double[][] execute() throws IOException {
		findMine();
		return scoreMap;
	}


	void findMine() throws IOException {

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] colUnit = new int[6];
		int[] rowUnit = new int[6];
		int[] dia1Unit = new int[6];
		int[] dia2Unit = new int[6];

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {

				colUnit=copyToColUnit(colUnit,i,j);//col���� �޾ƿ�
				rowUnit=copyToRowUnit(rowUnit,i,j);//row���� �޾ƿ�
				dia1Unit=copyToDia1Unit(dia1Unit,i,j);//dia1�������� �޾ƿ�
				dia2Unit=copyToDia2Unit(dia2Unit,i,j);//dia2�������� �޾ƿ�

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
				boolean target[]= new boolean[4];//44�� ����������� 33�� ���� �־���ϴϱ� 33�� �ִ� Ÿ���� ã�ƺ��°���
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
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=100;//
								writer.append("(" + tempi + "," + tempj + ") col4by4 " + 100 +"\n");
							}
						}//this is for targetColumn
					}
					if(target[1]==true) {//row
						for(tempj=j,tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=100;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") row4by4 " + 100 +"\n");
							}
						}

					}
					if(target[2]==true) {//dia1
						for(tempj=j,tempi=i ;tempj<j+6;tempi--,tempj++) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=100;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia14by4 " + 100 +"\n");
							}
						}

					}
					if(target[3]==true) {//dai2
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(check(tempi,tempj)) {
								scoreMap[tempi][tempj]+=100;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia24by4 " + 100 +"\n");
							}
			
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
