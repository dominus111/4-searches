package blockwords;

import java.util.ArrayList;
import java.util.List;

public class Search {
	List<Node> toExpand;
	
	public List<Node> BFS(Node root) {
		List<Node> solution = new ArrayList<>();
		toExpand = new ArrayList<>();
		List<Node> traversed = new ArrayList<>();
		
		toExpand.add(root);
		boolean solFound = false;
		
		while(toExpand.size() > 0 && solFound != true) {
			Node current = toExpand.get(0);
			//System.out.println("toexpand size " + toExpand.size());
			traversed.add(current);
			
			
			current.ExpandNode();
			
			for(int i = 0; i < current.children.size(); i++) {
//				System.out.println("Here");
//				System.out.println(current.children.size());
				Node child = current.children.get(i);
				if(child.isGoal()) {
					System.out.println("Goal is found");
					System.out.println("Depth");
					solFound = true;
					findPath(solution, child);
					break;
				}
				
				if(!contains(toExpand, child) && !contains(traversed, child)) {
					toExpand.add(child);
					//System.out.println("added");
				}
			}
			toExpand.remove(0);
			
		}
		return solution;
	}
	
	public void findPath(List<Node> list, Node a) {
		Node node = a;
		list.add(node);
		while(node.parent != null) {
			node = node.parent;
			list.add(node);
		}
	}
	
	public boolean contains(List<Node> list , Node a) {
		boolean contains = false; 
		//System.out.println("contains");
		//System.out.println("toexpand size " + list.size());
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).puzzleEquality(a.grid)) {
				//System.out.println("contains1");
				contains = true;
			}
		}
		return contains;
	}
	
}
