import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
	
	private final ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
	
	public FastCollinearPoints(Point[] points) {
		
		/**
		 * Checking the arguments of the constructor for the 
		 * IllgegalArgumentException, NullPointerException and for Duplicate Points
		 */
		checkArguments(points);
		checkDuplicates(points);
		
		for (Point p : points) {
			Point[] copyPoints = Arrays.copyOf(points, points.length);
			Arrays.sort(copyPoints, p.slopeOrder());
			
			ArrayList<Point> collinearPoints = new ArrayList<Point>();
			double previousSlope = Double.NEGATIVE_INFINITY;
			double slope = 0.0;
			for (Point q : copyPoints) {
				slope = p.slopeTo(q);
				if (Double.compare(slope,  previousSlope) == 0) {
					collinearPoints.add(q);
				}
				else {
					if (collinearPoints.size() >= 3) {
						collinearPoints.add(p);
						addLineSegment(collinearPoints);
					}
					collinearPoints.clear();
					collinearPoints.add(q);
				}
				previousSlope = slope;
			}
			
			if (collinearPoints.size() >= 3) {
				collinearPoints.add(p);
				addLineSegment(collinearPoints);
			}
		}
	
	}
	
	private void addLineSegment(ArrayList<Point> collinearPoints) {
		ArrayList<Point> endPoints = new ArrayList<Point>();
		Point startPoint = collinearPoints.get(0);
		Point endPoint = collinearPoints.get(collinearPoints.size() - 1);
		
		if (endPoints.isEmpty()) {
			endPoints.add(endPoint);
			segments.add(new LineSegment(startPoint, endPoint));
		}
		else {
			for (Point curEndPoint : endPoints) {
				if (curEndPoint.compareTo(endPoint) == 0) {
					return;
				}
				
			}
			endPoints.add(endPoint);
			segments.add(new LineSegment(startPoint, endPoint));
		}
		
		
		
		
	}
	
	public int numberOfSegments() {
		return segments.size();
	}
	
	public LineSegment[] segments() {
		return segments.toArray(new LineSegment[segments.size()]);
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