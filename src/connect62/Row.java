package connect62;
import java.io.*;
import java.util.ArrayList;


public class Row {

	double[][]scoreMap;
	int[][]map;
	int myColor;
	int enemyColor;
	FileWriter writer;
	FindBetter findBetter;

	Row(int[][] map,double[][]scoreMap,int myColor, FileWriter writer) throws IOException{
		this.map = map;
		this.scoreMap = scoreMap;
		this.myColor = myColor;
		enemyColor = myColor*-1;
		this.writer = writer;

	}

	double[][] execute() throws IOException{
		findBetter = new FindBetter(map,scoreMap,myColor);
		findMyFive();
		findMyFour();
		findMine();
		findEnemy();
		findEnemyFive();
		findEnemyFour();
		return scoreMap;
	}

	void findMyFive() throws IOException{

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
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
							scoreMap[tempi][j]=1;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}

				}
			}


		}
	}

	void findMyFour() throws IOException {

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
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
							scoreMap[tempi][j]=2.2;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
						}
					}



				}
			}


		}

	}

	void findMine() throws IOException {

		//������ ���ؼ� �װ� �����ؼ� �־����.//�����߽���
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
								scoreMap[tempi][j]+=20;//�� �� ��ó�� 20�� �帳�ϴ�~
							}

						}
						break;
					case 2 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								scoreMap[tempi][j]+=20;//6�� �ȿ� 2�� ������ �� �� ��ó�� 20�� �帳�ϴ�~
								writer.append("(" + tempi + "," + j + ") row findmy2 "+ 40 +"\n");
							}
						} 
						break;

					case 3 : 
						for(tempi=i;tempi<i+6;tempi++) {
							if(scoreMap[tempi][j]!=-10000&&scoreMap[tempi][j]%10==0) {
								scoreMap[tempi][j]+=100;//6ĭ�� 3�����ְ� ���� �� ������ 100���ݴϴ�.
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

	void findEnemy() throws IOException {//6ĭ�� �������� ���� �� �׷��� �� 6ĭ�ȿ� ���� ���� ������ ������ ĭ�� ������ 10�� �ο���
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ
		int[] unit = new int[6];//6���� ��� ����

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
							listRow.add(i);//����..�����Ѱ�..?
							listCol.add(tempi-1);
						}
						if(tempi<map.length-1) {//�̰�  dia1�� �����ؾ� �ϴºκ�
							listRow.add(i);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
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
								listRow.add(tempi-1);//����..�����Ѱ�..?
								listCol.add(j);
							}
							if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//�̰�  dia1�� �����ؾ� �ϴºκ�
								listRow.add(tempi+1);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
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
		int[] unit = new int[6];
		for(int i=0;i<map.length-6+1;i++) {
			for(int j=0;j<map.length;j++) {


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

					blankRow = i+blank;
					blankCol = j;



					if(scoreMap[blankRow][blankCol]>3||scoreMap[blankRow][blankCol]==0) {

						scoreMap[blankRow][blankCol]=3;
						writer.append("(" + blankRow + "," + blankCol + ") row findEne5 "+ 3+"\n");							
					}

					if(blank==0) {
						if(i+6<map.length&&(scoreMap[i+6][j]>3||scoreMap[i+6][j]==0)){
							scoreMap[i+6][j]=3;
							writer.append("(" + (i+6) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}
					}

					if(blank==1) {

						if(i+6<map.length&&(scoreMap[i+6][j]>3||scoreMap[i+6][j]==0)){
							scoreMap[i+6][j]=3;
							writer.append("(" + (i+6) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}
					}

					/*if(blank==2||blank==3) {
						boolean case1 = false;
						boolean case2= false;
						if(i+6<map.length){
							case1=true;//���� ��
						}
						if(i-1>=0){
							case2 = true;//������ �Ʒ�
						}
						if(case1==true && case2 ==false) {
							if(scoreMap[i+6][j]>3||scoreMap[i+6][j]==0){
								scoreMap[i+6][j]=3;
								writer.append("(" + (i+6) + "," + (j) + ") row findEne5 "+ 3+"\n");	
							}

						}

						if(case1==false && case2==true) {
							if(scoreMap[i-1][j]>3||scoreMap[i-1][j]==0){
								scoreMap[i-1][j]=3;
								writer.append("(" + (i-1) + "," + (j) + ") row findEne5 "+ 3+"\n");	
							}
						}

						if(case1==true && case2==true) {
							int score1=findBetter.execute(i+6,j);
							int score2=findBetter.execute(i-1, j);

							if(score1>=score2) {
								if(scoreMap[i+6][j]>3||scoreMap[i+6][j]==0){
									scoreMap[i+6][j]=3;
									writer.append("(" + (i+6) + "," + (j) + ") row findEne5 "+ 3+"\n");	
								}
							}
							else {
								if(scoreMap[i-1][j]>3||scoreMap[i-1][j]==0){
									scoreMap[i-1][j]=3;
									writer.append("(" + (i-1) + "," + (j) + ") row findEne5 "+ 3+"\n");	
								}
							}
						}

					}*/

					if(blank==4) {
						if(i-1>=0&&j<map.length&&(scoreMap[i-1][j]>3||scoreMap[i-1][j]==0)){
							scoreMap[i-1][j]=3;
							writer.append("(" + (i-1) + "," + (j) + ") row findEne5 "+ 3+"\n");	
						}
					}

					if(blank==5) {
						if(i-1>=0&&j<map.length&&(scoreMap[i-1][j]>3||scoreMap[i-1][j]==0)){
							scoreMap[i-1][j]=3;
							writer.append("(" + (i-1) + "," + (j) + ") row findEne5 "+ 3+"\n");	
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
						if(unit[k]==blankCount) {
							blankCount++;
							blankRow.add(i+k);
							blankCol.add(j);
						}
					}



					if (isMine==false && count==4) {
						if (blankCount==0) {
							hlepEnemy4(i,j);
						}

						if(blankCount==1) {
							int score1=findBetter.execute(blankRow.get(0), blankCol.get(0));

							if(score1>0&&(scoreMap[blankRow.get(0)][blankCol.get(0)]>4.2||scoreMap[blankRow.get(0)][blankCol.get(0)]==0)) {
								scoreMap[blankRow.get(0)][blankCol.get(0)]=4.2;
								writer.append("(" + blankRow.get(0) + "," + blankCol.get(0) + ") row findene4 "+ 4.2 +"\n");
							}
							else if(score1==0){
								hlepEnemy4(i,j);
							}
						}

						if(blankCount==2) {
							int score1=0;
							int score2=0;
							score1=findBetter.execute(blankRow.get(0), blankCol.get(0));
							score2=findBetter.execute(blankRow.get(1), blankCol.get(1));

							if(score1==0&&score2==0) {
								hlepEnemy4(i,j);
							}

							else if(score1>=score2&&
									(scoreMap[blankRow.get(0)][blankCol.get(0)]>4.2||scoreMap[blankRow.get(0)][blankCol.get(0)]==0)) {
								scoreMap[blankRow.get(0)][blankCol.get(0)]=4.2;
								writer.append("(" + blankRow.get(0) + "," + blankCol.get(0) + ") row findene4 "+ 4.2 +"\n");

							}

							else if(score1<score2&&
									(scoreMap[blankRow.get(1)][blankCol.get(1)]>4.2||scoreMap[blankRow.get(1)][blankCol.get(1)]==0)) {
								scoreMap[blankRow.get(1)][blankCol.get(1)]=4.2;
								writer.append("(" + blankRow.get(1) + "," + blankCol.get(1) + ") row findene4 "+ 4.2 +"\n");
							}


						}




					}
				}
			}
		}


	}

	void hlepEnemy4(int i, int j) throws IOException {
		if(i+4<map.length&&map[i+4][j]==0&&(scoreMap[i+4][j]>4.2||scoreMap[i+4][j]==0)) {
			scoreMap[i+4][j]=4.2;
			writer.append("(" + i+4 + "," +j + ") row findene4 "+ 4.2 +"\n");
		}
		if(i-1>=0&&map[i-1][j]==0&&(scoreMap[i-1][j]>4.2||scoreMap[i-1][j]==0)) {
			scoreMap[i-1][j]=4.2;
			writer.append("(" + (i-1) + "," + j + ") row findene4 "+ 4.2 +"\n");
		}
	}





	/*
	void findEnemyFour() throws IOException {
		ArrayList<Integer> listRow = new ArrayList<Integer>(0);//row�� ���� ����Ʈ
		ArrayList<Integer> listCol = new ArrayList<Integer>(0);//col�� ���� ����Ʈ

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
							listRow.add(tempi-1);//����..�����Ѱ�..?
							listCol.add(j);
						}
						if(scoreMap[tempi][j]==-10000&&tempi<map.length-1) {//�̰�  dia1�� �����ؾ� �ϴºκ�
							listRow.add(tempi+1);//�̰ͱ��� �ؾ����� ���ƾ� ���� �𸣰ھ�//�̰Ŵ� ������
							listCol.add(j);
						}
					}



					while(index<listRow.size()) {
						if(scoreMap[listRow.get(index)][listCol.get(index)]!=-10000&&
								(scoreMap[listRow.get(index)][listCol.get(index)]==0||
								scoreMap[listRow.get(index)][listCol.get(index)]>4.2)){
							scoreMap[listRow.get(index)][listCol.get(index)]=4.2;
							System.out.println("�����?");
							writer.append("(" + listRow.get(index) + "," + listCol.get(index) + ") row findene4 "+ 4.2 +"\n");
						}
						index++;
					}


					/*int tempi=i;
					for(tempi=i;tempi<i+6;tempi++) {
						if(scoreMap[tempi][j]!=-10000&&(scoreMap[tempi][j]==0||scoreMap[tempi][j]>4.2)) {
							scoreMap[tempi][j]+=4.2;//6ĭ�ȿ� �츮�� 5�� ���� �� ������ 400�� �ݴϴ�.
							writer.append("(" + tempi + "," + j + ") row findmy4 "+ 4.2 +"\n");
						}
					}




}
}


}

}
	 */

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