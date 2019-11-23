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
		List<Node> sol = search.AS(root);
		
		
		if(sol.size() > 0) {
//			Collections.reverse(sol);
//			for(int i = 0; i < sol.size(); i++) {
//				System.out.println(i);
//				sol.get(i).printGrid();
//			}
			
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("Found"); 
		} else {
			System.out.println("No solution found"); 
		}
//		System.out.println(sol.size());
	}
}
