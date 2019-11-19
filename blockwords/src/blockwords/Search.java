package blockwords;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class Search {
	List<Node> toExpand = new ArrayList<>();
	List<Node> solution = new ArrayList<>();
	List<Node> traversed = new ArrayList<>();
	List<Node> bfs = new ArrayList<>();
	List<Node> dls = new ArrayList<>();
	boolean solFound = false;
	boolean expand = false;

	public List<Node> BFS(Node root) {
//		List<Node> solution = new ArrayList<>();
//		toExpand = new ArrayList<>();
//		List<Node> traversed = new ArrayList<>();
		List<Node> children = new ArrayList<>();
		int depth = 0;
		int count = 1;
		int dcount = 1;
		int dhelper = 0;
		boolean dbool = true;

		toExpand.add(root);
		boolean solFound = false;

		while (toExpand.size() > 0 && solFound != true) {
			Node current = toExpand.get(0);
			// System.out.println("toexpand size " + toExpand.size());
			// traversed.add(current);
			int count2 = 0;
			// System.out.println(count);

			bfs.add(current);
			
			if (current.isGoal()) {
				System.out.println("Goal is found");
				System.out.println("Depth " + depth);
				solFound = true;
				findPath(solution, current);
				break;
			}

			current.ExpandNode();
			toExpand.remove(0);


			for (int i = 0; i < current.children.size(); i++) {
				count++;
				count2++;

//				System.out.println("Here");
//				System.out.println(current.children.size());
				Node child = current.children.get(i);
//				System.out.println("Children");
//				child.printGrid();
				
				if (!contains(toExpand, child)) {
					toExpand.add(child);
//					System.out.println("Entered");
				}
				// System.out.println("added");

			}
			dhelper = dhelper + count2;
			count2=0;
			dcount--;
			if (dcount == 0) {
				depth++;
				dcount = dhelper;
				dhelper = 0;
				System.out.println("Depth " + depth + ", " + "Nodes: " + count);
			}

		}
		System.out.println(count);
		return solution;
	}

	public List<Node> DFS(Node root) {
		Stack<Node> s = new Stack<>();
		HashMap<Node, Integer> map = new HashMap<>();
		int depth;
		int inc = 0;
		s.push(root);

		here: while (!s.empty() && solFound != true) {
			inc++;
			Node current = s.pop();
			traversed.add(current);
			current.ExpandNode();
			int nodesCount = (s.size() + inc);

			if (current.isGoal()) {
				System.out.println("Goal is found");
				System.out.println("Depth");
				solFound = true;
				findPath(solution, current);
				break here;
			}

			for (int i = 0; i < current.children.size(); i++) {

				Node child = current.children.get(i);

				if (!containsStack(s, child) && !contains(traversed, child)) {
					System.out.println("Put child");
					s.push(child);
				}
			}

		}
		return solution;
	}

	public List<Node> IDS(Node root) {
		Stack<Node> s = new Stack<>();

		s.push(root);

		here: while (!s.empty() && solFound != true) {
			Node current = s.pop();
			traversed.add(current);
			current.ExpandNode();

			if (current.isGoal()) {
				System.out.println("Goal is found");
				System.out.println("Depth");
				solFound = true;
				findPath(solution, current);
				break here;
			}

			for (int i = 0; i < current.children.size(); i++) {
				if (solFound) {
					break here;
				}

				Node child = current.children.get(i);

				if (!containsStack(s, child) && !contains(traversed, child)) {
					s.push(child);
				}
			}

		}
		return solution;
	}

	public List<Node> DLLS(Node root, int maxDepthh) {
		Stack<Node> s = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		int depth = 0;
		int count = 0;
		int maxDepth = maxDepthh;

		s.push(root);
		map.put(0, 1);

		here: while (!s.empty() && solFound != true) {
			Node current = s.pop();
			// depth = map.get(current);
			boolean d = false;
			count = 0;

			if (depth < maxDepth) {

				traversed.add(current);

				if (current.isGoal()) {
					System.out.println("Goal is found");
					System.out.println("Depth");
					solFound = true;
					findPath(solution, current);
					break here;
				}

				current.ExpandNode();

				for (int i = 0; i < current.children.size(); i++) {

					Node child = current.children.get(i);
					// if (!containsStack(s, child) && !contains(traversed, child)) {
					s.push(child);
					count++;
//						System.out.println("depth2 " + depth);
//						child.printGrid();
					// }
				}
				if (depth != 0) {
					map.put(depth, map.get(depth) - 1);
					if (count == 0) {
						while (map.get(depth) == 0) {
							depth = depth - 1;
						}
					}
				}

				if (count != 0) {
					depth = depth + 1;
					map.put(depth, count);
				}

				System.out.println(depth + ", " + map.get(depth));
			} else if (depth > maxDepth) {
				System.out.println("Problem");
				System.exit(0);
			} else {
				traversed.add(current);
				System.out.println("depth1 " + depth);
//				System.out.println("depth1");
//				current.printGrid();

				if (current.isGoal()) {
					System.out.println("Goal is found");
					System.out.println("Depth");
					solFound = true;
					findPath(solution, current);
					break here;

				}

				map.put(depth, map.get(depth) - 1);
				System.out.println(map.get(depth));

				while (map.get(depth) == 0) {
					depth = depth - 1;
				}
			}

		}
		return solution;
	}

	public List<Node> DLS(Node root, int maxDepthh) {
		Stack<Node> s = new Stack<>();
		HashMap<Node, Integer> map = new HashMap<>();
		int depth = 0;
		int count = 0;
		int maxDepth = maxDepthh;

		s.push(root);
		map.put(root, depth);

		here: while (!s.empty() && solFound != true) {
			Node current = s.pop();
			depth = map.get(current);
			System.out.println(depth);
			count++;
			System.out.println("Count = " + count);
//			current.printGrid();
			dls.add(current);

			if (depth < maxDepth) {
				depth = depth + 1;

				traversed.add(current);
				map.remove(current);

				if (current.isGoal()) {
					System.out.println("Goal is found");
					System.out.println("Depth");
					solFound = true;
					findPath(solution, current);
					break here;
				}

				current.ExpandNode();

				for (int i = 0; i < current.children.size(); i++) {

					Node child = current.children.get(i);
//					if (!containsStack(s, child) && !contains(traversed, child)) {
					s.push(child);
//						System.out.println("depth2 " + depth);
//						child.printGrid();
					map.put(child, depth);
//					}
				}
			} else if (depth > maxDepth) {
				System.out.println("Problem");
				System.exit(0);
			} else {
				traversed.add(current);
				System.out.println("depth1 " + depth);
//				System.out.println("depth1");
//				current.printGrid();
				map.remove(current);

				if (current.isGoal()) {
					System.out.println("Goal is found");
					System.out.println("Depth");
					solFound = true;
					findPath(solution, current);
					break here;

				}
			}

		}
		return solution;
	}
	
	public List<Node> AS(Node root) {
//		List<Node> solution = new ArrayList<>();
//		toExpand = new ArrayList<>();
//		List<Node> traversed = new ArrayList<>();
		List<Node> children = new ArrayList<>();
		Comparator<Node> comparator = new MyComparator();
		PriorityQueue<Node> pq = new PriorityQueue<>(comparator);
		int depth = 0;
		int count = 1;
		int dcount = 1;
		int dhelper = 0;
		boolean dbool = true;

		pq.add(root);
		boolean solFound = false;

		while (toExpand.size() > 0 && solFound != true) {
			Node current = pq.poll();
			// System.out.println("toexpand size " + toExpand.size());
			 traversed.add(current);
			int count2 = 0;
			// System.out.println(count);

			
			if (current.isGoal()) {
				System.out.println("Goal is found");
				System.out.println("Depth " + depth);
				solFound = true;
				findPath(solution, current);
				break;
			}

			current.ExpandNode();


			for (int i = 0; i < current.children.size(); i++) {
				count++;
				count2++;

//				System.out.println("Here");
//				System.out.println(current.children.size());
				Node child = current.children.get(i);
//				System.out.println("Children");
//				child.printGrid();
				
				if (!containsQueue(pq, child) && !contains(traversed,child)) {
					pq.add(child);
//					System.out.println("Entered");
				}
				// System.out.println("added");

			}
			dhelper = dhelper + count2;
			count2=0;
			dcount--;
			if (dcount == 0) {
				depth++;
				dcount = dhelper;
				dhelper = 0;
				System.out.println("Depth " + depth + ", " + "Nodes: " + count);
			}

		}
		System.out.println(count);
		return solution;
	}
	
	private boolean containsQueue(PriorityQueue<Node> pq, Node a) {
		boolean contains = false;

		for (Node n : pq) {
			if (n.puzzleEquality(a.grid)) {
				// System.out.println("contains1");
				contains = true;
			}
		}
		return contains;
	}

	class MyComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			if (o1.getPriority() > o2.getPriority()) {
				return 1;
			} else if (o1.getPriority() < o2.getPriority()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public void findPath(List<Node> list, Node a) {
		Node node = a;
		list.add(node);
		while (node.parent != null) {
			node = node.parent;
			list.add(node);
		}
	}

	public boolean containsStack(Stack<Node> list, Node a) {
		boolean contains = false;
		Iterator<Node> itr = list.iterator();
		while (itr.hasNext()) {
			if (itr.next().puzzleEquality(a.grid)) {
				// System.out.println("contains1");
				contains = true;
			}
		}
		return contains;
	}

	public boolean contains(List<Node> list, Node a) {
		boolean contains = false;
		// System.out.println("contains");
		// System.out.println("toexpand size " + list.size());
//		if (list.contains(a)) {
//			// System.out.println("contains1");
//			contains = true;
//		}

		for (Node n : list) {
			if (n.puzzleEquality(a.grid)) {
				// System.out.println("contains1");
				contains = true;
			}
		}
		return contains;
	}

}
