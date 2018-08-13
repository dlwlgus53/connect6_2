package connect62;

import java.util.ArrayList;

public class Diagonal1 {

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
		findEnemy();
		return scoreMap;
	}

	void findMine() {//칸6개씩 볼거야, 근데 거기에 내꺼3개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
								scoreMap[tempi][tempj]+=20;//내 돌 근처에 20점 드립니다~
						}
						break;
					case 2 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=20;//내 돌 근처에 20점 드립니다~
						}
						break;

					case 3 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 100점 드립니다~
						}
						break;
					case 4 :
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--)  {
							if(scoreMap[tempi][tempj]!=-10000)
								scoreMap[tempi][tempj]+=200;//6칸에 우리돌4개 상대방돌 없으면 200점 줍니다.
						}
						break;
					case 5 :
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--)  {
							if(scoreMap[tempi][tempj]!=-10000) {
								scoreMap[tempi][tempj]+=400;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							}
						}


					}
				}


			}
		}

	}



	void findEnemy() {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 상대방 돌이 있으면 나머지 칸에 점수를 10점 부여해
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row를 담을 리스트
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col을 담을 리스트
		int[] unit = new int[6];//6개씩 떼어서 생각

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
							listRow.add(tempi+1);//대각선 방향 왼쪽 아래돌
							listCol.add(tempj-1);
						}
						if(tempi-1>=0&&tempj+1<map.length) {
							listRow.add(tempi-1);//대각선 방향 오른쪽 위 돌
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


