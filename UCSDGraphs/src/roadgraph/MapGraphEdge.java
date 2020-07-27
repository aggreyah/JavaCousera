package roadgraph;

import geography.GeographicPoint;

public class MapGraphEdge {
	
	private MapNode mFrom;
	private MapNode mTo;
	private double mRoadLength;
	private String mRoadType;
	private String mRoadName;
	
	public MapGraphEdge(GeographicPoint from, GeographicPoint to) {
		MapNode nodeFrom = new MapNode(from.getX(), from.getY());
		MapNode nodeTo = new MapNode(to.getX(), to.getY());
		this.mFrom = nodeFrom;
		this.mTo = nodeTo;
		this.mRoadLength = nodeFrom.distance(to);
	}
	
	public void setRoadLength (double len) {this.mRoadLength = len;}
	public void setRoadType (String type) {this.mRoadType = type;}
	public void setRoadName (String name) {this.mRoadName = name;}
	
	
	public MapNode getFrom ( ) {return this.mFrom;}
	public MapNode getTo () {return this.mTo;}
	public double getRoadLength () {return this.mRoadLength;}
	public String getRoadType () {return this.mRoadType;}
	public String getRoadName () {return this.mRoadName;}
	
	

}
