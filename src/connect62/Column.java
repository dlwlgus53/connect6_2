package connect62;
import java.util.ArrayList;
import java.io.*;


public class Column {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 

	Column(int[][] map,double[][]scoreMap,int myColor, FileWriter writer) throws IOException{
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

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
							scoreMap[i][tempj]=1;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							writer.append("(" + i + "," + tempj + ") colfindmy5 " + 1 +"\n");
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
							scoreMap[i][tempj]=2.1;//이거는 2.1
							writer.append("(" + i + "," + tempj + ") col findmy4 "+ 2.1 +"\n");
						}
					}



				}
			}


		}
	}

	void findMine() throws IOException {

		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
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
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0) {
								scoreMap[i][tempj]+=20;//내 돌 근처에 20점 드립니다~
								writer.append("(" + i + "," + tempj + ") col findmy1 "+ 20 +"\n");
							}

						}
						break;
					case 2 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0) {
								scoreMap[i][tempj]+=20;//6개 안에 2개 있을때 내 돌 근처에 20점 드립니다~
								writer.append("(" + i + "," + tempj + ") col findmy2 "+ 20 +"\n");
							}
						} 
						break;

					case 3 : 
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0) {
								scoreMap[i][tempj]+=100;//6칸에 3개만있고 상대방 돌 없으면 100점줍니다.
								writer.append("(" + i + "," + tempj + ") col findmy3 "+ 100 +"\n");
							}
						}
						break;
					case 6:
						System.out.println("you lose...");						
						System.exit(0);

					}
				}
			}
		}
	}

	void findEnemy() throws IOException {//6칸을 유닛으로 떼어 내 그래서 그 6칸안에 상대방 돌이 있으면 나머지 칸에 점수를 10점 부여해
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row를 담을 리스트
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col을 담을 리스트
		int[] unit = new int[6];//6개씩 떼어서 생각

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
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0) {
								scoreMap[i][tempj]+=10;
								writer.append("(" + i + "," + tempj + ") col findene1 "+ 10 +"\n");
							}
						}
						break;
					case 2:
						for(tempj=j;tempj<j+6;tempj++) {
							if(scoreMap[i][tempj]!=-10000&&scoreMap[i][tempj]%10==0) {
								scoreMap[i][tempj]+=10;
								writer.append("(" + i + "," + tempj + ") col findene2 "+ 10 +"\n");
							}
						}
						break;

					case 3:
						for(tempj=j;tempj<j+6;tempj++) {
							if(tempj>=1) {
								listRow.add(i);//왼쪽..에만둘게..?
								listCol.add(tempj-1);
							}
							if(tempj<=map.length-1) {
								listRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
								listCol.add(tempj+1);
							}
						}
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
									scoreMap[listRow.get(index)][listCol.get(index)]%10==0)
							{scoreMap[listRow.get(index)][listCol.get(index)]+=200;
							writer.append("(" + i + "," + tempj + ") col findene3 "+ 200 +"\n");
							}

							index++;
						}

						break;			
					case 6:
						System.out.println("you lose...");
						System.exit(0);

					}



				}
			}
		}
	}

	void findEnemyFive() throws IOException {
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
							writer.append("(" + i + "," + tempj + ") col findene5 "+ 3 +"\n");
							scoreMap[i][tempj]=3;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
						}
					}



				}
			}


		}
	}

	void findEnemyFour() throws IOException {
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
							writer.append("(" + i + "," + tempj + ") col findene4 "+ 4.1 +"\n");
							scoreMap[i][tempj]=4.1;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
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





