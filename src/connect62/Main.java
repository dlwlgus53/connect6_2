package connect62;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	static Scanner scan = new Scanner(System.in);
	static int myColor;
	public static void main(String[] args) throws IOException {
		System.out.println("맵 크기를 알려주세요");
		int mapSize = scan.nextInt();
		int[][] map = new int[mapSize][mapSize];
		makeClean(map);	
		System.out.println("컴퓨터가  검은돌이면 -1  흰돌이면 1");
		myColor = scan.nextInt();
		if(myColor == -1)	map[map.length/2][map[0].length/2] = -1;
		Compute compute = new Compute(map,myColor);
		map[6][1]=1;
		map[5][2]=1;
		map[4][3]=1;
		map[2][5]=1;
		while(true) {
		enemyInput(map,myColor);
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
