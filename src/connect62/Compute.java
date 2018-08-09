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
		//�� �̰� ������ �ϴ� �Լ���
		//�̹� �� ������ ������ ������ �ȵ��ڳ�. �׷��ϱ� �̹� ���� �ִ� ������ -10000���� �ٰž�.
		checkAlreadyDone();
		//�׸��� �������� ��ĵ�ؼ� ������ �ٰ��� ������ �ִ°ž� ������. 
		scoreMap=column.execute();
		scoreMap=diagonal1.execute();
		//�׷����� �������� ����Ʈ�غ���.
		printMap(scoreMap);
		//result���� ���� ���� �ξ�� �ϴ����� ����.
		result=findMax();
		//�ʿ��� ���� �� ���� ����ϴ� �����̾�.
		map[result[0][0]][result[0][1]]= myColor;
		map[result[1][0]][result[1][1]]= myColor;
		//�׸��� ���� ���ƾ� �ϴ��� ���ڰ� ������ִ°ž�
		System.out.println("first Row : " + result[0][0] +" frist Col : " + result[0][1]);
		System.out.println("second Row : " + result[1][0] + " secnd Col : " + result[1][1]);
	}
	
	//1������ �����
	
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
