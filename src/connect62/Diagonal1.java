package connect62;

import java.util.ArrayList;

public class Diagonal1 {
<<<<<<< HEAD
	//x=y���⸦ ������ �밢�� ����ģ��.
=======
//x=y���⸦ ������ �밢�� ����ģ��.
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
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
<<<<<<< HEAD
		findMine();
	//	findMyOne();
=======
		findMyOne();
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
		//findMyThree();
		//findMyFour();
		//findMyFour();
		//findMyFive();
<<<<<<< HEAD
		findEnemy();
		//findEnemyThree();
=======
		//findEnemyOne();
		///findEnemyThree();
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
		//findEnemyFour();
		//findEnemyFive();
		return scoreMap;
	}
<<<<<<< HEAD
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
=======

	void findMyOne() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� �� ���� ������ ������ ĭ�� ������ 10�� �ο���, ���ǵ��� �ִ°Ŵ� ����� ����.
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i-1][j+1];
				unit[k+2]=map[i-2][j+2];
				unit[k+3]=map[i-3][j+3];
				unit[k+4]=map[i-4][j+4];
				unit[k+5]=map[i-5][j+5];

				boolean temp = false;
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
<<<<<<< HEAD
					if(unit[k]==enemyColor)
						isEnemy=true;
				}
				if(temp==true&&isEnemy==false){
					int tempj;
					int tempi;
					for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~

=======
				}
				if(temp==true){
					int tempj;
					int tempi;
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
						
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
					}

				}
			}
		}
	}

	void findMyThree() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
<<<<<<< HEAD
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);
=======
			for(int j=0;j<map.length-6;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i-1][j+1];
				unit[k+2]=map[i-2][j+2];
				unit[k+3]=map[i-3][j+3];
				unit[k+4]=map[i-4][j+4];
				unit[k+5]=map[i-5][j+5];
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf


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
<<<<<<< HEAD
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);
=======
			for(int j=0;j<map.length-6;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i-1][j+1];
				unit[k+2]=map[i-2][j+2];
				unit[k+3]=map[i-3][j+3];
				unit[k+4]=map[i-4][j+4];
				unit[k+5]=map[i-5][j+5];
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf



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

<<<<<<< HEAD
	/*void findMyFive() {//�̰Ŵ� 5�� ������ ������ �ٰž�. ���� ���� �ִ°� �Ű��� �Ƚ��..�ٵ� �������..?�ƴѰ�..?
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);
=======
/*	void findMyFive() {//������ �Լ��� 4���� ������ ������ �ذŶ�� �̰Ŵ� 5�� ������ ������ �ٰž�. ���� ���� �ִ°� �Ű��� �Ƚ��..�ٵ� �������..?�ƴѰ�..?
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


				int count=0;
				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						count++;
				}
<<<<<<< HEAD

				if(count==5){
					int tempj;
					int tempi;
					for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000) {
							scoreMap[tempi][tempj]+=400;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
=======
				if(count==5){
					int tempj;
					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000) {
							scoreMap[i][tempj]+=400;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
						}
					}

				}
			}
		}

<<<<<<< HEAD
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
=======
	}

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
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf

				boolean temp = false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
				}
				if(temp==true){
					int tempj;
<<<<<<< HEAD
					int tempi;

					for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=10;

=======
					
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) scoreMap[i][tempj]+=10;
						
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
					}

				}
			}
		}
	}
<<<<<<< HEAD


=======
	
	
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
	void findEnemyThree() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

<<<<<<< HEAD
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);
=======
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


				int count=0;
				int tempj=j;
<<<<<<< HEAD
				int tempi=i;
=======
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
<<<<<<< HEAD
						if(tempi+1<map.length&&tempj-1>=0) {
							listRow.add(tempi+1);//�밢�� ���� ���� �Ʒ���
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//�밢�� ���� ������ �� ��
							listCol.add(tempj+1);
						}
=======
						listRow.add(i);//����..�����Ѱ�..?
						listCol.add(tempj-1);
						listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
						listCol.add(tempj+1);
					
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==3 && IsMine==false){//3���ְ� ������!
					int index=0;//�̰Ŵ� ����Ʈ���� ����
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
<<<<<<< HEAD
							scoreMap[listRow.get(index)][listCol.get(index)]+=200;//200�� �帳�ϴ�~
=======
							scoreMap[listRow.get(index)][listCol.get(index)]+=200;
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
						index++;
					}


				}
			}
		}

	}
<<<<<<< HEAD

	void findEnemyFour() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 4���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
=======
		
	void findEnemyFour() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

<<<<<<< HEAD
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);

=======
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


				int count=0;
				int tempj=j;
<<<<<<< HEAD
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
=======
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
						listRow.add(i);//����..�����Ѱ�..?
						listCol.add(tempj-1);
						listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
						listCol.add(tempj+1);
					
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
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
<<<<<<< HEAD

=======
	
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
	void findEnemyFive() {//6���� ������ ����� �׷�������, 6���ȿ� �� ���� ����, ���� ���� 3���� ���ڳ� �׷�, �� �� ���ʿ� ������ �ִ°ɷ�!�������� �ƴ϶�!~
		int[] unit = new int[6];
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);

<<<<<<< HEAD
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {
				int k=0;
				unit=copyToUnit(unit,i,j);

=======
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


				int count=0;
				int tempj=j;
<<<<<<< HEAD
				int tempi=i;
=======
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
				boolean IsMine=false;

				for(k=0;k<6;k++,tempj++) {
					if(unit[k]==enemyColor) {
						count++;
<<<<<<< HEAD
						if(tempi+1<map.length&&tempj-1>=0) {
							listRow.add(tempi+1);//�밢�� ���� ���� �Ʒ���
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//�밢�� ���� ������ �� ��
							listCol.add(tempj+1);
						}

=======
						listRow.add(i);//����..�����Ѱ�..?
						listCol.add(tempj-1);
						listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
						listCol.add(tempj+1);
					
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
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

<<<<<<< HEAD
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

=======
	}
*/
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
}
