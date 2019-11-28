package blockwords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Search {
	List<Node> solution = new ArrayList<>();
	List<Node> traversed = new ArrayList<>();
	Stack<Node> s;
	Runtime runtime = Runtime.getRuntime();
	long kb = 1024L;
	boolean solFound = false;
	boolean expand = false;
	int priority1;
	int priority2;

	public List<Node> BFS(Node root) {
		Queue<Node> queue = new LinkedList<>();
		int depth = 0;
		int count = 1;
		int dcount = 1;
		int dhelper = 0;
		boolean solFound = false;
		queue.add(root);
		if (root.isGoal()) {
			System.out.println("Goal depth " + depth + ", Nodes expanded " + count);
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
			current = null;

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
				System.out.println("Goal depth " + depth + ", Nodes expanded " + count);
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
			current.children = null;
			current = null;
			System.out.println("Depth " + depth + ", " + "Nodes: " + count);

		}
		return solution;
	}

	public List<Node> IDS(Node root) {

		List<Node> ids = new ArrayList<>();
		int maxDepth = 0;
		if (maxDepth == 0) {
			if (root.isGoal()) {
				System.out.println("The goal is the root");
				findPath(solution, root);
			}
			maxDepth = 1;
		}

		while (ids.isEmpty()) {
			ids = DLS(root, maxDepth);
			maxDepth++;

		}
		return ids;
	}

	public List<Node> DLS(Node root, int maxDepthh) {
		s = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		int depth = 0;
		int count = 0;
		int totalCount = 1;
		int maxDepth = maxDepthh;
		boolean delete = false;
		int toDelete = 0;

		s.push(root);
		map.put(0, 1);

		here: while (!s.empty() && solFound != true) {
			Node current = s.pop();
			toDelete = 0;
			delete = false;
			count = 0;

			if (depth < maxDepth) {

				if (current.isGoal()) {
					System.out.println("Goal depth " + depth + ", Nodes expanded " + totalCount);
					solFound = true;
					findPath(solution, current);
					break here;
				}

				current.ExpandNode();
				Collections.shuffle(current.children);

				for (int i = 0; i < current.children.size(); i++) {

					Node child = current.children.get(i);
					s.push(child);
					count++;
					totalCount++;
				}

				current.children = new ArrayList<>();

				if (depth != 0) {
					map.put(depth, map.get(depth) - 1);
					if (count == 0) {
						while (map.get(depth) == 0) {
							depth = depth - 1;
							toDelete++;
							delete = true;
						}
						if (delete = true) {
							for (int i = 0; i < toDelete; i++) {
								Node n = current.parent;
								current.parent = null;
								current = n;
								n = null;
							}
						}
					}
				}

				if (count != 0) {
					depth = depth + 1;
					map.put(depth, count);
				}

				current = null;

			} else {

				if (current.isGoal()) {
					System.out.println("Goal depth " + depth + ", Nodes expanded " + totalCount);
					solFound = true;
					findPath(solution, current);
					break here;
				}
				map.put(depth, map.get(depth) - 1);

				while (map.get(depth) == 0) {
					depth = depth - 1;
					toDelete++;
					delete = true;
				}
				if (delete = true) {
					for (int i = 0; i < toDelete; i++) {
						Node n = current.parent;
						current.parent = null;
						current = n;
						n = null;
					}
				}

				current = null;

			}

		}
		root = null;
		map = null;
		s = null;
		System.gc();
		System.out.println("Depth " + maxDepth + ", Nodes: " + totalCount);
		return solution;
	}

	public List<Node> AS(Node root) {
		Comparator<Node> comparator = new MyComparator();
		PriorityQueue<Node> pq = new PriorityQueue<>(comparator);
		HashMap<Node, Integer> map = new HashMap<>();
		HashMap<Node, Integer> nodeCount = new HashMap<>();
		int depth = 0;
		int count = 1;
		boolean dbool = true;

		pq.add(root);

		map.put(root, 0);
		boolean solFound = false;

		while (pq.size() > 0 && solFound != true) {
			Node current = pq.poll();
			depth = map.get(current);

			traversed.add(current);
			dbool = true;

			if (current.isGoal()) {
				System.out.println("Goal depth " + depth +
						", Nodes expanded " + count);
				solFound = true;
				findPath(solution, current);
				break;
			}

			current.ExpandNode();

			for (int i = 0; i < current.children.size(); i++) {
				count++;
				if (dbool) {
					depth++;
					dbool = false;
				}

				Node child = current.children.get(i);

				nodeCount.put(child, depth);

				child.setDepth(depth);
				child.calcPriority();
				map.put(child, depth);
				pq.add(child);

			}

//			System.out.println("Depth " + depth + ", " + "Nodes: " + count);
			current.children = null;
			current = null;

		}
		int[] z = new int[17];
		for (Entry<Node, Integer> entry : nodeCount.entrySet()) {
			switch (entry.getValue()) {
			case 1:
				z[0] = z[0] + 1;
				break;
			case 2:
				z[1] = z[1] + 1;
				break;
			case 3:
				z[2] = z[2] + 1;
				break;
			case 4:
				z[3] = z[3] + 1;
				break;
			case 5:
				z[4] = z[4] + 1;
				break;
			case 6:
				z[5] = z[5] + 1;
				break;
			case 7:
				z[6] = z[6] + 1;
				break;
			case 8:
				z[7] = z[7] + 1;
				break;
			case 9:
				z[8] = z[8] + 1;
				break;
			case 10:
				z[9] = z[9] + 1;
				break;
			case 11:
				z[10] = z[10] + 1;
				break;
			case 12:
				z[11] = z[11] + 1;
				break;
			case 13:
				z[12] = z[12] + 1;
				break;
			case 14:
				z[13] = z[13] + 1;
				break;

			case 15:
				z[14] = z[14] + 1;
				break;

			case 16:
				z[15] = z[15] + 1;
				break;
			case 17:
				z[16] = z[16] + 1;
				break;

			}
		}
		int i = 0;
		for (int x : z) {
			i++;
			System.out.println("Depth " + i + ", Nodes: " + x);
		}
//		System.out.println("Total nodes " + count);
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
			priority1 = o1.getPriority();
			priority2 = o2.getPriority();
			if (priority1 > priority2) {
				return 1;
			} else if (priority1 < priority2) {
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

	public boolean contains(List<Node> list, Node a) {
		boolean contains = false;
		for (Node n : list) {
			if (n.puzzleEquality(a.grid)) {
				contains = true;
			}
		}
		return contains;
	}

}