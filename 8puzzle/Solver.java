import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private SearchNode goal;
	private boolean solved;
	private Stack<Board> stack = new Stack<Board>();
	
	private class SearchNode {
		private int moves;
		private SearchNode prev;
		private Board board;
		
		public SearchNode(Board initial, SearchNode prev, int move) {
			this.board = initial;
			this.prev = prev;
			this.moves = move;
		}
	}
	
	private class PriorityOrder implements Comparator<SearchNode> {
		public int compare(SearchNode a, SearchNode b) {
			int priorityA = a.board.manhattan() + a.moves;
			int priorityB = b.board.manhattan() + b.moves;
			
			if (priorityA > priorityB) return 1;
			if (priorityA < priorityB) return -1;
			
			return 0;
		}
	}
	
	public Solver(Board initial) {
		
		MinPQ<SearchNode> PQ = new MinPQ<SearchNode>(new PriorityOrder());
		PQ.insert(new SearchNode(initial, null, 0));
		
		MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>(new PriorityOrder());
		twinPQ.insert(new SearchNode(initial.twin(), null, 0));
		
		SearchNode step;
		while(!PQ.min().board.isGoal() && !twinPQ.min().board.isGoal()) {
			
			step = PQ.delMin();
			for (Board b:step.board.neighbors()) {
				if (step.prev == null || !b.equals(step.prev.board)) {
					PQ.insert(new SearchNode(b, step, step.moves + 1));
				}
			}
			
			SearchNode twinStep = twinPQ.delMin();
			for (Board b:twinStep.board.neighbors()) {
				if (twinStep.prev == null || !b.equals(twinStep.prev.board)) {
					twinPQ.insert(new SearchNode(b, twinStep, twinStep.moves + 1));
				}
			}
		}
		
		step = PQ.delMin();
		solved = step.board.isGoal();
		
		goal = new SearchNode(step.board, step.prev, step.moves);
	}
	
	public boolean isSolvable() {
		return solved;
	}
	
	public int moves() {
		if (!isSolvable()) return -1;
		else return goal.moves;
	}
	
	public Iterable<Board> solution() {
		if (!isSolvable()) return null;
		
		SearchNode n = goal;
		while (n != null) {
			stack.push(n.board);
			n = n.prev;
		}
		return stack;
	}
	
    public static void main(String[] args) { // solve a slider puzzle
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}




















