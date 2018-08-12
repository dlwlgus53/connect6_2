package connect62;

import java.util.ArrayList;

public class Diagonal1 {
	//x=y���⸦ ������ �밢�� ����ģ��.
	int[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;

	Diagonal1(int[][] map,int[][]scoreMap,int myColor){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
	}

	int[][] execute() {
		findMine();
	//	findMyOne();
		//findMyThree();
		//findMyFour();
		//findMyFour();
		//findMyFive();
		findEnemy();
		//findEnemyThree();
		//findEnemyFour();
		//findEnemyFive();
		return scoreMap;
	}
	void findMine() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����3���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

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
				System.out.println("count : " + count);
				if(isEnemy==false) {
					
					switch(count){
					case 1 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							System.out.println("i : " +tempi + " j : " + tempj);
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
						}
						break;
					case 2 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
						}
						break;

					case 3 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=100;//�� �� ��ó�� 100�� �帳�ϴ�~
						}
						break;
					case 4 :
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--)  {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=200;//6ĭ�� �츮��4�� ���浹 ������ 200�� �ݴϴ�.
						}
						break;
					case 5 :
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--)  {
							if(scoreMap[tempi][tempj]!=-10000) {
								scoreMap[tempi][tempj]+=400;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							}
						}


					}
				}


			}
		}

	}

/*	void findMyOne() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� �� ���� ������ 20�� �帳�ϴ�., ���ǵ��� �ִ°Ŵ� ����� ��.
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				
				unit=copyToUnit(unit,i,j);

				boolean temp = false;
				boolean isEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
					if(unit[k]==enemyColor)
						isEnemy=true;
				}
				if(temp==true&&isEnemy==false){
					int tempj;
					int tempi;
					for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~

					}

				}
			}
		}
	}

	void findMyThree() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);


				int count=0;
				boolean IsEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor*-1)
						IsEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(count==3&&IsEnemy==false){
					int tempj;
					int tempi;
					for(tempj=j,tempi =i;tempj<j+6;tempj++, tempi--) {
						if(scoreMap[tempi][tempj]!=-10000)
							scoreMap[tempi][tempj]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
					}

				}
			}
		}

	}

	void findMyFour() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);



				int count=0;
				boolean IsEnemy=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor*-1)
						IsEnemy = true;
					if(unit[k]==myColor)
						count++;
				}

				if(count==4&&IsEnemy==false){
					int tempj;
					int tempi;
					for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000)
							scoreMap[tempi][tempj]+=200;//6ĭ�� �츮��4�� ���浹 ������ 200�� �ݴϴ�.
					}

				}
			}
		}

	}

	/*void findMyFive() {//�̰Ŵ� 5�� ������ ������ �ٰž�. ���� ���� �ִ°� �Ű��� �Ƚ��..�ٵ� �������..?�ƴѰ�..?
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);


				int count=0;
				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						count++;
				}

				if(count==5){
					int tempj;
					int tempi;
					for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000) {
							scoreMap[tempi][tempj]+=400;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}

				}
			}
		}

	}*/

	
	
	void findEnemy() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� ���� ���� ������ ������ ĭ�� ������ 10�� �ο���
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ
		int[] unit = new int[6];//6���� ��� ����

		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj = j;
				int tempi = i;
				int index = 0;
				listRow.clear();
				listCol.clear();
				boolean isMine = false;
				
				for(k=0;k<6;k++,tempj++,tempi--) {
					if(unit[k]==enemyColor) {
						count++;
						if(tempi+1<map.length&&tempj-1>=0) {
							listRow.add(tempi+1);//�밢�� ���� ���� �Ʒ���
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//�밢�� ���� ������ �� ��
							listCol.add(tempj+1);
						}
					}
					if(unit[k]==myColor)
						isMine = true;
				}
				listRow.trimToSize();

				
				if(isMine==false) {
					switch(count) {
					case 1:
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=10;

						}
						break;
					case 2:
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=10;

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





					}

				}


			}

		}

	}
	
	
	/*void findEnemyOne() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� �� ���� ������ ������ ĭ�� ������ 10�� �ο���, �� ���� �ִ°Ŵ� ����� ����.
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);

				boolean temp = false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
				}
				if(temp==true){
					int tempj;
					int tempi;

					for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=10;

					}

				}
			}
		}
	}


	void findEnemyThree() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);


				int count=0;
				int tempj=j;
				int tempi=i;
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						if(tempi+1<map.length&&tempj-1>=0) {
							listRow.add(tempi+1);//�밢�� ���� ���� �Ʒ���
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//�밢�� ���� ������ �� ��
							listCol.add(tempj+1);
						}
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==3 && IsMine==false){//3���ְ� ������!
					int index=0;//�̰Ŵ� ����Ʈ���� ����
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=200;//200�� �帳�ϴ�~
						index++;
					}


				}
			}
		}

	}

	void findEnemyFour() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 4���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);



				int count=0;
				int tempj=j;
				int tempi=i;
				boolean IsMine=false;
//��..�߸𸣰ڴ� ��������߾�.
				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						if(tempi+1<map.length&&tempj-1>=0) {
							listRow.add(tempi+1);//�밢�� ���� ���� �Ʒ���
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//�밢�� ���� ������ �� ��
							listCol.add(tempj+1);
						}
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

		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);



				int count=0;
				int tempj=j;
				int tempi=i;
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						if(tempi+1<map.length&&tempj-1>=0) {
							listRow.add(tempi+1);//�밢�� ���� ���� �Ʒ���
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//�밢�� ���� ������ �� ��
							listCol.add(tempj+1);
						}

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

	}*/
	
	int[]copyToUnit(int[]unit, int row, int col){

		int k=0;
		unit[k] = map[row][col];
		unit[k+1]=map[row-1][col+1];
		unit[k+2]=map[row-2][col+2];
		unit[k+3]=map[row-3][col+3];
		unit[k+4]=map[row-4][col+4];
		unit[k+5]=map[row-5][col+5];

		return unit;
	}

}
