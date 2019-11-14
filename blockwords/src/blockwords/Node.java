package blockwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

	public List<Node> children = new ArrayList<Node>();
	char[] goal = {
			'^','^','$','^',
			'^','a','^','^',
			'^','b','^','^',
			'^','c','^','^'
	};
	public Node parent;
	public int gridSize = 16;
	char[] grid = new char[gridSize];
	public int col = 4;
	public int agentPos;

	public Node(char[] begin) {
		copyNode(begin, grid);
//		printGrid();
//		try {
//			Thread.sleep(500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void fillGrid() {
		for (int i = 0; i < grid.length; i++) {
			grid[i] = '#';

		}
	}
	
	public void ExpandNode() {
		for(int i = 0; i < grid.length; i++) {
			if (grid[i] == '$') {
				agentPos = i;
			}
		}
			System.out.println("agentPos" + agentPos);
			moveRight(grid, agentPos);
			moveLeft(grid, agentPos);
			moveUp(grid, agentPos);
			moveDown(grid, agentPos);
	}

	public void moveRight(char[] some, int pos) {

		if (pos % col < col - 1) {

			char[] ch = new char[gridSize];
			copyNode(some, ch);

			char temp = ch[pos + 1];
			ch[pos + 1] = ch[pos];
			ch[pos] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
			System.out.println("Right");
		}

	}

	public void moveLeft(char[] some, int pos) {
		if (pos % col > 0) {
			char[] ch = new char[gridSize];
			copyNode(some, ch);

			char temp = ch[pos - 1];
			ch[pos - 1] = ch[pos];
			ch[pos] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
			System.out.println("left");
		}
	}

	public void moveUp(char[] some, int pos) {
		if (pos >= col) {
			char[] ch = new char[gridSize];
			copyNode(some, ch);

			char temp = ch[pos - col];
			ch[pos - col] = ch[pos];
			ch[pos] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
			System.out.println("up");
		}
	}

	public void moveDown(char[] some, int pos) {
		if (pos + col < grid.length) {
			char[] ch = new char[gridSize];
			copyNode(some, ch);

			char temp = ch[pos + col];
			ch[pos + col] = ch[pos];
			ch[pos] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
			System.out.println("Down");
		}
	}

	public void printGrid() {
		int b = 0;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(grid[b] + " ");
				b++;
			}
			System.out.println();

		}
		System.out.println();
	}

	public boolean puzzleEquality(char[] x) {
		boolean equal = true;
		for (int i = 0; i < x.length; i++) {
			if (grid[i] != x[i]) {
				equal = false;
			}
		}
		return equal;
	}

	public void copyNode(char[] x, char[] y) {
		for (int i = 0; i < x.length; i++) {
			y[i] = x[i];
		}
	}

	public boolean isGoal() {
		boolean finish = false;
		if (Arrays.equals(goal, this.grid)) {
			finish = true;
		}
		return finish;
	}
}
