import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Client {
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In("src/collinear/" + args[0]);
	    int n = in.readInt();
	    StdOut.print(n);
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	        StdOut.println("("+x + ", " + y + ")");
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	    	StdOut.println("Drawing point : " + p.toString());
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        StdOut.println("Drawing Line Segment : " + segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}

}
