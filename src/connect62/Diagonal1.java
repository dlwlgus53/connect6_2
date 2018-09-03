package connect62;

import java.util.ArrayList;
import java.io.*;

public class Diagonal1 {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer; 
	FindBetter findBetter;

	Diagonal1(int[][] map,double[][]scoreMap,int myColor, FileWriter writer){
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;
	}

	double[][] execute() throws IOException {
		findBetter = new FindBetter(map,scoreMap,myColor);
		findMyFive();
		findMyFour();
		findMine();
		findEnemy();
		findEnemyFive();
		findEnemyFour();
		return scoreMap;
	}

	void findMyFive() throws IOException {
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

				if(isEnemy==false&&count==5) {				
					for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--)  {
						if(scoreMap[tempi][tempj]!=-10000&&(scoreMap[tempi][tempj]==0||scoreMap[tempi][tempj]>1)) {
							scoreMap[tempi][tempj]=1;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							writer.append("(" + tempi + "," + tempj + ") dia1 findmy5 "+ 1 +"\n");
						}
					}


				}
			}


		}
	}

	void findMyFour() throws IOException {

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

				if(isEnemy==false&&count==4) {				
					for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--)  {
						if(scoreMap[tempi][tempj]!=-10000&&(scoreMap[tempi][tempj]==0||scoreMap[tempi][tempj]>2.3)) {
							scoreMap[tempi][tempj]=2.3;//6칸안에 우리돌 5개 상대방 돌 없으면 400점 줍니다.
							writer.append("(" + tempi + "," + tempj + ") dia1 findmy4 "+ 2.3 +"\n");
						}
					}


				}
			}


		}


	}

	void findMine() throws IOException {//칸6개씩 볼거야, 근데 거기에 내꺼3개가 있으면 다른거 상관안하고 당장 거기다 놓아야 하자나 그래서 가산점을 줄거야, 근데 남의 돌이 중간에 끼어 있을수있다는걸
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

				if(isEnemy==false) {

					switch(count){
					case 1 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {

							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=20;//내 돌 근처에 20점 드립니다~
								writer.append("(" + tempi + "," + tempj + ") dia1 findmy1 "+ 20 +"\n");
							}
						}
						break;
					case 2 : 
						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0)
								scoreMap[tempi][tempj]+=20;//내 돌 근처에 20점 드립니다~
							writer.append("(" + tempi + "," + tempj + ") dia1 findmy2 "+ 40 +"\n");
						}
						break;

					case 3 : 

						for(tempj=j,tempi=i;tempj<j+6&&tempi>i-6 ;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0)
								scoreMap[tempi][tempj]+=100;//내 돌 근처에 100점 드립니다~
							writer.append("(" + tempi + "," + tempj + ") dia1 findmy3 "+ 100 +"\n");
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

					}
					if(unit[k]==myColor)
						isMine = true;
				}



				if(isMine==false) {
					switch(count) {
					case 1:
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=10;
								writer.append("(" + tempi + "," + tempj + ") dia1 findene1 "+ 10 +"\n");
							}


						}
						break;
					case 2:
						for(tempj=j,tempi=i;tempj<j+6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]!=-10000&&scoreMap[tempi][tempj]%10==0) {
								scoreMap[tempi][tempj]+=10;
								writer.append("(" + tempi + "," + tempj + ") dia1 findene2 "+ 10 +"\n");
							}

						}
						break;
					case 3:
						for(tempi=i, tempj=j;tempi>i-6;tempj++,tempi--) {
							if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi-1>=0) {
								listRow.add(tempi-1);//대각선 방향 왼쪽 아래돌
								listCol.add(tempj+1);
							}
							if(scoreMap[tempi][tempj]==-10000&&tempj-1>=0&&tempj+1<map.length) {
								listRow.add(tempi+1);//대각선 방향 오른쪽 위 돌
								listCol.add(tempj-1);
							}
						}
						while(index<listRow.size()) {
							if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
									scoreMap[listRow.get(index)][listCol.get(index)]%10==0) {
								scoreMap[listRow.get(index)][listCol.get(index)]+=20;
								writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") dia1 findene3 "+ 20 +"\n");
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
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				int blank=0;
				int blankRow=0;
				int blankCol=0;

				boolean isMine=false;

				for(k=0;k<6;k++) {
					if(unit[k]==myColor)
						isMine = true;
					if(unit[k]==enemyColor)
						count++;
				}

				if(isMine==false&&count==5) {


					for(k=0;k<6;k++) {
						if(unit[k]==0) {
							blank=k;
						}
					}

					blankRow = i-blank;
					blankCol = j+blank;


					if(scoreMap[blankRow][blankCol]>3||scoreMap[blankRow][blankCol]==0) {

						scoreMap[blankRow][blankCol]=3;
						writer.append("(" + blankRow + "," + blankCol + ") dia1 findEne5 "+ 3+"\n");							
					}


					if(blank==0) {
						if(i-6>=0&&j+6<map.length&&(scoreMap[i-6][j+6]>3||scoreMap[i-6][j+6]==0)){
							scoreMap[i-6][j+6]=3;
							writer.append("(" + (i-6) + "," + (j+6) + ") dia1 findEne5 "+ 3+"\n");	
						}
					}

					if(blank==1) {

						if(i-6>=0&&j+6<map.length&&(scoreMap[i-6][j+6]>3||scoreMap[i-6][j+6]==0)){
							scoreMap[i-6][j+6]=3;
							writer.append("(" + (i-6) + "," + (j+6) + ") dia1 findEne5 "+ 3+"\n");	
						}
					}

					/*if(blank==2||blank==3) {
						boolean case1 = false;
						boolean case2= false;

						if(i-6>=0&&j+6<map.length){
							case1=true;//왼쪽 위
						}
						if(i+1<map.length&&j-1>=0){
							case2 = true;//오른쪽 아래
						}
						if(case1==true && case2 ==false) {
							if(scoreMap[i-6][j+6]>3||scoreMap[i-6][j+6]==0){
								scoreMap[i-6][j+6]=3;
								writer.append("(" + (i-6) + "," + (j+6) + ") dia1 findEne5 "+ 3+"\n");	
							}

						}

						if(case1==false && case2==true) {
							if(scoreMap[i+1][j-1]>3||scoreMap[i+1][j-1]==0){
								scoreMap[i+1][j-1]=3;
								writer.append("(" + (i+1) + "," + (j-1) + ") dia1 findEne5 "+ 3+"\n");	
							}
						}

						if(case1==true && case2==true) {
							int score1=findBetter.execute(i-6,j+6);
							int score2=findBetter.execute(i+1, j-1);

							if(score1>=score2) {
								if(scoreMap[i-6][j+6]>3||scoreMap[i-6][j+6]==0){
									scoreMap[i-6][j+6]=3;
									writer.append("(" + (i-6) + "," + (j+6) + ") dia1 findEne5 "+ 3+"\n");	
								}
							}
							else {
								if(scoreMap[i+1][j-1]>3||scoreMap[i+1][j-1]==0){
									scoreMap[i+1][j-1]=3;
									writer.append("(" + (i+1) + "," + (j-1) + ") dia1 findEne5 "+ 3+"\n");	
								}
							}
						}

					}*/

					if(blank==4) {
						if(i+1<map.length&&j-1>=0&&(scoreMap[i+1][j-1]>3||scoreMap[i+1][j-1]==0)){
							scoreMap[i+1][j-1]=3;
							writer.append("(" + (i+1) + "," + (j-1) + ") dia1 findEne5 "+ 3+"\n");	
						}
					}

					if(blank==5) {
						if(i+1<map.length&&j-1>=0&&(scoreMap[i+1][j-1]>3||scoreMap[i+1][j-1]==0)){
							scoreMap[i+1][j-1]=3;
							writer.append("(" + (i+1) + "," + (j-1) + ") dia1 findEne5 "+ 3+"\n");	
						}
					}



				}
			}
		}
	}


	void findEnemyFour() throws IOException {
		ArrayList<Integer> blankRow = new ArrayList<Integer>(0);
		ArrayList<Integer> blankCol = new ArrayList<Integer>(0);

		int[] unit = new int[6];

		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==enemyColor) {

					unit=copyToUnit(unit,i,j);

					int k=0;
					int count=0;
					int blankCount=0;
					blankRow.clear();
					blankCol.clear();

					boolean isMine=false;

					for(k=0;k<6;k++) {
						if(unit[k]==myColor)
							isMine = true;
						if(unit[k]==enemyColor) {
							count++;
						}
						if(k<4&&unit[k]==0) {
							blankCount++;
							blankRow.add(i-k);
							blankCol.add(j+k);
						}
					}



					if (isMine==false && count==4) {
						if (blankCount==0) {
							helpEnemy4(i,j);
						}

						if(blankCount==1) {
							int score1=findBetter.execute(blankRow.get(0), blankRow.get(0));

							if(score1>0&&(scoreMap[blankRow.get(0)][blankCol.get(0)]>4.3||scoreMap[blankRow.get(0)][blankCol.get(0)]==0)) {
								scoreMap[blankRow.get(0)][blankCol.get(0)]=4.3;
								writer.append("(" + blankRow.get(0) + "," + blankCol.get(0) + ") dia1 findene4 "+ 4.3 +"\n");
							}

							else if(score1==0) {
								helpEnemy4(i,j);
							}
						}

						if(blankCount==2) {
							int score1=0;
							int score2=0;
							score1=findBetter.execute(blankRow.get(0), blankCol.get(0));
							score2=findBetter.execute(blankRow.get(1), blankCol.get(1));

							if (score1==0&&score2==0) {
								helpEnemy4(i,j);
							}


							else if(score1>=score2&&
									(scoreMap[blankRow.get(0)][blankCol.get(0)]>4.3||scoreMap[blankRow.get(0)][blankCol.get(0)]==0)) {
								scoreMap[blankRow.get(0)][blankCol.get(0)]=4.3;
								writer.append("(" + blankRow.get(0) + "," + blankCol.get(0) + ") dia1 findene4 "+ 4.3 +"\n");

							}

							else if(score1<score2&&
									(scoreMap[blankRow.get(1)][blankCol.get(1)]>4.3||scoreMap[blankRow.get(1)][blankCol.get(1)]==0)) {
								scoreMap[blankRow.get(1)][blankCol.get(1)]=4.3;
								writer.append("(" + blankRow.get(1) + "," + blankCol.get(1) + ") dia1 findene4 "+ 4.3 +"\n");
							}


						}




					}
				}
			}
		}


		}

		private void helpEnemy4(int i, int j) throws IOException {

			if(i-4>=0&&j+4<map.length&&map[i-4][j+4]==0&&(scoreMap[i-4][j+4]>4.3||scoreMap[i-4][j+4]==0)) {
				scoreMap[i-4][j+4]=4.3;
				writer.append("(" + (i-4) + "," +j+4 + ") dia1 findene4 "+ 4.3 +"\n");
			}
			if(i+1<map.length&&j-1>=0&&map[i+1][j-1]==0&&(scoreMap[i+1][j-1]>4.3||scoreMap[i+1][j-1]==0)) {
				scoreMap[i+1][j-1]=4.3;
				writer.append("(" + (i+1) + "," + (j-1) + ") dia1 findene4 "+ 4.3 +"\n");
			}

		}

		/*
	void findEnemyFour() throws IOException {
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row를 담을 리스트
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col을 담을 리스트
		int[] unit = new int[6];
		for(int i=5;i<map.length;i++) {
			for(int j=0;j<map.length-6+1;j++) {

				unit=copyToUnit(unit,i,j);

				int k=0;
				int count=0;
				int tempj=j;
				int tempi=i;
				int index = 0;
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
					for(tempi=i, tempj=j;tempi>i-6;tempj++,tempi--) {
						if(scoreMap[tempi][tempj]==-10000&&tempj+1<map.length&&tempi-1>=0) {
							listRow.add(tempi-1);//대각선 방향 왼쪽 아래돌
							listCol.add(tempj+1);
						}
						if(scoreMap[tempi][tempj]==-10000&&tempj+1>=0&&tempj+1<map.length) {
							listRow.add(tempi+1);//대각선 방향 오른쪽 위 돌
							listCol.add(tempj-1);
						}
					}


					while(index<listRow.size()) {

					if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
							(scoreMap[listRow.get(index)][listCol.get(index)]==0||
							scoreMap[listRow.get(index)][listCol.get(index)]>4.3)){
						scoreMap[listRow.get(index)][listCol.get(index)]=4.3;
						writer.append("(" + tempi + "," + tempj + ") dia1 findene4 "+ 4.3 +"\n");
					}
					index++;
					}


				}
			}
		}

	}
		 */
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


