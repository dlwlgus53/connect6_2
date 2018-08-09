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
		findMyOne();
		//findMyThree();
		//findMyFour();
		//findMyFour();
		//findMyFive();
		//findEnemyOne();
		///findEnemyThree();
		//findEnemyFour();
		//findEnemyFive();
		return scoreMap;
	}

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

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						temp = true;
				}
				if(temp==true){
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
			for(int j=0;j<map.length-6;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i-1][j+1];
				unit[k+2]=map[i-2][j+2];
				unit[k+3]=map[i-3][j+3];
				unit[k+4]=map[i-4][j+4];
				unit[k+5]=map[i-5][j+5];


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
			for(int j=0;j<map.length-6;j++) {
				int k=0;
				unit[k] = map[i][j];
				unit[k+1]=map[i-1][j+1];
				unit[k+2]=map[i-2][j+2];
				unit[k+3]=map[i-3][j+3];
				unit[k+4]=map[i-4][j+4];
				unit[k+5]=map[i-5][j+5];



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


				int count=0;
				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						count++;
				}
				if(count==5){
					int tempj;
					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000) {
							scoreMap[i][tempj]+=400;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}

				}
			}
		}

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

	}
*/
}
