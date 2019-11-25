package blockwords;

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
		List<Node> sol = search.BFS(root);	
		
		if(sol.size() > 0) {
			Collections.reverse(sol);
			for(int i = 0; i < sol.size(); i++) {
				System.out.println(i);
				sol.get(i).printGrid();
			}
		} else {
			System.out.println("No solution found"); 
		}
	}
}
