/**
 * 
 */
package roadgraph;

import geography.GeographicPoint;

/**
 * @author aggreyah
 *
 */
public class MapNode {
	private  GeographicPoint mLatLong;
	private double mDistance;
	
	public MapNode(double lat, double lon) {
		this.mLatLong = new GeographicPoint(lat, lon);
		this.mDistance = Double.POSITIVE_INFINITY;
	}
	
	public void setDistance(double dist) {this.mDistance = dist;}
	public GeographicPoint getPoint() {return this.mLatLong;}
	public double getDistance() {return this.mDistance;}
	
	public double distance(GeographicPoint other)
	{
		return getDist(this.mLatLong.getX(), this.mLatLong.getY(),
                other.getX(), other.getY());     
	}
	
	public double heuristic(GeographicPoint mLatLong, GeographicPoint goal)
	{
		return getDist(mLatLong.getX(), mLatLong.getY(),
                goal.getX(), goal.getY());     
	}
	
	private double getDist(double lat1, double lon1, double lat2, double lon2)
    {
    	int R = 6373; // radius of the earth in kilometres
    	double lat1rad = Math.toRadians(lat1);
    	double lat2rad = Math.toRadians(lat2);
    	double deltaLat = Math.toRadians(lat2-lat1);
    	double deltaLon = Math.toRadians(lon2-lon1);

    	double a = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +
    	        Math.cos(lat1rad) * Math.cos(lat2rad) *
    	        Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
    	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

    	double d = R * c;
    	return d;
    }

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int) (this.mLatLong.getX() + this.mLatLong.getY());
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this.getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		MapNode node = (MapNode) obj;
		return this.mLatLong.getX() == node.mLatLong.getX() &&
				this.mLatLong.getY() == node.mLatLong.getY();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Lat: " + this.mLatLong.getX() + ", Lon: " + this.mLatLong.getY();
	}

}
