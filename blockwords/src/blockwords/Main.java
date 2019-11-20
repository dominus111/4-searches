package blockwords;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		char[][] startState = {
				{'^','^','^','^'},
				{'^','^','^','^'},
				{'^','^','^','^'},
				{'a','b','c','$'}
		};
		Node root = new Node(startState);
		
		
		Search search = new Search();
		List<Node> sol = search.IDS(root);
//		List<Node> dls = search.dls;
//		Search search1 = new Search();
//		List<Node> sol1 = search1.BFS(root);
//		List<Node> bfs = search1.bfs;
//		 bfs.removeAll(dls);
//		 System.out.println("DIferencessssssssssssssssssssssssssss");
//
//		 for(Node e : bfs) {
//	        e.printGrid();
//		 }
//		PrintStream fileOut = null;
//		try {
//			 fileOut = new PrintStream("./out.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.setOut(fileOut);
		if(sol.size() > 0) {
			Collections.reverse(sol);
			for(int i = 0; i < sol.size(); i++) {
				System.out.println(i);
				sol.get(i).printGrid();
			}
		} else {
			System.out.println("No solution found"); 
		}
//		System.out.println(sol.size());
	}
}
