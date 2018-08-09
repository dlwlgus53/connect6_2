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

	void findOne() {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 내 돌이 있으면 나머지 칸에 점수를 10점 부여해, 남의돌이 있는거는 고려를 안해.
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
	
	void findThree() {//칸6개씩 볼거야, 근데 거기에 내꺼4개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
							scoreMap[i][tempj]+=100;//6칸에 3개만있고 상대방 돌 없으면 100점줍니다.
					}

				}
			}
		}

	}
	
	

	void findFour() {//칸6개씩 볼거야, 근데 거기에 내꺼4개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
							scoreMap[i][tempj]+=200;//6칸에 우리돌4개 상대방돌 없으면 200점 줍니다.
					}

				}
			}
		}

	}


	void findFive() {//세컨드 함수가 4개씩 있을때 점수를 준거라면 이거는 5개 있을때 점수를 줄거야. 상대방 돌이 있는거 신경을 안썼어..근데 상관없나..?아닌가..?
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
}




