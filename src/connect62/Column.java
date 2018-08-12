package connect62;
import java.util.ArrayList;


public class Column {

	int[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;

	Column(int[][] map,int[][]scoreMap,int myColor){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
	}

	int[][] execute() {
<<<<<<< HEAD
		findMine();
		findEnemy();
		return scoreMap;
	}

	void findMine() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����3���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
=======
		findMyOne();
		findMyThree();
		findMyFour();
		findMyFour();
		findMyFive();
		findEnemyOne();
		findEnemyThree();
		findEnemyFour();
		findEnemyFive();
		return scoreMap;
	}

	void findMyOne() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� �� ���� ������ ������ ĭ�� ������ 10�� �ο���, ���ǵ��� �ִ°Ŵ� ����� ����.
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i][j+1];
				unit[k+2]=map[i][j+2];
				unit[k+3]=map[i][j+3];
				unit[k+4]=map[i][j+4];
				unit[k+5]=map[i][j+5];

				boolean temp = false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
				}
				if(temp==true){
					int tempj;
					
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) scoreMap[i][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
						
					}

				}
			}
		}
	}

	void findMyThree() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
<<<<<<< HEAD
				boolean isEnemy=false;
=======
				boolean IsEnemy=false;
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf

				for(k=0;k<6;k++) {
					if(unit[k]==enemyColor)
						isEnemy = true;
					if(unit[k]==myColor)
						count++;
				}
<<<<<<< HEAD
				if(isEnemy==false) {
					int tempj=j;
					switch(count){
					case 1 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000)
								scoreMap[i][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
						}
						break;
					case 2 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) 
								scoreMap[i][tempj]+=20;//6�� �ȿ� 2�� ������ �� �� ��ó�� 20�� �帳�ϴ�~
						} 
						break;

					case 3 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000)
								scoreMap[i][tempj]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
						}
						break;
					case 4 :
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000)
								scoreMap[i][tempj]+=200;//6ĭ�� �츮��4�� ���浹 ������ 200�� �ݴϴ�.
						}
						break;
					case 5 :
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) {
								scoreMap[i][tempj]+=400;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							}
						}
=======

				if(count==3&&IsEnemy==false){
					int tempj;
					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000)
							scoreMap[i][tempj]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
					}
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf


					}
				}


			}
		}

	}

<<<<<<< HEAD

	void findEnemy() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� ���� ���� ������ ������ ĭ�� ������ 10�� �ο���
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ
		int[] unit = new int[6];//6���� ��� ����

=======
	void findMyFour() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
<<<<<<< HEAD
				int tempj = j;
				int index = 0;
				listRow.clear();
				listCol.clear();
				boolean isMine = false;
				
				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						if(tempj>=1) {
							listRow.add(i);//����..�����Ѱ�..?
							listCol.add(tempj-1);
						}
						if(tempj<=map.length-1) {
							listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
							listCol.add(tempj+1);}
=======
				boolean IsEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor*-1)
						IsEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(count==4&&IsEnemy==false){
					int tempj;
					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000)
							scoreMap[i][tempj]+=200;//6ĭ�� �츮��4�� ���浹 ������ 200�� �ݴϴ�.
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
					}
					if(unit[k]==myColor)
						isMine = true;

				}
				listRow.trimToSize();

				if(isMine==false) {
					switch(count) {
					case 1:
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) scoreMap[i][tempj]+=10;
						}
						break;
					case 2:
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) scoreMap[i][tempj]+=10;
						}
						break;
					case 3:
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
								scoreMap[listRow.get(index)][listCol.get(index)]+=200;
							index++;
						}
						break;
					case 4:
						System.out.println(listRow.size());
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
								scoreMap[listRow.get(index)][listCol.get(index)]+=400;
							System.out.printf("index : %d row : %d col :  %d\n", index, listRow.get(index), listCol.get(index));
							index++;
							
						}
						break;
					case 5:
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
								scoreMap[listRow.get(index)][listCol.get(index)]+=500;
							index++;
						}
						break;


<<<<<<< HEAD

=======
	void findMyFive() {//������ �Լ��� 4���� ������ ������ �ذŶ�� �̰Ŵ� 5�� ������ ������ �ٰž�. ���� ���� �ִ°� �Ű��� �Ƚ��..�ٵ� �������..?�ƴѰ�..?
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i][j+1];
				unit[k+2]=map[i][j+2];
				unit[k+3]=map[i][j+3];
				unit[k+4]=map[i][j+4];
				unit[k+5]=map[i][j+5];
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf


					}

				}


			}

		}

	}

<<<<<<< HEAD
	int[]copyToUnit(int[]unit, int row, int col){

		int k=0;
		unit[k] = map[row][col];
		unit[k+1]=map[row][col+1];
		unit[k+2]=map[row][col+2];
		unit[k+3]=map[row][col+3];
		unit[k+4]=map[row][col+4];
		unit[k+5]=map[row][col+5];

		return unit;
=======
	void findEnemyOne() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� �� ���� ������ ������ ĭ�� ������ 10�� �ο���, ���ǵ��� �ִ°Ŵ� ����� ����.
		int[] unit = new int[6];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i][j+1];
				unit[k+2]=map[i][j+2];
				unit[k+3]=map[i][j+3];
				unit[k+4]=map[i][j+4];
				unit[k+5]=map[i][j+5];

				boolean temp = false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
				}
				if(temp==true){
					int tempj;
					
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) scoreMap[i][tempj]+=10;
						
					}

				}
			}
		}
	}
	
	
	void findEnemyThree() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i][j+1];
				unit[k+2]=map[i][j+2];
				unit[k+3]=map[i][j+3];
				unit[k+4]=map[i][j+4];
				unit[k+5]=map[i][j+5];


				int count=0;
				int tempj=j;
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						listRow.add(i);//����..�����Ѱ�..?
						listCol.add(tempj-1);
						listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
						listCol.add(tempj+1);
					
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==3 && IsMine==false){//3���ְ� ������!
					int index=0;//�̰Ŵ� ����Ʈ���� ����
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=200;
						index++;
					}


				}
			}
		}

	}
		
	void findEnemyFour() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i][j+1];
				unit[k+2]=map[i][j+2];
				unit[k+3]=map[i][j+3];
				unit[k+4]=map[i][j+4];
				unit[k+5]=map[i][j+5];


				int count=0;
				int tempj=j;
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						listRow.add(i);//����..�����Ѱ�..?
						listCol.add(tempj-1);
						listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
						listCol.add(tempj+1);
					
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==4 && IsMine==false){//4���ְ� ������!
					int index=0;
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=400;//400�� �帳�ϴ�~
						index++;
					}


				}
			}
		}

	}
	
	void findEnemyFive() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i][j+1];
				unit[k+2]=map[i][j+2];
				unit[k+3]=map[i][j+3];
				unit[k+4]=map[i][j+4];
				unit[k+5]=map[i][j+5];


				int count=0;
				int tempj=j;
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						listRow.add(i);//����..�����Ѱ�..?
						listCol.add(tempj-1);
						listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
						listCol.add(tempj+1);
					
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==5 && IsMine==false){//5���ְ� ���� �� ���� ������!
					int index=0;//�̰Ŵ� ����Ʈ���� ����
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=500;//500�� �帳�ϴ�~
						index++;
					}


				}
			}
		}

>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
	}
}




