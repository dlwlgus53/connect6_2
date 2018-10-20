package connect62;
import java.io.*;
import java.util.ArrayList;


public class Row extends CheckNScore{

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 


	Row(int[][] map,double[][]scoreMap,int myColor,FileWriter writer) throws IOException{
		super(map, scoreMap, myColor, writer);
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
	}

	double[][] execute() throws IOException{
		findMyFive();
		findMyFour();
		findMine();
		findEnemy();
		findEnemyFive();
		findEnemyFour();
		return scoreMap;
	}

	void findMyFive() throws IOException{

		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;

				boolean isEnemy=false;


				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(isEnemy==false&&count==5) {
					int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(checkMust(tempi,j,1)) {
							writer.append("(" + tempi + "," + j + ") row findmy5 "+ 1 +"\n");
							scoreMap[tempi][j]=scoreMust(scoreMap[tempi][j],1);//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}

				}
			}


		}
	}

	void findMyFour() throws IOException {

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;

				boolean isEnemy=false;


				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(isEnemy==false&&count==4) {
					int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(checkMust(tempi,j,2.2)) {
							writer.append("(" + tempi + "," + j + ") row findmy4 "+ 2.2 +"\n");
							scoreMap[tempi][j]=scoreMust(scoreMap[tempi][j],2.2);//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}



				}
			}


		}

	}

	void findMine() throws IOException {

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;

				boolean isEnemy=false;


				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(isEnemy==false) {
					int tempi=i;
					switch(count){
					case 1 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,j)) {
								writer.append("(" + tempi + "," + j + ") row findmy1 "+ 20 +"\n");
								scoreMap[tempi][j]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
							}

						}
						break;
					case 2 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,j)) {
								scoreMap[tempi][j]+=20;//6�� �ȿ� 2�� ������ �� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 40 +"\n");
							}
						} 
						break;

					case 3 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,j)) { 
								scoreMap[tempi][j]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 100 +"\n");
							}
						}
						break;
					case 6:
						System.out.println("you win...");
						System.exit(0);


					}
				}


			}
		}

	}

	void findEnemy() throws IOException {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� ���� ���� ������ ������ ĭ�� ������ 10�� �ο���
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ
		int[] unit = new int[6];//6���� ��� ����

		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempi = i;
				int index = 0;
				listRow.clear();
				listCol.clear();
				boolean isMine = false;

				for(k=0;k<6;k++,tempi++) {
					if(unit[k]==enemyColor) {
						count++;
					}
					if(unit[k]==myColor)
						isMine = true;

				}
				listRow.trimToSize();

				if(isMine==false) {
					switch(count) {
					case 1:
						for(tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,j)) {
								scoreMap[tempi][j]+=10;
								writer.append("(" + tempi + "," + j + ") row findene1 "+ 10 +"\n");
							}
						}
						break;
					case 2:
						for(tempi=i;tempi<i+6;tempi++) {
							if(check(tempi,j)) {
								scoreMap[tempi][j]+=10;
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 10 +"\n");
							}
						}
						break;
					case 3:
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
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000) {
								scoreMap[listRow.get(index)][listCol.get(index)]+=20;
								writer.append("(" + listRow.get(index) + "," + j + ") row findmy3 "+ 20 +"\n");
							}
							index++;
						}
						break;
					case 6:
						System.out.println("you lose...");
						System.exit(0);

					}


				}
			}
		}
	}

	void findEnemyFive() throws IOException {
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				//if(map[i][j]==enemyColor) {//modify


				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int blank=0;
				int blankRow=0;
				int blankCol=0;

				boolean isMine=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = true;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false&&count==5) {


					for(k=0;k<6;k++) {
						if(unit[k]==0) {
							blank=k;
						}
					}

					blankRow = i+blank;
					blankCol = j;



					if(scoreMap[blankRow][blankCol]>3||scoreMap[blankRow][blankCol]==0) {
						scoreMap[blankRow][blankCol]=scoreMust(scoreMap[blankRow][blankCol], 3);
						writer.append("(" + blankRow + "," + blankCol + ") row findEne5 "+ 3+"\n");							
					}

					if(blank==0) {
						if(i+6<map.length&&checkMust(i+6,j,3)){
							scoreMap[i+6][j]=scoreMust(scoreMap[i+6][j], 3);
							writer.append("(" + (i+6) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}

					}

					if(blank==1) {

						if(i+6<map.length&&checkMust(i+6,j,3)){
							scoreMap[i+6][j]=scoreMust(scoreMap[i+6][j], 3);
							writer.append("(" + (i+6) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}
					}

					if(blank==4) {
						if(i-1>=0&&j<map.length&&checkMust(i-1,j,3)){
							scoreMap[i-1][j]=scoreMust(scoreMap[i-1][j], 3);
							writer.append("(" + (i-1) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}
					}

					if(blank==5) {
						if(i-1>=0&&j<map.length&&checkMust(i-1,j,3)){
							scoreMap[i-1][j]=scoreMust(scoreMap[i-1][j], 3);
							writer.append("(" + (i-1) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}
					}




				}
			}
		}
	}





	void findEnemyFour() throws IOException {
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ

		int[] unit = new int[6];

		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==enemyColor) {//�̰Ŷ�1

					unit=copyToUnit(unit,i,j);
					int k=0;
					int count=0;
					int index =0;
					int blank=0;
					int blankRow=0;
					int blankCol=0;
					listRow.clear();
					listCol.clear();
					boolean isMine=false;//�̰Ŵٶ�					



					for(k=0;k<6;k++) {
						if(unit[k]==myColor)
							isMine = true;
						if(unit[k]==enemyColor)
							count++;
					}

					if(isMine==false&&count==4) {
						for(k=0;k<4;k++) {
							if(unit[k]==0) {
								blank=k;
							}
						}//�̰� ����
						
						blankRow = i+blank;
						blankCol = j;

						if(blank!=0) {
							if(checkMust(blankRow,blankCol,4.2)) {
								scoreMap[blankRow][blankCol]=scoreMust(scoreMap[blankRow][blankCol],4.2);
								writer.append("(" + blankRow + "," + blankCol + ") dia2 findEne4 "+ 4.2+"\n");
								return;//�̰͵� �ؾ���.
							}
							
						}//�̰� ���� 5ģ������ ������ž�, ���� �ٲ����.
						
						int tempi = i;
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
							if(checkMust(listRow.get(index),listCol.get(index),4.2)){
								scoreMap[listRow.get(index)][listCol.get(index)]
										=scoreMust(scoreMap[listRow.get(index)][listCol.get(index)], 4.2);
								writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") row findene4 "+ 4.2 +"\n");
							}
							index++;
						}
					}
				}


			}
		}

	}

	



	int[]copyToUnit(int[]unit, int row, int col){

		int k=0;
		unit[k] = map[row][col];
		unit[k+1]=map[row+1][col];
		unit[k+2]=map[row+2][col];
		unit[k+3]=map[row+3][col];
		unit[k+4]=map[row+4][col];
		unit[k+5]=map[row+5][col];

		return unit;
	}
}