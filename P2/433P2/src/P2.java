import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class P2{

	static int ROW = 0, COL = 0;
	private static BufferedReader matrix;
	private static Scanner keyboard;
	
	public static void main(String[] args) throws IOException{	
		matrix = new BufferedReader(new FileReader("Matrix.txt"));
		String cols = matrix.readLine();
		ROW++;
		StringTokenizer collectData = new StringTokenizer(cols," ");
		COL = collectData.countTokens();
		
		
		
		while(matrix.readLine() != null)
			ROW++;
		keyboard = new Scanner(System.in);
		System.out.print("Enter the value k: ");
		
		int val = keyboard.nextInt();
		
		matrix = new BufferedReader(new FileReader("Matrix.txt"));
		int data[][] = new int[ROW][COL];
		for(int m=0; m<ROW; m++){
		
			String colLine = matrix.readLine();
			collectData = new StringTokenizer(colLine," ");
			for(int n=0; n<COL; n++){
				
				int temp = Integer.parseInt(collectData.nextToken());
				if(temp > val)
				
					data[m][n] = 1;
				
				else
				data[m][n] = 0;
			
			}
			
		}
			
		printMaxSubSquare(data);	

		
		}
		
		
		static int minimum(int a, int b, int c){
			int mini = a;
			if (mini > b)
				mini = b;
			if (mini > c)
				mini = c;
			return mini;	
		}
		
		
		
		static void printMaxSubSquare(int M[][]){
			int x,y;
			int[][] S = new int[ROW][COL];
			int maxs;
			
			
			for(x = 0; x < ROW; x++)
			S[x][0] = M[x][0];
			
			for(y = 0; y < COL; y++)
			S[0][y] = M[0][y];
			
		
			
			for(x = 1; x < ROW; x++){
				for(y = 1; y < COL; y++){
					
					if(M[x][y] == 1)
						S[x][y] = minimum(S[x][y-1], S[x-1][y], S[x-1][y-1]) + 1;
					else
						S[x][y] = 0;
					
				}
			
			}
		
			
			
			maxs = S[0][0];  
			for(x = 0; x < ROW; x++){
				for(y = 0; y < COL; y++){
					if(maxs < S[x][y])
						maxs = S[x][y];
				}
			}
			
			ArrayList<String> smatrix =  new ArrayList<String>();
			for(x = 0; x < ROW; x++){
				for(y = 0; y<COL; y++){
					if(S[x][y] == maxs) 
						smatrix.add((x+1)+" "+(y+1));


				}
			}
			System.out.println(smatrix.size());
			
			for (x=0; x<smatrix.size(); x++) {
				System.out.println(smatrix.toArray()[x]);
			}
		}

}