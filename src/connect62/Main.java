package connect62;
import java.io.IOException;
import java.util.Scanner;
public class Main {
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		//처음부터 할게
		//맵을 만들자
		System.out.println("맵 크기를 알려주세요");
		int mapSize = scan.nextInt();
		//맵을 만들자 나는 맵을 배열로 만들었어.
		int[][] map = new int[mapSize][mapSize];
		//그리고 맵 안에 전부 0을 넣어줄거야. 함수를 이용해서!
		makeClean(map);
		
		//그다음 내가 검은돌인지 흰돌인지를 입력받아야해.
		System.out.println("컴퓨터가  검은돌이면 -1  흰돌이면 1");
		int myColor = scan.nextInt();
		
		//검은돌이면 맵 중앙에 표시해야하자나?? 그래서 사이즈를 절반으로 나눈값에다가 -1 표시를 할게
		if(myColor == -1)	map[map.length/2][map[0].length/2] = -1;
		//게임이 스타또!
		//반복문스따또
		Compute compute = new Compute(map,myColor);
		while(true) {
		//상대방부터 시작해 언제나:) (x1,y1)이랑 (x2,y2)를 입력 받아야하지 함수로 할게
		enemyInput(map,myColor);
		//여기서 나는 계산기를 사용하는거지.
		//print(Compute.result);
		printMap(map);
		compute.execute();
		
		}
		
		
		
		
		

	}
	
	static void makeClean(int[][]map) {
		int i,j;
		for(i=0;i<map.length;i++) {
			for(j=0;j<map[i].length;j++) {
				map[i][j]=0;
			}
		}
	}
	
	static void printMap(int[][]map) {
		for(int i=0;i<map.length;i++) {
			System.out.println(" ");
			for(int j=0;j<map.length;j++) {
				System.out.print(map[i][j]);
			}
		}
		System.out.println(" ");
	}
	
	static void enemyInput(int[][]map, int myColor) {
		int x1=0,y1=0,x2=0,y2=0;
		int temp=0;
		boolean isRight= false;
		//물어보는 착한 친구 컴퓨터친구:) 
		while(isRight==false) {
		System.out.println("당신의 x1을 입력하세요");
		x1 = scan.nextInt();
		
		System.out.println("당신의 y1을 입력하세요");
		y1 = scan.nextInt();
		
		System.out.println("당신의 x2를 입력하세요");
		x2 = scan.nextInt();
		
		System.out.println("당신의 y2를 입력하세요");
		y2 = scan.nextInt();
		
		System.out.println("제대로 입력하셨나요? 맞으면1, 틀리면0을 입력해 주세요");
		
		temp = scan.nextInt();
		if(temp==1)	isRight=true;	
		}
		
		//이제 입력받은거를 맵에 기록해주자
		map[x1][y1] = myColor*-1;//내 컬러에서 -1곱해주면 상대방 색이 되, 너무 좋은생각 아니니?! 지현이 짱짱
		map[x2][y2] = myColor*-1;
		//두개 다 기록을 했다 짱짱맨~~ 근데 실수로 잘못입력하면 망하자나? 그래서 나중에 잘 입력하셨어요? 물어보는거 넣을라구 일단 그건 나중에 생각하자.
		
		
	}
	
	

}
