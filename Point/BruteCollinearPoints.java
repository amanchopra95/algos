import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	
	private final LineSegment[] segments;
	
	
	public BruteCollinearPoints(Point[] points) {
		
		/**
		 * Checking the arguments of the constructor for the 
		 * IllgegalArgumentException, NullPointerException and for Duplicate Points
		 */
		checkArguments(points);
		checkDuplicates(points);
		
		ArrayList<LineSegment> collinearPoints = new ArrayList<LineSegment>();
		Point[] copyPoints = Arrays.copyOf(points, points.length); 
		Arrays.sort(copyPoints);
		for (int p = 0; p < copyPoints.length - 3; p++) {
			for (int q = p+1; q < copyPoints.length - 2; q++) {
				for (int r = q+1; r < copyPoints.length - 1; r++) {
					for (int s = r+1; s < copyPoints.length; s++) {
						double m1 = copyPoints[p].slopeTo(copyPoints[q]);
						double m2 = copyPoints[p].slopeTo(copyPoints[r]);
						double m3 = copyPoints[p].slopeTo(copyPoints[s]);
						if (Double.compare(m1, m2) == 0 && Double.compare(m1, m3) == 0) {
							collinearPoints.add(new LineSegment(copyPoints[p], copyPoints[s]));	
						}
						/* if ((copyPoints[p].slopeTo(copyPoints[q]) == copyPoints[p].slopeTo(copyPoints[r])) 
								&& (copyPoints[p].slopeTo(copyPoints[q]) == copyPoints[p].slopeTo(copyPoints[s]))) {}*/
					}
				}
			}
		}
		segments = collinearPoints.toArray(new LineSegment[collinearPoints.size()]);
	}
	
	public int numberOfSegments() {
		return segments.length;
	}
	
	public LineSegment[] segments() {
		return Arrays.copyOf(segments,  numberOfSegments());
	}
	
	private void checkDuplicates(Point[] points) {
		for (int i = 0; i < points.length-1; i++) {
			for (int j = i+1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					throw new java.lang.IllegalArgumentException();
				}
			}
		}
	}
	
	private void checkArguments(Point[] points) {
		if (points == null) {
			throw new java.lang.IllegalArgumentException();
		}
		
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new java.lang.IllegalArgumentException();
			}
		}
	}
}
