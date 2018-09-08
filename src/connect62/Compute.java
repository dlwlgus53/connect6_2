package connect62;

import java.io.FileWriter;
import java.io.IOException;

public class Compute {

	int[][]map;
	double[][]scoreMap;
	int[]result=new int[2];
	int myColor;
	Column column;
	Diagonal1 diagonal1;
	Row row;
	Diagonal2 diagonal2;
	Make4by4 make4by4;
	AboutEnemy3 aboutEnemy3;
	FileWriter writer;
	FileWriter mapWriter;
	FindTwoEnemy3 findTwoEnemy3;

	Compute(int[][]map, int myColor) throws IOException{
		this.map = map;
		this.myColor =myColor;
		scoreMap = new double[map.length][map.length];



	}

	public void execute() throws IOException{
		makeClean(scoreMap);
		FileWriter writer = new FileWriter("log.txt");
		
		column = new Column(map, scoreMap, myColor, writer);
		diagonal1 = new Diagonal1(map, scoreMap, myColor, writer);
		row = new Row(map,scoreMap, myColor,writer);
		diagonal2 = new Diagonal2(map, scoreMap, myColor, writer);
		make4by4 = new Make4by4(map,scoreMap, myColor,writer);
		aboutEnemy3 = new AboutEnemy3(map,scoreMap, myColor,writer);
		findTwoEnemy3 = new FindTwoEnemy3(map,scoreMap,myColor,writer);		
		//자 이건 실행을 하는 함수야
		//이미 둔 돌에는 점수를 넣으면 안되자나. 그러니까 이미 돌이 있는 곳에는 -10000점을 줄거야.
		checkAlreadyDone();
		//그리고 가로줄을 스캔해서 점수를 줄곳에 점수를 주는거야 실행해. 
		scoreMap=column.execute();
		scoreMap=diagonal1.execute();
		scoreMap = row.execute();
		scoreMap = diagonal2.execute();
		scoreMap = make4by4.execute();
		scoreMap = aboutEnemy3.execute();
		scoreMap = findTwoEnemy3.execute();
		//그런다음 점수맵을 프린트해보고.
		printMap(scoreMap);
		//result에는 어디다 돌을 두어야 하는지가 나와.
		result=findResult();
		//맵에다 내가 둔 돌을 기록하는 과정이야.
		map[result[0]][result[1]]= myColor;
		System.out.println("first Row : " + result[0] +" frist Col : " + result[1]);

		checkAlreadyDone();
		result=findResult();
		map[result[0]][result[1]]= myColor;
		//그리고 어디다 놓아야 하는지 예쁘게 출력해주는거야
		System.out.println("second Row : " + result[0] +" second Col : " + result[1]);
		writer.close();

	}

	//1번부터 만들게

	void printMap(double[][] scoreMap2) throws IOException {
		mapWriter = new FileWriter("map.txt");
		for(int i=0;i<scoreMap2.length;i++) {
			for(int j=0;j<scoreMap2.length;j++) {
				mapWriter.append(i +" : " + j +" : " + scoreMap2[i][j]+"\n");
			}
		}
	
		mapWriter.close();
	}

	void checkAlreadyDone() {
		for(int i=0; i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]!=0)	scoreMap[i][j]=-10000;
			}
		}
	}

	void makeClean(double[][] scoreMap2) {
		int i,j;
		for(i=0;i<scoreMap2.length;i++) {
			for(j=0;j<scoreMap2[i].length;j++) {
				scoreMap2[i][j]=0;
			}
		}
	}

	int[] findResult(){
		int result[] = new int[2];

		int i;
		int j;

		double check=9;
		boolean isMust=false;

		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(scoreMap[i][j]>0&&(int)scoreMap[i][j]%10<9&&(int)scoreMap[i][j]%10>0) {

					isMust=true;
					if(scoreMap[i][j]%10<check%10) {
						check = scoreMap[i][j]; result[0]=i; result[1]=j;
					}
					if(scoreMap[i][j]%10==check%10) {
						if(scoreMap[i][j]>check%10) {
							check = scoreMap[i][j]; result[0]=i; result[1]=j;
						}
					}
				}
			}
		}

		if(isMust)	return result;

		check=0;

		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(scoreMap[i][j]>check) {
					check = scoreMap[i][j]; result[0]=i; result[1]=j;

				}
			}
		}
		return result;
	}





}
