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

	void findMine() {//칸6개씩 볼거야, 근데 거기에 내꺼3개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
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

	void findMyOne() {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 내 돌이 있으면 나머지 칸에 점수를 10점 부여해, 남의돌이 있는거는 고려를 안해.
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
							if(scoreMap[i][tempj]!=-10000) scoreMap[i][tempj]+=20;//내 돌 근처에 20점 드립니다~
						
					}

				}
			}
		}
	}

	void findMyThree() {//칸6개씩 볼거야, 근데 거기에 내꺼4개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
								scoreMap[i][tempj]+=20;//내 돌 근처에 20점 드립니다~
						}
						break;
					case 2 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) 
								scoreMap[i][tempj]+=20;//6개 안에 2개 있을때 내 돌 근처에 20점 드립니다~
						} 
						break;

					case 3 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000)
								scoreMap[i][tempj]+=100;//6칸에 3개만있고 상대방 돌 없으면 100점줍니다.
						}
						break;
					case 4 :
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000)
								scoreMap[i][tempj]+=200;//6칸에 우리돌4개 상대방돌 없으면 200점 줍니다.
						}
						break;
					case 5 :
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000) {
								scoreMap[i][tempj]+=400;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							}
						}
=======

				if(count==3&&IsEnemy==false){
					int tempj;
					for(tempj=j;tempj<j+6;tempj++) {
						if(scoreMap[i][tempj]!=-10000)
							scoreMap[i][tempj]+=100;//6칸에 3개만있고 상대방 돌 없으면 100점줍니다.
					}
>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf


					}
				}


			}
		}

	}

<<<<<<< HEAD

	void findEnemy() {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 상대방 돌이 있으면 나머지 칸에 점수를 10점 부여해
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row를 담을 리스트
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col을 담을 리스트
		int[] unit = new int[6];//6개씩 떼어서 생각

=======
	void findMyFour() {//칸6개씩 볼거야, 근데 거기에 내꺼4개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
							listRow.add(i);//왼쪽..에만둘게..?
							listCol.add(tempj-1);
						}
						if(tempj<=map.length-1) {
							listRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
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
							scoreMap[i][tempj]+=200;//6칸에 우리돌4개 상대방돌 없으면 200점 줍니다.
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
	void findMyFive() {//세컨드 함수가 4개씩 있을때 점수를 준거라면 이거는 5개 있을때 점수를 줄거야. 상대방 돌이 있는거 신경을 안썼어..근데 상관없나..?아닌가..?
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

>>>>>>> 28d60695390135ae693e1d824984358d4dc37ddf
	}
}




