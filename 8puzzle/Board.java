import edu.princeton.cs.algs4.Queue;

public class Board {
	private int[] board;
	private int N;
	
	public Board(int[][] blocks) {
		N = blocks[0].length;
		board = new int[N*N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i*N + j] = blocks[i][j];
			}
		}
	}
	
	private Board(int[] blocks) {
		this.N = (int)Math.sqrt(blocks.length);
		this.board = new int[N*N];
		
		for (int i = 0; i < N*N; i++) {
			board[i] = blocks[i];
		}
	}
	
	public int dimension() {
		return N;
	}
	
	public int hamming() {
		int count = 0;
		for (int i = 0; i < N*N; i++) {
			if (board[i] != i+1 && board[i] != 0) {
				count++;
			}
		}
		return count;
	}
	
	public int manhattan() {
		int row, col, sum = 0;
		for (int i = 0; i < N*N; i++) {
			if (board[i] != i+1) {
				row = Math.abs((board[i]-1)/N - i/N);
				col = Math.abs((board[i]-1)%N - i%N);
				sum += row + col;
			}
		}
		
		return sum;
	}
	
	public boolean isGoal() {
		for (int i = 0; i < N*N; i++) {
			if (board[i] != i+1 && board[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public Board twin() {
		int[] twin = copy(board);
		
		if (twin[0] != 0 || twin[1] != 0) {
			exch(twin, 0, 1);
		}
		else {
			exch(twin, N, N+1);
		}
		
		return new Board(twin);
	}
	
	public boolean equals(Object y) {
		if (y == this) return true;
		if (y == null || y.getClass() != this.getClass()) return false;
		
		Board that = (Board) y;
		for (int i = 0; i < N*N; i++) {
			if (this.board[i] != that.board[i]) {
				return false;
			}
		}
		return true;
	}
	
	public Iterable<Board> neighbors() {
		int index = 0;
		Queue<Board> neighbors = new Queue<Board>();
		while (board[index] != 0) {
			index++;
			if (index > board.length)
				break;
		}
		
		if (index/N > 0) {
			int[] blocks = copy(board);
			exch(blocks, index-N, index);
			neighbors.enqueue(new Board(blocks));
		}
		
		if (index/N < N-1) {
			int[] blocks = copy(board);
			exch(blocks, index, index+N);
			neighbors.enqueue(new Board(blocks));
		}
		
		if (index%N > 0) {
			int[] blocks = copy(board);
			exch(blocks, index-1, index);
			neighbors.enqueue(new Board(blocks));
		}
		
		if (index%N < N-1) {
			int[] blocks = copy(board);
			exch(blocks, index, index+1);
			neighbors.enqueue(new Board(blocks));
		}
		
		return neighbors;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
	    s.append(N + "\n");
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            s.append(String.format("%2d ", board[i*N + j]));
	        }
	        s.append("\n");
	    }
	    return s.toString();
	}
	
	private int[] copy(int[] blocks) {
		int[] copy = new int[blocks.length];
		
		for (int i = 0; i < blocks.length; i++) {
			copy[i] = blocks[i];
		}
		return copy;
	}
	
	private void exch(int[] blocks, int i, int j) {
		int temp = blocks[i];
		blocks[i] = blocks[j];
		blocks[j] = temp;
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
