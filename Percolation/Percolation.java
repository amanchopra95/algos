import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
	
	private WeightedQuickUnionUF uf;
	private boolean [] grid;
	private int N, virBotIdx, virTopIdx;
	private int count;
	
	public Percolation(int n) {
		virTopIdx = n*n;
		virBotIdx = virTopIdx + 1;
		uf = new WeightedQuickUnionUF(virTopIdx + 2);
		N = n;
		count = 0;
		grid = new boolean[virTopIdx];
	}
	
	private int indexOf(int row, int col) {
		validate(row,col);
		return N*(row-1) + (col-1);
	}
	
	private void validate(int row, int col) {
		if (row<1 || row>N || col<1 || col>N) {
			throw new java.lang.IllegalArgumentException();
		}	
	}
	public void open(int row, int col) {
		
		validate(row, col);
		int cur = indexOf(row,col);
		if(!grid[indexOf(row,col)]) {
			
			grid[cur] = true;
			count++;
			
			/*int top = row > 1 ? indexOf(row-1, col) : -1;*/
			
			//Top
			if (row > 1 && isOpen (row-1, col)) {
				uf.union(cur, indexOf (row-1, col));
			}
			else if (row == 1) {
				uf.union(cur, virTopIdx);
			}
			//Bottom
			if(row < N && isOpen (row+1, col)) {
				uf.union (cur, N*row + (col-1));
			}
			else if (row == N) {
				uf.union (virBotIdx, cur);
			}
			//Left
			if (col > 1 && isOpen (row, col-1)) {
				uf.union (cur, N*(row-1) + (col-2));
			}
			//Right
			if (col < N && isOpen(row, col+1)) {
				uf.union(indexOf(row,col), N*(row-1) + col);
			}
		}
		return;
	}
	
	public boolean isOpen(int row, int col) {
		validate(row, col);
		return grid[indexOf(row,col)];
	}
	
	public boolean isFull(int row, int col) {
		validate(row, col);
		return isOpen(row,col) && uf.connected(indexOf(row,col), virTopIdx);
	}
	
	public int numberOfOpenSites() {
		return count;
	}
	
	public boolean percolates() {
		return uf.connected(virTopIdx, virBotIdx);
	}
	
	public static void main(String[] args) {
		int i, j;
		StdOut.printf("Enter the size of the grid : ");
		int size = StdIn.readInt();
		Percolation perc = new Percolation(size);
		for (int k = 0; k < size; k++) {
			StdOut.print("Enter sites to be open ");
			i = StdIn.readInt();
			j = StdIn.readInt();
			perc.open(i, j);
		}

		StdOut.print(perc.numberOfOpenSites());
		
		for (int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				StdOut.print(perc.isOpen(row+1, col+1));
			}
		}
		
		StdOut.print(perc.isFull(3,2));
		StdOut.print(perc.percolates());
	}
}
