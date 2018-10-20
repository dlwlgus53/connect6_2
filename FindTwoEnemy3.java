package connect62;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class FindTwoEnemy3 extends CheckNScore{
	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	int targetRow;
	int targetCol;
	int targetWay;

	FindTwoEnemy3(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
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
		targetRow=0;
		targetCol=0;
		targetWay=0;

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
			findResult();
		}

	}


	void findResult() throws IOException{

		int tempi= targetRow;
		int tempj = targetCol;
		int way = targetWay;


		if(way==0) {//���ι����̸�
			setStone(tempi, tempj, 0);
		}

		if(way==1) {//���ι����̸�

			setStone(tempi, tempj,1);
		}
		if(way==2) {//�밢��1 �����϶�
			setStone(tempi, tempj, 2);
		}

		if(way==3) {//�밢��2 �����϶�
			setStone(tempi,tempj,3);
		}

	}



	boolean colTargetCheck() {//Ÿ���� �³� �ƴѰ� üũ
		boolean isTarget = false;


		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {
					int[] unit = new int[6];//6���� ��� ����
					boolean isMine = false;
					unit=copyToColUnit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}
					if(isMine == false && count == 3) {
						targetRow=i;
						targetCol=j;
						targetWay=0;;//����
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
	}

	boolean rowTargetCheck() {//Ÿ���� �³� �ƴѰ� üũ
		boolean isTarget = false;


		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==enemyColor) {

					int[] unit = new int[6];//6���� ��� ����
					boolean isMine = false;
					unit=copyToRowUnit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}
					if(isMine == false && count == 3) {
						targetRow=i;
						targetCol=j;
						targetWay=1;//����
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
	}


	boolean dia1TargetCheck() {//Ÿ���� �³� �ƴѰ� üũ
		boolean isTarget = false;


		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				if(map[i][j]==enemyColor) {

					int[] unit = new int[6];//6���� ��� ����
					boolean isMine = false;
					unit=copyToDia1Unit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow=i;
						targetCol=j;
						targetWay=2;//�밢��1
						isTarget=true;
					}
				}
			}

		}
		return isTarget;
	}

	boolean dia2TargetCheck() {//Ÿ���� �³� �ƴѰ� üũ
		boolean isTarget = false;



		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {
				if(map[i][j]==enemyColor) {
					int[] unit = new int[6];//6���� ��� ����
					boolean isMine = false;
					unit=copyToDia2Unit(unit,i,j);
					int count = 0;


					for(int k=0;k<6;k++) {
						if(unit[k]==enemyColor) {
							count++;
						}
						if(unit[k]==myColor)
							isMine = true;
					}

					if(isMine == false && count == 3) {
						targetRow=i;
						targetCol=j;
						targetWay=3;//�밢��2
						isTarget=true;
					}
				}
			}

		}
		return isTarget;








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

	void setStone(int i, int j, int way) throws IOException{
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ
		int index =0;
		listRow.clear();
		listCol.clear();
		int tempi = i;
		int tempj =j;
				if(way==0) {


					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]==-10000&&tempj>=1) {
							listRow.add(i);//����..�����Ѱ�..?
							listCol.add(tempj-1);
						}
						if(scoreMap[i][tempj]==-10000&&tempj<=map.length-1) {
							listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
							listCol.add(tempj+1);
						}
					}


					while(index<listRow.size()) {
						if(checkMust(listRow.get(index),listCol.get(index),5.1)){
							scoreMap[listRow.get(index)][listCol.get(index)]
									=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)],5.1);
							writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") col twothree "+ 5.1 +"\n");
						}
						index++;
					}
				}

		if(way==1) {


			for(tempi=i;tempi<i+6;tempi++) {
				if(scoreMap[tempi][j]==-10000&&tempi>=1) {
					listRow.add(tempi-1);//����..�����Ѱ�..?
					listCol.add(j);
				}
				if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//�̰�  dia1�� �����ؾ� �ϴºκ�
					listRow.add(tempi+1);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
					listCol.add(j);
				}
			}



			while(index<listRow.size()) {
				if(checkMust(listRow.get(index),listCol.get(index),5.2)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)], 5.2);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") row twothree"+ 5.2 +"\n");
				}
				index++;
			}
		}
		if(way==2) {
			for(tempi=i, tempj=j;tempi>i-6;tempj++,tempi--) {
				if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi-1>=0) {
					listRow.add(tempi-1);//�밢�� ���� ���� �Ʒ���
					listCol.add(tempj+1);
				}
				if(scoreMap[tempi][tempj]==-10000&&tempj+1>=0&&tempj+1<map.length) {
					listRow.add(tempi+1);//�밢�� ���� ������ �� ��
					listCol.add(tempj-1);
				}
			}


			while(index<listRow.size()) {

				if(checkMust(listRow.get(index),listCol.get(index),5.3)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)],5.3);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") dia1 twoThree "+ 5.3 +"\n");
				}
				index++;
			}
		}
		if(way==3) {

			for(tempi=i, tempj=j;tempi>i-6;tempj--,tempi--) {
				if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempi-1>=0) {
					listRow.add(tempi-1);//�밢�� ���� ���� �Ʒ���
					listCol.add(tempj-1);
				}
				if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi+1<map.length) {
					listRow.add(tempi+1);//�밢�� ���� ������ �� ��
					listCol.add(tempj+1);
				}
			}



			while(index<listRow.size()) {

				if(checkMust(listRow.get(index), listCol.get(index), 5.4)){
					scoreMap[listRow.get(index)][listCol.get(index)]
							=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)],5.4);
					writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") dia2 twoThree "+ 5.4 +"\n");
				}

				index++;
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
}
