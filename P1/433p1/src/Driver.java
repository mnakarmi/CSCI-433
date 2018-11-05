import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver{
	public static int[][] model;
	static Scanner scan = new Scanner(System.in);
	static Scanner scan2 = new Scanner(System.in);
	public static int Area = 0; 
	
	public static void main(String [] args) throws FileNotFoundException{	
		ArrayList<Integer> vls = new ArrayList<Integer>();
		ArrayList<Integer> il = new ArrayList<Integer>();
		try{
			scan = new Scanner(new File("sample1.txt"));
			String ln = scan.nextLine();
			String[] ModelGraph = ln.split(" ");
			Area = ModelGraph.length;
			System.out.println();
			
			System.out.println();
			model = new int[Area][Area];

			//Loop for rows and columns.
			for(int i = 0; i <= Area-1; i++){
			    for(int j = 0; j <= Area-1; j++){
			        	model[i][j] = Integer.parseInt(ModelGraph[j]);
			    }
			    
			    if(scan.hasNextLine()){
					ln = scan.nextLine();
					ModelGraph = ln.split(" "); 
				}
			}
		scan.close();

		}
		catch(FileNotFoundException e){
			System.out.println("The file was not found.");
		}
		
		printModel("Adj matrix", model);
		
		dfs(vls, il);
		bfs(vls, il);
		
		
		
		}
	
	public static void printModel(String head, int[][] model){
		System.out.println(head);
		//Loop for rows and columns.
		for(int i = 0; i <= Area-1; ++i){
			System.out.print("    ");
		    for(int j = 0; j <= Area-1; ++j){
		    	System.out.print(model[i][j] + " ");
		    }
		    System.out.println();
		}
		System.out.println();
	}
	
	public static void dfs(ArrayList<Integer> vertexList, ArrayList<Integer> iList){
		boolean[] VitA = new boolean[Area];
		boolean[] VitA2 = new boolean[Area];
		ArrayList<Integer> Vitls = new ArrayList<Integer>();
		ArrayList<Integer> Deadmod = new ArrayList<Integer>();

		int[][] EndGraph = new int[Area][Area];
		int[][] crossGraph = new int[Area][Area];
		int Sumpro = 0;
		//Sets all values in visitArray to false. They will be set to true when that number is visited.
		for(int i = 0; i <= Area-1; i++){
			VitA[i] = false;
		}
		
		//Initializing treeEdgeGraph and back edge graph to all zeros.
		//Loop for rows and columns.
		for(int i = 0; i <= Area-1; i++){
		    for(int j = 0; j <= Area-1; j++){
		        	EndGraph[i][j] = 0;
		        	crossGraph[i][j]=0;
		    }
		}
		//This is a for loop to go through all the nodes starting with the first node
		for(int i = 0; i <= Area-1; i++){
			//This if statement checks to see if this node has been visited yet
			if(!VitA[i]){
				dfs(i, VitA, Vitls, Deadmod, EndGraph, vertexList, iList, VitA2);
				Deadmod.add(i);
				Sumpro++;
				VitA2[i] = true;
			}
		}
		System.out.println();
		System.out.println("In DFS the Number of Connected Components is: " + Sumpro);
		System.out.print("In DFS the Order of First Encountered      : ");
		for(int i : Vitls){
			i=i+1;
		}
		for(int k=0;k<8;k++){
			System.out.print((Vitls.indexOf(k)+1)+" ");
		}
		System.out.println();
		System.out.print("In DFS the Order of First Dead-Ends        : ");
		for(int i : Deadmod){
			i=i+1;
		}
		for(int k=0;k<8;k++){
			System.out.print((Deadmod.indexOf(k)+1)+" ");
		}
		System.out.println();
		System.out.println();
		printModel("DFS Tree Edge Graph", EndGraph);
		
		for(int i = 0; i <= iList.size()-1; i++){
			crossGraph[iList.get(i)-1][vertexList.get(i)-1] = 1;
			if(EndGraph[iList.get(i)-1][vertexList.get(i)-1]==1){
				crossGraph[iList.get(i)-1][vertexList.get(i)-1] = 0;
			}
		}
		printModel("DFS Back Edge Graph", crossGraph);
	}
	
	public static void dfs(int visit, boolean[] visitArray, ArrayList<Integer> dfsls, ArrayList<Integer> DFSdeadEndList, int[][] DFStreeEdgeGraph, ArrayList<Integer> vertexList, ArrayList<Integer> iList, boolean[] visitArray2){
		int ver = 0;
		dfsls.add(visit);
		visitArray[visit] = true;

		for(int i = 0; i <= Area-1; i++){
			if(model[visit][i] == 1){
				if(!visitArray[i]){
					ver = i;
					DFStreeEdgeGraph[visit][i] = 1;
					dfs(i, visitArray, dfsls, DFSdeadEndList, DFStreeEdgeGraph, vertexList, iList, visitArray2);
					DFSdeadEndList.add(i);
				}
				else if(i >= visit && i != ver){
					vertexList.add(visit+1);
					iList.add(i+1);
				}
			}
		}				
	}
	
	public static void bfs(ArrayList<Integer> vertexList, ArrayList<Integer> iList){
		boolean[] visitArray = new boolean[Area];
		boolean[] visitArray2 = new boolean[Area];
		ArrayList<Integer> BFSvertexList = new ArrayList<Integer>();
		int[][] BFStreeEdgeGraph = new int[Area][Area];
		int[][] crossGraph = new int[Area][Area];
		int BFSnumOfComponents = 0;
		//Initializing treeEdgeGraph to all zeros.
		//Loop for rows and columns .
		for(int i = 0; i <=Area-1; i++){
		    for(int j = 0; j <= Area-1; j++){
		        	BFStreeEdgeGraph[i][j] = 0;
		        	crossGraph[i][j]=0;
		    }
		}

		for(int i = 0; i < Area; i++){
			if(!visitArray[i]){
				BFSvertexList.add(i);
				visitArray[i] = true;
				visitArray2[i] = true;
				bfs(crossGraph,i, visitArray, BFSvertexList, BFStreeEdgeGraph, vertexList, iList, visitArray2);
				BFSnumOfComponents++;
			}		
		}
		System.out.println();
		System.out.println("In BFS the Number of Connected Components is: " + BFSnumOfComponents);
		System.out.print("In BFS the Order of First encountered      : ");
		for(int i : BFSvertexList){
			i=i+1;
		}
		for(int k=0;k<8;k++){
			System.out.print((BFSvertexList.indexOf(k)+1)+" ");
		}
		System.out.println();
		System.out.println();
		printModel("BFS Tree Edge Graph", BFStreeEdgeGraph);
		printModel("BFS Cross Edge Graph", crossGraph);
	}
	
	public static void bfs(int[][] CG,int v, boolean[] visitArray, ArrayList<Integer> BFSvertexList, int[][] BFStreeEdgeGraph, ArrayList<Integer> vertexList, ArrayList<Integer> iList, boolean[] visitArray2){
		for(int i = 0; i < model.length; i++){
			if(model[v][i] == 1){
				if(!visitArray[i]){
					BFStreeEdgeGraph[v][i] = 1;
					BFSvertexList.add(i);
					visitArray[i] = true;
				}
				else if(visitArray[i] && BFStreeEdgeGraph[v][i] != 1 && BFStreeEdgeGraph[i][v] != 1){
					vertexList.add(v+1);
					iList.add(i+1);
					CG[v][i]=1;
					if(CG[i][v]==1)
						CG[v][i]=0;
				}
			}
		
		}
		
		for(int i = BFSvertexList.indexOf(v) + 1; i < BFSvertexList.size(); i++){
			int value = BFSvertexList.get(i);
			bfs(CG,value, visitArray, BFSvertexList, BFStreeEdgeGraph, vertexList, iList, visitArray2);
		}
	}
	
}