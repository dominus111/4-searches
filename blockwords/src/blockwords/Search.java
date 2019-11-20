package blockwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Search {
	List<Node> toExpand = new ArrayList<>();
	List<Node> solution = new ArrayList<>();
	List<Node> traversed = new ArrayList<>();
	boolean solFound = false;
	boolean expand = false;
	int o1Pri;
	int o2Pri;

	public List<Node> BFS(Node root) {
		Queue<Node> queue = new LinkedList<>();
		int depth = 0;
		int count = 1;
		int dcount = 1;
		int dhelper = 0;
		boolean solFound = false;
		queue.add(root);
		if (root.isGoal()) {
			System.out.println("Goal is found at depth " + (depth));
			System.out.println("Nodes expanded " + count);
			findPath(solution, root);
			solFound = true;
		}

		here: while (queue.size() > 0 && solFound != true) {
			Node current = queue.poll();
			int count2 = 0;

			current.ExpandNode();

			for (int i = 0; i < current.children.size(); i++) {
				count++;

				Node child = current.children.get(i);

				if (child.isGoal()) {
					System.out.println("Goal is found at depth " + (depth + 1));
					System.out.println("Nodes expanded " + count);
					findPath(solution, child);
					solFound = true;
					break here;
				}

				queue.add(child);
				count2++;

			}
			current.children = null;

			dhelper = dhelper + count2;
			count2 = 0;
			dcount--;
			if (dcount == 0) {
				depth++;
				dcount = dhelper;
				dhelper = 0;
				System.out.println("Depth " + depth + ", " + "Nodes: " + count);
			}

		}
		return solution;
	}

	public List<Node> DFS(Node root) {
		Stack<Node> s = new Stack<>();
		int depth = 0;
		int count = 1;
		boolean dbool = false;
		s.push(root);

		here: while (!s.empty() && solFound != true) {

			Node current = s.pop();
			dbool = true;

			if (current.isGoal()) {
				System.out.println("Goal is found at depth " + (depth + 1));
				System.out.println("Nodes expanded " + count);
				findPath(solution, current);
				solFound = true;
				break here;
			}

			current.ExpandNode();

			Collections.shuffle(current.children);

			for (int i = 0; i < current.children.size(); i++) {
				count++;

				Node child = current.children.get(i);

				if (dbool) {
					depth++;
					dbool = false;
				}
				s.push(child);

			}
		}
		return solution;
	}

	public List<Node> IDS(Node root) {

		List<Node> ids = new ArrayList<>();
		int maxDepth = 1;
		while (ids.isEmpty()) {
			ids = DLLS(root, maxDepth);
			maxDepth++;

		}
		return ids;
	}

	public List<Node> DLLS(Node root, int maxDepthh) {
		Stack<Node> s = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		int depth = 0;
		int count = 0;
		int totalCount = 1;
		int maxDepth = maxDepthh;

		s.push(root);
		map.put(0, 1);

		here: while (!s.empty() && solFound != true) {
			Node current = s.pop();
			// depth = map.get(current);
			boolean d = false;
			count = 0;

			if (depth < maxDepth) {

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
					totalCount++;
//						System.out.println("depth2 " + depth);
						child.printGrid();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

//				System.out.println(depth + ", " + map.get(depth));
			} else if (depth > maxDepth) {
				System.out.println("Problem");
				System.exit(0);
			} else {
				
//				System.out.println("depth1 " + depth);
//				System.out.println("depth1");
//				current.printGrid();

				if (current.isGoal()) {
					System.out.println("Goal is found");
					System.out.println("Depth");
					solFound = true;
					findPath(solution, current);
					break here;

				}
				
				current = null;
			

				map.put(depth, map.get(depth) - 1);
//				System.out.println(map.get(depth));

				while (map.get(depth) == 0) {
					depth = depth - 1;
				}
			}

		}
		System.out.println("Depth, " + maxDepth + "Nodes: " + totalCount);
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
		System.out.println("1 ");
		PriorityQueue<Node> pq = new PriorityQueue<>(comparator);
		System.out.println("2");
		HashMap<Node, Integer> map = new HashMap<>();
		int depth = 0;
		int count = 1;
		int dcount = 1;
		int dhelper = 0;
		boolean dbool = true;

		pq.add(root);

		map.put(root, 0);
		boolean solFound = false;

		while (pq.size() > 0 && solFound != true) {
			Node current = pq.poll();
			depth = map.get(current);

			// System.out.println("toexpand size " + toExpand.size());
			traversed.add(current);
			int count2 = 0;
			dbool = true;
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

//				System.out.println("Here");
//				System.out.println(current.children.size());
				Node child = current.children.get(i);
//				System.out.println("Children");
//				child.printGrid();

				if (!containsQueue(pq, child) && !contains(traversed, child)) {

					count2++;
					if (dbool) {
						depth++;
						dbool = false;
					}

					child.setDepth(depth);
					child.calcPriority();
					map.put(child, depth);
					pq.add(child);

					System.out.println("Priority " + child.getPriority());
//					child.printGrid();
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("Entered");
				}
				// System.out.println("added");

			}
//			dhelper = dhelper + count2;
//			count2 = 0;
//			dcount--;
//			if (dcount == 0) {
//				depth++;
//				dcount = dhelper;
//				dhelper = 0;

//			}
			System.out.println("Depth " + depth + ", " + "Nodes: " + count);

		}
		System.out.println(count);
		return solution;
	}

	private boolean containsQueue(Queue<Node> queue, Node a) {
		boolean contains = false;

		for (Node n : queue) {
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
			o1Pri = o1.getPriority();
			o2Pri = o2.getPriority();
			if (o1Pri > o2Pri) {
				return 1;
			} else if (o1Pri < o2Pri) {
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
