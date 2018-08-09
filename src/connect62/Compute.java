package connect62;

public class Compute {
	int[][]map;
	int[][]scoreMap;
	int[][]result=new int[2][2];
	int myColor;
	Column column;
	Diagonal1 diagonal1;
	
	Compute(int[][]map, int myColor){
		this.map = map;
		this.myColor =myColor;
		scoreMap = new int[map.length][map.length];
		makeClean(scoreMap);
		column = new Column(map, scoreMap, myColor);
		diagonal1 = new Diagonal1(map, scoreMap, myColor);
		
	}
	
	public void execute(){
		//자 이건 실행을 하는 함수야
		//이미 둔 돌에는 점수를 넣으면 안되자나. 그러니까 이미 돌이 있는 곳에는 -10000점을 줄거야.
		checkAlreadyDone();
		//그리고 가로줄을 스캔해서 점수를 줄곳에 점수를 주는거야 실행해. 
		scoreMap=column.execute();
		scoreMap=diagonal1.execute();
		//그런다음 점수맵을 프린트해보고.
		printMap(scoreMap);
		//result에는 어디다 돌을 두어야 하는지가 나와.
		result=findMax();
		//맵에다 내가 둔 돌을 기록하는 과정이야.
		map[result[0][0]][result[0][1]]= myColor;
		map[result[1][0]][result[1][1]]= myColor;
		//그리고 어디다 놓아야 하는지 예쁘게 출력해주는거야
		System.out.println("first Row : " + result[0][0] +" frist Col : " + result[0][1]);
		System.out.println("second Row : " + result[1][0] + " secnd Col : " + result[1][1]);
	}
	
	//1번부터 만들게
	
	void printMap(int[][]map) {
		for(int i=0;i<map.length;i++) {
			System.out.println(" ");
			for(int j=0;j<map.length;j++) {
				System.out.print(map[i][j]+"|");
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
	
	void makeClean(int[][]map) {
		int i,j;
		for(i=0;i<map.length;i++) {
			for(j=0;j<map[i].length;j++) {
				map[i][j]=0;
			}
		}
	}
	
	int[][] findMax(){
		
		int[][] reuslt =new int[2][2];
		int i=0;
		int j=0;
		int maxRow1=0, maxCol1=0, maxRow2=0, maxCol2=0;
		int max1=0, max2=0;
		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(max1<scoreMap[i][j]) {
					max1 = scoreMap[i][j];
					maxRow1 = i;
					maxCol1 = j;
				}
			}
		}
		
		for(i=0;i<map.length;i++) {
			for(j=0;j<map.length;j++) {
				if(max2<scoreMap[i][j]) {
					if(maxRow1==i&&maxCol1==j)	continue;
					max2 = scoreMap[i][j];
					maxRow2 = i;
					maxCol2 = j;
				}
			}
		}

		result[0][0]= maxRow1;
		result[0][1]= maxCol1;
		result[1][0] =maxRow2;
		result[1][1] =maxCol2;
		
		return result;
	}
}
