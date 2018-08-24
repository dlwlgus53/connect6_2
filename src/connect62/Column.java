package connect62;
import java.util.ArrayList;
import java.io.*;


public class Column {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer = new FileWriter("log.txt");;
	
	Column(int[][] map,double[][]scoreMap,int myColor) throws IOException{
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
	
	}

	double[][] execute() throws IOException {
		findMyFive();
		findMyFour();
		findEnemyFive();
		findEnemyFour();
		findMine();
		findEnemy();
		return scoreMap;
	}

	void findMyFive() throws IOException {

		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

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

				if(isEnemy==false && count==5) {
					int tempj=j;

					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000&&(scoreMap[i][tempj]==0||scoreMap[i][tempj]>1)) {
							
							scoreMap[i][tempj]=1;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							writer.write("(" + i + ", " + j + ") col findmy5 " + 1);
						}
					}



				}
			}


		}
	}
	
	void findMyFour() throws IOException {
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

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

				if(isEnemy==false && count==4) {
					int tempj=j;

					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000&&(scoreMap[i][tempj]==0||scoreMap[i][tempj]>2.1)) {
							scoreMap[i][tempj]=2.1;//�̰Ŵ� 2.1
							writer.write("(" + i + ", " + j + ") col findmy4 "+ 2.1);
						}
					}



				}
			}


		}
	}
	
	void findMine() throws IOException {

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

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
					int tempj=j;
					switch(count){
					case 1 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0)
								scoreMap[i][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
								writer.write("(" + i + ", " + j + ") col findmy1 "+ 20);
						}
						break;
					case 2 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0)
								scoreMap[i][tempj]+=20;//6�� �ȿ� 2�� ������ �� �� ��ó�� 20�� �帳�ϴ�~
							writer.write("(" + i + ", " + j + ") col findmy2 "+ 20);
						} 
						break;

					case 3 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0)
								scoreMap[i][tempj]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
								writer.write("(" + i + ", " + j + ") col findmy3 "+ 100);
						}
						break;
					}
				}
			}
		}
	}

	void findEnemy() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� ���� ���� ������ ������ ĭ�� ������ 10�� �ο���
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ
		int[] unit = new int[6];//6���� ��� ����

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj = j;
				int index = 0;
				listRow.clear();
				listCol.clear();
				boolean isMine = false;

				for(k=0;k<6;k++,tempj++) {
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
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0) 
								scoreMap[i][tempj]+=10;
						}
						break;
					case 2:
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0)
								scoreMap[i][tempj]+=10;
						}
						break;
						
					case 3:
						for(tempj=j;tempj<j+6;tempj++) {
							if(tempj>=1) {
								listRow.add(i);//����..�����Ѱ�..?
								listCol.add(tempj-1);
							}
							if(tempj<=map.length-1) {
								listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
								listCol.add(tempj+1);
							}
						}
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
									scoreMap[listRow.get(index)][listCol.get(index)]%10==0)
								scoreMap[listRow.get(index)][listCol.get(index)]+=200;
							index++;
						}

						break;				
					}


				}
			}
		}
	}

	void findEnemyFive() {
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;

				boolean isMine=false;


				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = false;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false && count==5) {
					int tempj=j;

					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000&&(scoreMap[i][tempj]==0||scoreMap[i][tempj]>3)) {
							scoreMap[i][tempj]=3;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}



				}
			}


		}
	}
	
	void findEnemyFour() {
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;

				boolean isMine=false;


				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = false;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false && count==4) {
					int tempj=j;

					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000&&(scoreMap[i][tempj]==0||scoreMap[i][tempj]>4.1)) {
							scoreMap[i][tempj]=4.1;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}



				}
			}


		}
	}
		
	int[]copyToUnit(int[]unit, int row, int col){

		int k=0;
		unit[k] = map[row][col];
		unit[k+1]=map[row][col+1];
		unit[k+2]=map[row][col+2];
		unit[k+3]=map[row][col+3];
		unit[k+4]=map[row][col+4];
		unit[k+5]=map[row][col+5];

		return unit;
	}

}





