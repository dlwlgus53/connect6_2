package connect62;

import java.util.ArrayList;

public class Diagonal1 {
//x=y기울기를 가지는 대각선 방향친구.
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

	void findMyOne() {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 내 돌이 있으면 나머지 칸에 점수를 10점 부여해, 남의돌이 있는거는 고려를 안해.
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
							if(scoreMap[tempi][tempj]!=-10000) scoreMap[tempi][tempj]+=20;//내 돌 근처에 20점 드립니다~
						
					}

				}
			}
		}
	}

	void findMyThree() {//칸6개씩 볼거야, 근데 거기에 내꺼4개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
							scoreMap[tempi][tempj]+=100;//6칸에 3개만있고 상대방 돌 없으면 100점줍니다.
					}

				}
			}
		}

	}

	void findMyFour() {//칸6개씩 볼거야, 근데 거기에 내꺼4개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
							scoreMap[tempi][tempj]+=200;//6칸에 우리돌4개 상대방돌 없으면 200점 줍니다.
					}

				}
			}
		}

	}

/*	void findMyFive() {//세컨드 함수가 4개씩 있을때 점수를 준거라면 이거는 5개 있을때 점수를 줄거야. 상대방 돌이 있는거 신경을 안썼어..근데 상관없나..?아닌가..?
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
							scoreMap[i][tempj]+=400;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
						}
					}

				}
			}
		}

	}

	void findEnemyOne() {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 내 돌이 있으면 나머지 칸에 점수를 10점 부여해, 남의돌이 있는거는 고려를 안해.
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
	
	
	void findEnemyThree() {//6개씩 유닛을 만들어 그래가지구, 6개안에 내 돌이 없고, 상대방 돌만 3개가 있자나 그럼, 그 돌 양쪽에 점수를 주는걸로!랜덤리가 아니라!~
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
						listRow.add(i);//왼쪽..에만둘게..?
						listCol.add(tempj-1);
						listRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
						listCol.add(tempj+1);
					
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==3 && IsMine==false){//3개있고 없을때!
					int index=0;//이거는 리스트에서 쓸거
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=200;
						index++;
					}


				}
			}
		}

	}
		
	void findEnemyFour() {//6개씩 유닛을 만들어 그래가지구, 6개안에 내 돌이 없고, 상대방 돌만 3개가 있자나 그럼, 그 돌 양쪽에 점수를 주는걸로!랜덤리가 아니라!~
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
						listRow.add(i);//왼쪽..에만둘게..?
						listCol.add(tempj-1);
						listRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
						listCol.add(tempj+1);
					
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==4 && IsMine==false){//4개있고 없을때!
					int index=0;
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=400;//400점 드립니다~
						index++;
					}


				}
			}
		}

	}
	
	void findEnemyFive() {//6개씩 유닛을 만들어 그래가지구, 6개안에 내 돌이 없고, 상대방 돌만 3개가 있자나 그럼, 그 돌 양쪽에 점수를 주는걸로!랜덤리가 아니라!~
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
						listRow.add(i);//왼쪽..에만둘게..?
						listCol.add(tempj-1);
						listRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
						listCol.add(tempj+1);
					
					}
					if(unit[k]==myColor)	IsMine=true;
				}

				listRow.trimToSize();				
				if(count==5 && IsMine==false){//5개있고 옆에 내 돌이 없을때!
					int index=0;//이거는 리스트에서 쓸거
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000)
							scoreMap[listRow.get(index)][listCol.get(index)]+=500;//500점 드립니다~
						index++;
					}


				}
			}
		}

	}
*/
}
