import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
	private double [] x = null;
	private int trials;
	
	public PercolationStats(int n, int t) {
		
		if(n < 0 || t < 0) {
			throw new java.lang.IllegalArgumentException();
		}
		trials = t;
		x = new double[trials];
		
	
		for(int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(n);
			int count = 0;
			
			while(!perc.percolates()) {
				int randRow = StdRandom.uniform(n);
				int randCol = StdRandom.uniform(n);
				
				if(!perc.isFull(randRow+1, randCol+1)) {
					if(!perc.isOpen(randRow+1, randCol+1)) {
						perc.open(randRow+1, randCol+1);
						count++;
					}
					
					if(perc.percolates()) {
						int N = n*n;
						x[i] = (double)count/N;
						break;
					}
				}
			}
		}
		
	}
	
	public double mean() {
		return StdStats.mean(x);
	}
	
	public double stddev() {
		return StdStats.stddev(x);
	}
	
	public double confidenceLo() {
		return (mean() - (1.96*stddev())/Math.sqrt(trials));
	}
	
	public double confidenceHi() {
		return (mean() + (1.96*stddev())/Math.sqrt(trials));
	}
	
	public static void main(String [] args) {
		StdOut.println("Enter the grid size and no. of trials.");
		int n = StdIn.readInt();
		int T = StdIn.readInt();
		PercolationStats percStats = new PercolationStats(n, T);
		StdOut.println("mean = " + percStats.mean());
		StdOut.println("stddev = " + percStats.stddev());
		StdOut.println("95% confidence interval = " + "[" + 
						percStats.confidenceLo()+", "+ percStats.confidenceHi()+"]");

		
	}
}
