package connect62;

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

	Compute(int[][]map, int myColor){
		this.map = map;
		this.myColor =myColor;
		scoreMap = new double[map.length][map.length];
		makeClean(scoreMap);
		column = new Column(map, scoreMap, myColor);
		diagonal1 = new Diagonal1(map, scoreMap, myColor);
		row = new Row(map,scoreMap, myColor);
		diagonal2 = new Diagonal2(map, scoreMap, myColor);
		make4by4 = new Make4by4(map,scoreMap, myColor);

	}

	public void execute(){
		//자 이건 실행을 하는 함수야
		//이미 둔 돌에는 점수를 넣으면 안되자나. 그러니까 이미 돌이 있는 곳에는 -10000점을 줄거야.
		checkAlreadyDone();
		//그리고 가로줄을 스캔해서 점수를 줄곳에 점수를 주는거야 실행해. 
		scoreMap=column.execute();
		scoreMap=diagonal1.execute();
		scoreMap = row.execute();
		scoreMap = diagonal2.execute();
		scoreMap = make4by4.execute();
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
		System.out.println("first Row : " + result[0] +" frist Col : " + result[1]);
		
	}

	//1번부터 만들게

	void printMap(double[][] scoreMap2) {
		for(int i=0;i<scoreMap2.length;i++) {
			System.out.println(" ");
			for(int j=0;j<scoreMap2.length;j++) {

				System.out.printf("%5d|" ,scoreMap2[i][j]);

			
			}
		}
		System.out.println(" ");
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
	
		double check=10;
		boolean isMust=false;

		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(scoreMap[i][j]>0&&scoreMap[i][j]<9) {
					isMust=true;
					if(scoreMap[i][j]<check) {
						check = scoreMap[i][j]; result[0]=i; result[1]=j;
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
