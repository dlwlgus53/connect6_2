package connect62;

import java.io.IOException;
import java.util.Scanner;
//com == me
public class Main {
	static Scanner scan = new Scanner(System.in);
	static int myColor;
	static int mapSize = 19;

	public static void main(String[] args) throws IOException {
		int[][] map = new int[mapSize][mapSize];
		makeClean(map);	

		System.out.println("black : type -1 | white : type 1");
		myColor = scan.nextInt();//==com color

		if(myColor == -1)	map[map.length/2][map[0].length/2] = -1;
		Compute compute = new Compute(map,myColor);
		
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

	public static void enemyInput(int[][]map, int myColor) {
		int x1=0,y1=0,x2=0,y2=0;
		int temp=0;
		boolean isRight= false;
		while(isRight==false) {
			System.out.println("enter the x1");
			x1 = scan.nextInt();

			System.out.println("enter the y1");
			y1 = scan.nextInt();

			System.out.println("enter the x2");
			x2 = scan.nextInt();

			System.out.println("enter the y2");
			y2 = scan.nextInt();

			System.out.println("did y do well?? yes : 1 ,no: 0");

			temp = scan.nextInt();
			if(temp==1)	isRight=true;	
		}


		map[x1][y1] = myColor*-1;
		map[x2][y2] = myColor*-1;



	}



}
