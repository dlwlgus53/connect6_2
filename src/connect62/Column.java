package connect62;

public class Column {
	int[][]scoreMap;
	int[][]map;
	int myColor;

	Column(int[][] map,int[][]scoreMap,int myColor){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
	}

	int[][] execute() {
		findOne();
		findThree();
		findFour();
		findFour();
		return scoreMap;
	}

	void findOne() {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� �� ���� ������ ������ ĭ�� ������ 10�� �ο���, ���ǵ��� �ִ°Ŵ� ����� ����.
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
						scoreMap[i][tempj]+=10;
					}

				}
			}
		}
	}
	
	void findThree() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
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
				boolean IsEnemy=false;
				
				for(k=0;k<6;k++) {
					if(unit[k]==myColor*-1)
						IsEnemy = true;
					if(unit[k]==myColor)
						count++;
				}
				
				if(count==3&&IsEnemy==false){
					int tempj;
					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000)
							scoreMap[i][tempj]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
					}

				}
			}
		}

	}
	
	

	void findFour() {//ĭ6���� ���ž�, �ٵ� �ű⿡ ����4���� ������ �ٸ��� ������ϰ� ���� �ű�� ���ƾ� ���ڳ� �׷��� �������� �ٰž�, �ٵ� ���� ���� �߰��� ���� �������ִٴ°�
		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
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
					}

				}
			}
		}

	}


	void findFive() {//������ �Լ��� 4���� ������ ������ �ذŶ�� �̰Ŵ� 5�� ������ ������ �ٰž�. ���� ���� �ִ°� �Ű��� �Ƚ��..�ٵ� �������..?�ƴѰ�..?
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
}




