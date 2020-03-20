
# 4 Searches
This project looks a tile puzzle, where we have to reach a final state of the board.\
The agent (smile face) is the empty tile and we can only slide blocks into its place.\
![](puzzle.png)
I have used 4 different search algorithms, to show their time complexity for solving the puzzle.\
The project further looks at the space complexity of the searches and see how the complexity worsens when we make some of the tiles not being able to move.\
For more detailed information please look at the PDF.

**Uninformed searches:**
* BFS - breadth first search
* DFS - depth first search
* IDS - iterative deepening search*

**Heuristics search**
* A* - A star (Manhattan distance + current depth)
## Running

The project can be run from Main.java
To run the desired search algorithm, you can change in Main.java the following line.
> List<Node> sol = search.BFS(root);

With any of the following:
```
List<Node> sol = search.BFS(root);
List<Node> sol = search.DFS(root);
List<Node> sol = search.IDS(root);
List<Node> sol = search.AS(root);
```
To make a tile not being able to move please substitute any of the '^' in Main.java with 'x'.

## Developed by
Martin Kanev - m.kanev99@gmial.com
