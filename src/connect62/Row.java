package connect62;
import java.io.*;
import java.util.ArrayList;


public class Row {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer;

	Row(int[][] map,double[][]scoreMap,int myColor, FileWriter writer) throws IOException{
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

	}

	double[][] execute() throws IOException{
		findMyFive();
		findMyFour();
		findMine();
		findEnemy();
		findEnemyFive();
		findEnemyFour();
		return scoreMap;
	}

	void findMyFive() throws IOException{

		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

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

				if(isEnemy==false&&count==5) {
					int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]!=-10000&&(scoreMap[tempi][j]==0||scoreMap[tempi][j]>1)) {
							writer.append("(" + tempi + "," + j + ") row findmy5 "+ 1 +"\n");
							scoreMap[tempi][j]=1;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
						}
					}

				}
			}


		}
	}

	void findMyFour() throws IOException {

		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

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

				if(isEnemy==false&&count==4) {
					int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]!=-10000&&(scoreMap[tempi][j]==0||scoreMap[tempi][j]>2.2)) {
							writer.append("(" + tempi + "," + j + ") row findmy4 "+ 2.2 +"\n");
							scoreMap[tempi][j]=2.2;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
						}
					}



				}
			}


		}

	}

	void findMine() throws IOException {

		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

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
					int tempi=i;
					switch(count){
					case 1 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								writer.append("(" + tempi + "," + j + ") row findmy1 "+ 20 +"\n");
								scoreMap[tempi][j]+=20;//내 돌 근처에 20점 드립니다~
							}

						}
						break;
					case 2 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								scoreMap[tempi][j]+=20;//6개 안에 2개 있을때 내 돌 근처에 20점 드립니다~
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 40 +"\n");
							}
						} 
						break;

					case 3 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								scoreMap[tempi][j]+=100;//6칸에 3개만있고 상대방 돌 없으면 100점줍니다.
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 100 +"\n");
							}
						}
						break;
					case 6:
						System.out.println("you win...");
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

		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempi = i;
				int index = 0;
				listRow.clear();
				listCol.clear();
				boolean isMine = false;

				for(k=0;k<6;k++,tempi++) {
					if(unit[k]==enemyColor) {
						count++;
						if(tempi>=1) {
							listRow.add(i);//왼쪽..에만둘게..?
							listCol.add(tempi-1);
						}
						if(tempi<map.length-1) {//이고  dia1도 수정해야 하는부분
							listRow.add(i);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
							listCol.add(tempi+1);}

					}
					if(unit[k]==myColor)
						isMine = true;

				}
				listRow.trimToSize();

				if(isMine==false) {
					switch(count) {
					case 1:
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								scoreMap[tempi][j]+=10;
								writer.append("(" + tempi + "," + j + ") row findene1 "+ 10 +"\n");
							}
						}
						break;
					case 2:
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								scoreMap[tempi][j]+=10;
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 10 +"\n");
							}
						}
						break;
					case 3:
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]==-10000&&tempi>=1) {
								listRow.add(tempi-1);//왼쪽..에만둘게..?
								listCol.add(j);
							}
							if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//이고  dia1도 수정해야 하는부분
								listRow.add(tempi+1);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
								listCol.add(j);
							}
						}
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
									scoreMap[listRow.get(index)][listCol.get(index)]%10==0) {
								scoreMap[listRow.get(index)][listCol.get(index)]+=20;
								writer.append("(" + listRow.get(index) + "," + j + ") row findmy3 "+ 20 +"\n");
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
		//생각을 안해서 그걸 수정해서 넣어야해.//수정했슴당
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;

				boolean isMine=false;


				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = true;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false&&count==5) {
					int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]!=-10000&&(scoreMap[tempi][j]==0||scoreMap[tempi][j]>3)) {
							scoreMap[tempi][j]=3;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							writer.append("(" + tempi + "," + j + ") row findmy5 "+ 3 +"\n");
						}
					}



				}
			}


		}

	}

	void findEnemyFour() throws IOException {
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row를 담을 리스트
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col을 담을 리스트

		int[] unit = new int[6];
		
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int index =0;
				listRow.clear();
				listCol.clear();
				boolean isMine=false;


				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = true;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false&&count==4) {
					int tempi = i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]==-10000&&tempi>=1) {
							listRow.add(tempi-1);//왼쪽..에만둘게..?
							listCol.add(j);
						}
						if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//이고  dia1도 수정해야 하는부분
							listRow.add(tempi+1);//이것까지 해야할지 말아야 할지 모르겠어//이거는 오른쪽
							listCol.add(j);
						}
					}

					
				
					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
								(scoreMap[listRow.get(index)][listCol.get(index)]==0||
								scoreMap[listRow.get(index)][listCol.get(index)]>4.2)){
							scoreMap[listRow.get(index)][listCol.get(index)]=4.2;
							System.out.println("여기야?");
							writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") row findene4 "+ 4.2 +"\n");
						}
						index++;
					}
					
					
					/*int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]!=-10000&&(scoreMap[tempi][j]==0||scoreMap[tempi][j]>4.2)) {
							scoreMap[tempi][j]+=4.2;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							writer.append("(" + tempi + "," + j + ") row findmy4 "+ 4.2 +"\n");
						}
					}
					*/



				}
			}


		}

	}

	int[]copyToUnit(int[]unit, int row, int col){

		int k=0;
		unit[k] = map[row][col];
		unit[k+1]=map[row+1][col];
		unit[k+2]=map[row+2][col];
		unit[k+3]=map[row+3][col];
		unit[k+4]=map[row+4][col];
		unit[k+5]=map[row+5][col];

		return unit;
	}
}