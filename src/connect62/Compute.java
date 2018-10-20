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
		//�� �̰� ������ �ϴ� �Լ���
		//�̹� �� ������ ������ ������ �ȵ��ڳ�. �׷��ϱ� �̹� ���� �ִ� ������ -10000���� �ٰž�.
		checkAlreadyDone();
		//�׸��� �������� ��ĵ�ؼ� ������ �ٰ��� ������ �ִ°ž� ������. 
		scoreMap=column.execute();
		scoreMap=diagonal1.execute();
		scoreMap = row.execute();
		scoreMap = diagonal2.execute();
		scoreMap = make4by4.execute();
		scoreMap = aboutEnemy3.execute();
		scoreMap = findTwoEnemy3.execute();
		
		printMap(scoreMap);
		result=findResult();
		map[result[0]][result[1]]= myColor;
		System.out.println("first Row : " + result[0] +" frist Col : " + result[1]);

		checkAlreadyDone();
		result=findResult();
		map[result[0]][result[1]]= myColor;
		System.out.println("second Row : " + result[0] +" second Col : " + result[1]);
		writer.close();

	}
	
	


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
