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
	ArrayList<Integer> colListRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
	ArrayList<Integer> colListCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ

	ArrayList<Integer> rowListRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
	ArrayList<Integer> rowListCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ

	ArrayList<Integer> dia1ListRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
	ArrayList<Integer> dia1ListCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ

	ArrayList<Integer> dia2ListRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
	ArrayList<Integer> dia2ListCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ

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
		//��� 3�� ���� �ʿ�� ������ 3�� �ΰ��϶� �ݵ�� �ϳ��� ���ƾ� ��.
		
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
		//Ÿ���� �´°� �ƴѰ� üũ!
		
		int targetCount=0;

		if(isColTarget==true)	targetCount++;
		if(isRowTarget==true)	targetCount++;
		if(isDia1Target==true)	targetCount++;
		if(isDia2Target==true)	targetCount++;
		//Ÿ���� ����� üũ
		int index = 0;
		//�ΰ��̻��̸� ���� �����ؾ�����..
		
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


	void findOverlap3() throws IOException {//���ÿ� 3�̵Ǵ� �κ� ã��


		int[] colUnit = new int[6];
		int[] rowUnit = new int[6];
		int[] dia1Unit = new int[6];
		int[] dia2Unit = new int[6];

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==0) {//����ִ� ĭ�� �߽����� ���� ���� �밢���� ���࿡ �������� ������ ���� �ִ� ĭ�� ã�Ƽ� �װ��� �޿�� ���� ����
					
					colUnit=copyToColUnit(colUnit,i,j);//col���� �޾ƿ�
					rowUnit=copyToRowUnit(rowUnit,i,j);//row���� �޾ƿ�
					dia1Unit=copyToDia1Unit(dia1Unit,i,j);//dia1�������� �޾ƿ�
					dia2Unit=copyToDia2Unit(dia2Unit,i,j);//dia2�������� �޾ƿ�
					//����ִ� ĭ �߽����� ������ ���

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
					//���־ȿ� �����ֳ� üũ
					
					colCount=countUnit(colUnit);
					rowCount=countUnit(rowUnit);
					dia1Count=countUnit(dia1Unit);
					dia2Count=countUnit(dia2Unit);
					//���ֿ� ��� �� �� ���� üũ

					int tempi=i;
					int tempj=j;
					int target3Number=0;
					int target2Number=0;


					boolean target3[]= new boolean[4];//�ϴ� 3�� �ֳ� ã�ƺ���.
					boolean target2[]= new boolean[4];//�׸���2�� �ֳ� ã�ƺ���.

					//0==col 1==row 2==dia1 3==dia2//�� ������ �˰���.

					if(isMineCol==false&&colCount==3) target3[0]=true; else target3[0]=false;
					if(isMineRow==false&&rowCount==3) target3[1]=true; else target3[1]=false;
					if(isMineDia1==false&&dia1Count==3) target3[2]=true; else target3[2]=false;
					if(isMineDia2==false&&dia2Count==3) target3[3]=true; else target3[3]=false;
					//��������, ���� 3�� �ִ°� �˻�
				
					if(isMineCol==false&&colCount==2) target2[0]=true; else target2[0]=false;
					if(isMineRow==false&&rowCount==2) target2[1]=true; else target2[1]=false;
					if(isMineDia1==false&&dia1Count==2) target2[2]=true; else target2[2]=false;
					if(isMineDia2==false&&dia2Count==2) target2[3]=true; else target2[3]=false;
					//��������, ���� 2�� �ִ°� �˻�

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
					//���ǿ� ������ �߰��� �� ���ƹ�����, 6��
				}
			}
		}

	}




	boolean colTargetCheck() {//Ÿ���� �³� �ƴѰ� üũ
		boolean isTarget = false;

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				int[] unit = new int[6];//6���� ��� ����

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
							colListRow.add(i);//����..�����Ѱ�..?
							colListCol.add(tempj-1);
						}
						if(scoreMap[i][tempj]==-10000&&tempj<=map.length-1) {
							colListRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
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

				int[] unit = new int[6];//6���� ��� ����

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
							rowListRow.add(tempi-1);//����..�����Ѱ�..?
							rowListCol.add(j);
						}
						if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//�̰�  dia1�� �����ؾ� �ϴºκ�
							rowListRow.add(tempi+1);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
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

				int[] unit = new int[6];//6���� ��� ����

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
							dia1ListRow.add(tempi-1);//�밢�� ���� ���� �Ʒ���
							dia1ListCol.add(tempj+1);
						}
						if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempj+1<map.length) {
							dia1ListRow.add(tempi+1);//�밢�� ���� ������ �� ��
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

				int[] unit = new int[6];//6���� ��� ����

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
							dia2ListRow.add(tempi-1);//�밢�� ���� ���� �Ʒ���
							dia2ListCol.add(tempj-1);
						}
						if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi+1<map.length) {
							dia2ListRow.add(tempi+1);//�밢�� ���� ������ �� ��
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
