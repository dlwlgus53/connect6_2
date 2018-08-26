package connect62;

import java.util.ArrayList;
import java.io.*;

public class Diagonal2{

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer;


	Diagonal2(int[][] map,double[][]scoreMap,int myColor ,FileWriter writer){
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
		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				boolean isEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(isEnemy==false&&count==5) {
					for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {
						if(scoreMap[tempi][tempj]!=-10000&&(scoreMap[tempi][tempj]==0||scoreMap[tempi][tempj]>1)) {
							scoreMap[tempi][tempj]=1;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							writer.append("(" + tempi + "," + tempj + ") dia2 findmy5 "+ 1 +"\n");
						}
					}


				}
			}


		}
	}

	void findMyFour() throws IOException{
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				boolean isEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(isEnemy==false&&count==4) {
					for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {
						if(scoreMap[tempi][tempj]!=-10000&&(scoreMap[tempi][tempj]==0||scoreMap[tempi][tempj]>2.4)){
							scoreMap[tempi][tempj]=2.4;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							writer.append("(" + tempi + "," + tempj + ") dia2 findmy4 "+ 2.4 +"\n");
						}
					}


				}
			}


		}
	}


	void findMine() throws IOException {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����3���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				boolean isEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(isEnemy==false) {

					switch(count){
					case 1 : 
						for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {

							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia2 findmy1 "+ 20 +"\n");
							}
						}
						break;
					case 2 : 
						for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0){
								scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia2 findmy2 "+ 20 +"\n");
							}
						}
						break;

					case 3 : 
						for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=100;//�� �� ��ó�� 100�� �帳�ϴ�~
								writer.append("(" + tempi + "," + tempj + ") dia2 findmy3 "+ 100 +"\n");
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

		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj = j;
				int tempi = i;
				int index = 0;
				listRow.clear();
				listCol.clear();
				boolean isMine = false;

				for(k=0;k<6;k++,tempi--,tempj--) {
					if(unit[k]==enemyColor) 
						count++;
					if(unit[k]==myColor)
						isMine = true;
				}

				listRow.trimToSize();


				if(isMine==false) {
					switch(count) {
					case 1:
						for(tempj=j,tempi=i;tempi>i-6;tempi--,tempj--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=10;
								writer.append("(" + tempi + "," + tempj + ") dia2 findene1 "+ 10 +"\n");
							}

						}
						break;
					case 2:
						for(tempj=j,tempi=i;tempi>i-6;tempi--,tempj--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0){
								scoreMap[tempi][tempj]+=10;
								writer.append("(" + tempi + "," + tempj + ") dia2 findene2 "+ 10 +"\n");
							}

						}
						break;
					case 3:
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
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
									scoreMap[listRow.get(index)][listCol.get(index)]%10==0) {
								scoreMap[listRow.get(index)][listCol.get(index)]+=200;
								writer.append("(" + tempi + "," + tempj + ") dia2 findene3 "+ 200 +"\n");
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
		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				boolean isMine=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = true;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false&&count==5) {
					for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {
						if(scoreMap[tempi][tempj]!=-10000&&(scoreMap[tempi][tempj]==0||scoreMap[tempi][tempj]>3)) {
							scoreMap[tempi][tempj]=3;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							writer.append("(" + tempi + "," + tempj + ") dia2 findene5 "+ 3 +"\n");
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
		for(int i=5;i<map.length;i++) {
			for(int j=5;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				int index=0;
				listRow.clear();
				listCol.clear();
				boolean isMine=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = true;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false&&count==4) {
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



					
					
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
								(scoreMap[listRow.get(index)][listCol.get(index)]==0||
								scoreMap[listRow.get(index)][listCol.get(index)]>4.4)){
							scoreMap[listRow.get(index)][listCol.get(index)]=4.4;
							writer.append("(" + tempi + "," + tempj + ") dia2 findene4 "+ 4.4 +"\n");
						}

						index++;
					}
					/*
					for(tempj=j,tempi=i;tempi>i-6&&tempj>j-6 ;tempi--,tempj--) {
						if(scoreMap[tempi][tempj]!=-10000&&(scoreMap[tempi][tempj]==0||scoreMap[tempi][tempj]>4.4)) {
							scoreMap[tempi][tempj]=4.4;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							writer.append("(" + tempi + "," + tempj + ") dia2 findene5 "+ 4.4 +"\n");
						}
					}*/


				}
			}


		}

	}



	int[]copyToUnit(int[]unit, int row, int col){

		int k=0;
		unit[k] = map[row][col];
		unit[k+1]=map[row-1][col-1];
		unit[k+2]=map[row-2][col-2];
		unit[k+3]=map[row-3][col-3];
		unit[k+4]=map[row-4][col-4];
		unit[k+5]=map[row-5][col-5];

		return unit;
	}


}

