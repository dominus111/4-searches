package blockwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {

	public List<Node> children = new ArrayList<Node>();
	public Node parent;
	public int col = 4;
	char[][] grid = new char[col][col];
	public int priority;
	public int depth;
	public char blocked = 'x';
	public int manhattan;

	public Node(char[][] begin) {
		copyNode(begin, grid);
	}

	public void ExpandNode() {
		int agentRow = 0;
		int agentCol = 0;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == '$') {
					agentRow = i;
					agentCol = j;
				}
			}
		}
		moveRight(grid, agentRow, agentCol);
		moveLeft(grid, agentRow, agentCol);
		moveUp(grid, agentRow, agentCol);
		moveDown(grid, agentRow, agentCol);
	}

	public void moveRight(char[][] some, int agentRow, int agentCol) {


		if (agentCol < col - 1 && some[agentRow][agentCol+1] != blocked) {

			char[][] ch = new char[col][col];
			copyNode(some, ch);

			char temp = ch[agentRow][agentCol + 1];
			ch[agentRow][agentCol + 1] = ch[agentRow][agentCol];
			ch[agentRow][agentCol] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
		}
	}

	public void moveLeft(char[][] some, int agentRow, int agentCol) {
		if (agentCol > 0 && some[agentRow][agentCol-1] != blocked) {
			char[][] ch = new char[col][col];
			copyNode(some, ch);

			char temp = ch[agentRow][agentCol - 1];
			ch[agentRow][agentCol - 1] = ch[agentRow][agentCol];
			ch[agentRow][agentCol] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
		}
	}

	public void moveUp(char[][] some, int agentRow, int agentCol) {
		if (agentRow > 0 &&  some[agentRow-1][agentCol] != blocked) {
			char[][] ch = new char[col][col];
			copyNode(some, ch);

			char temp = ch[agentRow - 1][agentCol];
			ch[agentRow - 1][agentCol] = ch[agentRow][agentCol];
			ch[agentRow][agentCol] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
		}
	}

	public void moveDown(char[][] some, int agentRow, int agentCol) {
		if (agentRow < col - 1 &&  some[agentRow+1][agentCol] != blocked) {
			char[][] ch = new char[col][col];
			copyNode(some, ch);

			char temp = ch[agentRow + 1][agentCol];
			ch[agentRow + 1][agentCol] = ch[agentRow][agentCol];
			ch[agentRow][agentCol] = temp;

			Node child = new Node(ch);
			children.add(child);
			child.parent = this;
		}
	}
	
	
	public void calcPriority() {
		int rowDiff = 0;
		int colDiff = 0;
		int val = 0;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				if(this.grid[i][j] == 'a') {
					rowDiff = Math.abs(i - 1);
					colDiff = Math.abs(j - 1);
					val = val + rowDiff + colDiff;
				}
				if(this.grid[i][j] == 'b') {
					rowDiff = Math.abs(i - 2);
					colDiff = Math.abs(j - 1);
					val = val + rowDiff + colDiff;
				}
				if(this.grid[i][j] == 'c') {
					rowDiff = Math.abs(i - 3);
					colDiff = Math.abs(j - 1);
					val = val + rowDiff + colDiff;

				}
				
			}

		}
		manhattan = val + depth;
	}
	public int getPriority() {
		return manhattan;
	}

	public void printGrid() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();

		}
		System.out.println();
	}

	public boolean puzzleEquality(char[][] x) {
		boolean equal = true;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] != x[i][j]) {
					equal = false;
				}
			}
		}
		return equal;
	}

	public void copyNode(char[][] x, char[][] y) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				y[i][j] = x[i][j];
			}
		}
	}

	public boolean isGoal() {
		boolean finish = false;
		if (this.grid[1][1] == 'a' && this.grid[2][1] == 'b' 
				&& this.grid[3][1] == 'c') {
			finish = true;
		}
		return finish;
	}

	public void setDepth(int depth) {
		this.depth = depth;
		
	}
}