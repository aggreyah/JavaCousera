/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private HashMap<MapNode, List<MapNode>> mMapAdjList;
	private HashSet<MapGraphEdge> mEdgeList;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		this.mMapAdjList = new HashMap<MapNode, List<MapNode>>();
		this.mEdgeList = new HashSet<MapGraphEdge>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return this.mMapAdjList.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		HashSet<MapNode> vertexNodes = new HashSet<MapNode>();
		HashSet<GeographicPoint> vertexPoints = new HashSet<GeographicPoint>();
		vertexNodes.addAll(this.mMapAdjList.keySet());
		//Return all geographic points assembled.
		Iterator<MapNode> it = vertexNodes.iterator();
		while (it.hasNext()) {
			MapNode next = it.next();
			GeographicPoint pointNext = new GeographicPoint(next.getPoint().getX(), next.getPoint().getY());
			vertexPoints.add(pointNext);
		}
		Iterator<GeographicPoint> it1 = vertexPoints.iterator();
		while(it.hasNext())
			System.out.println("***Vertices***" + it1.next());
		return vertexPoints;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3		}
		//Return the total number of edges.
		return this.mEdgeList.size();
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		//If location to be added is null we straight return false
		if (location == null)
			return false;
		/**
		 * A variable to tell us if an addition should be effected given a default value true.
		 * If location is already added we flip it to false indicating addition not possible.
		 * */
		boolean additionSuccessful = false;
		MapNode locationNode = new MapNode(location.getX(), location.getY());
		if (!this.mMapAdjList.containsKey(locationNode)) {
			//If location already in graph we set additionSuccessful variable to false;
			additionSuccessful = true;
			this.mMapAdjList.put(locationNode, new ArrayList<MapNode>());
		}
		//we then return the addition successful.
		return additionSuccessful;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		//Append node to to the end of the list value associated with the vertex from
		MapNode fromNode = new MapNode(from.getX(), from.getY());
		MapNode toNode = new MapNode(to.getX(), to.getY());
		
		MapGraphEdge edge = new MapGraphEdge(from, to);
		edge.setRoadName(roadName);
		edge.setRoadType(roadType);
		edge.setRoadLength(length);
		this.mEdgeList.add(edge);
		this.mMapAdjList.get(fromNode).add(toNode);
	}
	
	public MapGraphEdge findEdge(GeographicPoint from, GeographicPoint to) {
		for (MapGraphEdge edge : this.mEdgeList)
			if (edge.getFrom().getPoint().equals(from) && edge.getTo().getPoint().equals(to))
				return edge;
		return null;
	}
	
	//find a vertex form the set of vertices of map
	public MapNode findVertex(MapNode vert) {
		for (MapNode   n : this.mMapAdjList.keySet()) {
			if(n.equals(vert))
				return n;
		}
		return null;
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		LinkedList<MapNode> queue = new LinkedList<MapNode>();
		HashMap<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();
		boolean success = false;
		
		MapNode startNode = new MapNode(start.getX(), start.getY());
		MapNode goalNode = new MapNode(goal.getX(), goal.getY());
		
		parentMap.put(startNode, null);
		queue.add(startNode);
		
		while (!queue.isEmpty()) {
			MapNode currNode = queue.remove();
			nodeSearched.accept(currNode.getPoint());
			if (currNode.equals(goalNode)) {
				success = true;
				break;
			}
			List<MapNode> neighbors = this.mMapAdjList.get(currNode);
			ListIterator<MapNode> it = neighbors.listIterator();
			while(it.hasNext()) {
				MapNode next = it.next();
				if (!parentMap.containsKey(next)) {
					queue.add(next);
					parentMap.put(next, currNode);
				}
			}
		}
		if (!success) {
			return null;
		}		
		return reBuildPath(parentMap, startNode, goalNode);
		
	}
	

	public LinkedList<GeographicPoint> reBuildPath(HashMap<MapNode, MapNode> nodeMap, MapNode start, MapNode goal) {
		// TODO Auto-generated method stub
		LinkedList<GeographicPoint> path = new LinkedList<GeographicPoint>();
		MapNode curr = goal;
		while (curr != start) {
			GeographicPoint currPoint = new GeographicPoint(curr.getPoint().getX(), curr.getPoint().getY());
			path.addFirst(currPoint);
			curr = nodeMap.get(curr);
		}
		GeographicPoint startPoint = new GeographicPoint(start.getPoint().getX(), start.getPoint().getY());
		path.addFirst(startPoint);
		return path;
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		MapNodeDistComparator comparator = new MapNodeDistComparator();
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>(comparator);
		HashMap<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();
		HashMap<MapNode, Double> distanceMap = new HashMap<MapNode, Double>();
		
		MapNode startNode = new MapNode(start.getX(), start.getY());
		MapNode goalNode = new MapNode(goal.getX(), goal.getY());
		
		parentMap.put(startNode, null);
		distanceMap.put(startNode, 0.0);
		queue.add(startNode);
		
		boolean success = false;
		int nodes = 0;
		
		while (!queue.isEmpty()) {
			MapNode curr = queue.remove();
			nodeSearched.accept(curr.getPoint());
			System.out.println("Curr node: " + curr);
			nodes += 1;
			if (curr.equals(goalNode)) {
				success = true;
				break;
			}
			List<MapNode> neighbors = this.mMapAdjList.get(curr);
			ListIterator<MapNode> it = neighbors.listIterator();
			while(it.hasNext()) {
				MapNode next = it.next();
				MapGraphEdge edge = this.findEdge(curr.getPoint(), next.getPoint());
				double newDist = distanceMap.get(curr) + edge.getRoadLength();
				
				if (!distanceMap.containsKey(next)) {
					parentMap.put(next, curr);
					distanceMap.put(next, newDist);
					next.setDistance(newDist);
					queue.add(next);
				}
				if (distanceMap.containsKey(next) && newDist < distanceMap.get(next)) {
					parentMap.remove(next);
					parentMap.put(next, curr);
					distanceMap.remove(next);
					distanceMap.put(next, newDist);
					next.setDistance(newDist);
					queue.add(next);
				}
			}
		}
		System.out.println("Nodes searched in dijkstra search: " + nodes);
		if (!success) {
			System.out.println("path not found!");
			return null;
		}
		return reBuildPath(parentMap, startNode, goalNode);
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public LinkedList<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public LinkedList<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		
//		 Hook for visualization.  See writeup.
//		nodeSearched.accept(next.getLocation());
		
		MapNodeDistComparatorForAStar comparator = new MapNodeDistComparatorForAStar(goal);
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>(comparator);
		HashMap<MapNode, MapNode> parentMap = new HashMap<MapNode, MapNode>();
		HashMap<MapNode, Double> distanceMap = new HashMap<MapNode, Double>();
		
		MapNode startNode = new MapNode(start.getX(), start.getY());
		MapNode goalNode = new MapNode(goal.getX(), goal.getY());
		
		parentMap.put(startNode, null);
		distanceMap.put(startNode, 0.0);
		queue.add(startNode);
		
		boolean success = false;
		int nodes = 0;
		
		while (!queue.isEmpty()) {
			MapNode curr = queue.remove();
			nodeSearched.accept(curr.getPoint());
//			System.out.println("Curr node: " + curr);
			nodes += 1;
			if (curr.equals(goalNode)) {
				success = true;
				break;
			}
			List<MapNode> neighbors = this.mMapAdjList.get(curr);
			ListIterator<MapNode> it = neighbors.listIterator();
			while(it.hasNext()) {
				MapNode next = it.next();
				MapGraphEdge edgeCurrNext = this.findEdge(curr.getPoint(), next.getPoint());
				double newDist = distanceMap.get(curr) + edgeCurrNext.getRoadLength();
				
				if (!distanceMap.containsKey(next)) {
					parentMap.put(next, curr);
					distanceMap.put(next, newDist);
					next.setDistance(newDist);
					queue.add(next);
				}
				if (distanceMap.containsKey(next) && newDist < distanceMap.get(next)) {
					parentMap.remove(next);
					parentMap.put(next, curr);
					distanceMap.remove(next);
					distanceMap.put(next, newDist);
					next.setDistance(newDist);
					queue.add(next);
				}
			}
		}
//		System.out.println("Nodes searched in a* search: " + nodes);
		if (!success) {
			System.out.println("path not found!");
			return null;
		}
		return reBuildPath(parentMap, startNode, goalNode);
	}
	
	//Project Extension
	
	/**
	 * lengthOfPath returns the distance between the nodes list supplied to it
	 * @param list of nodes whose path distance we seek to determine.
	 * @return a double of the length between the nodes supplied.
	 *  including the length of path back to the start node. 
	 */
	public double lengthOfPath(LinkedList<GeographicPoint> list) {
		if(list.size() == 0)
			return 0.0;
		GeographicPoint first = list.getFirst();
		ListIterator<GeographicPoint> it = list.listIterator();
		double distance = 0.0;
		while(it.hasNext()) {
			GeographicPoint next = it.next();
			distance += first.distance(next);
			first = next;
		}
		distance += list.getLast().distance(list.getFirst());
		return distance;
	}
	
	/**
	 * traverses the path using the nearest neighbor heuristic
	 * @param set of map nodes that are to be visited at least once.
	 * @return returns a linked list of nodes that has the least cost.
	 * visiting the nearest of all the neighbors from each node.
	 * */
	public LinkedList<GeographicPoint> nearestNeighbor(HashSet<MapNode> mapNodes){
		List<MapNode> listNodes = new ArrayList<MapNode>(mapNodes);
		MapNode start = listNodes.get(0);
		LinkedList<MapNode> queue = new LinkedList<MapNode>();
		queue.add(start);
		LinkedList<MapNode> visited = new LinkedList<MapNode>();
		while(!queue.isEmpty()) {
			MapNode curr = queue.remove();
			List<MapNode> neighbors = listNodes;
			neighbors.remove(curr);
			ListIterator<MapNode> it = neighbors.listIterator();
			MapNode nearestNeighbor = null;
			double nearestDistance = 0.0;
			while(it.hasNext()) {
				MapNode next = it.next();
				if (visited.contains(next))
					continue;
				double nodesDistance = curr.getPoint().distance(next.getPoint());
				if(nearestNeighbor == null || nodesDistance < nearestDistance) {
					nearestNeighbor = next;
					nearestDistance = nodesDistance;
				}				
			}
			visited.addLast(curr);
			if (nearestNeighbor != null)
				queue.add(nearestNeighbor);
		}
//		visited.addLast(start);
		LinkedList<GeographicPoint> visitedPoints = new LinkedList<GeographicPoint>();
		for (MapNode item: visited) {
			visitedPoints.addLast(item.getPoint());
		}
		System.out.println("Length: " + this.lengthOfPath(visitedPoints));
		return visitedPoints;		
	}
	
	/**
	 * twoOpt uses the two-opt algorithm modifying path to establish a shorter path.
	 * @param input is a greedy path established from the nearest neighbor algorithm.
	 * @return returns a cheaper path than the greedy input path.
	 */
	public LinkedList<GeographicPoint> twoOpt(LinkedList<GeographicPoint> greedy){
		
		TreeMap<Double, LinkedList<GeographicPoint>> pathD = new TreeMap<Double, LinkedList<GeographicPoint>>();
		int i = 1;
		while(i < greedy.size() + 1) {
			LinkedList<GeographicPoint> pathToSwap = new LinkedList<GeographicPoint>();
			pathToSwap.addAll(greedy);
			int pathSize = pathToSwap.size();
			GeographicPoint point1;
			GeographicPoint point2 ;
			try {
				point1 = pathToSwap.get(i);
			}
			catch (Exception e) {
				// TODO: handle exception
				point1 = pathToSwap.get(i % pathSize);
			}
			try {
				point2 = pathToSwap.get(i + 1);
			}
			catch (Exception e) {
				// TODO: handle exception
				point2 = pathToSwap.get((i + 1) % pathSize);
			}
			try {
				pathToSwap.remove(i);
				pathToSwap.add(i, point2);
			}
			catch (Exception e) {
				// TODO: handle exception
				pathToSwap.remove(i % pathSize);
				pathToSwap.add(i % pathSize, point2);
			}
			try {
				pathToSwap.remove(i + 1);
				pathToSwap.add(i + 1, point1);	
			}
			catch (Exception e) {
				// TODO: handle exception
				pathToSwap.remove((i + 1) % pathSize);
				pathToSwap.add((i + 1) % pathSize, point1);
			}
			
			System.out.println("Curr path: " + pathToSwap);
			Double currLength = this.lengthOfPath(pathToSwap);
			if (currLength < this.lengthOfPath(greedy)) {
				pathD.put(currLength, pathToSwap);
			}
			i ++;			
		}
		LinkedList<GeographicPoint> leastLengthPath = new LinkedList<GeographicPoint>();
		try {
			leastLengthPath = pathD.get(pathD.firstKey());
		}
		catch (Exception e) {
			// TODO: handle exception
			if (pathD.isEmpty()) {
				leastLengthPath.addAll(greedy);
			}
		}
		
//		System.out.println("Least Length: " + this.lengthOfPath(leastLength));
//		System.out.println("Path:\n" + leastLengthPath);
		return leastLengthPath;
	}
	/**
	 * uses the nearest neighbor plus the two-opt to get cheaper paths
	 * @param a nodeset
	 * @return returns a linked list of a path that is cheapest through iteration set by user.
	 * */
	
	public LinkedList<GeographicPoint> nearestNeighborWithTwoOpt(HashSet<MapNode> nodeSet){
		LinkedList<GeographicPoint> nearestNPath = nearestNeighbor(nodeSet);
		int numberOfIterations = 100;
		LinkedList<GeographicPoint> improvedPath = null;
		for (int i = 0; i < numberOfIterations; i++) {
			improvedPath = twoOpt(nearestNPath);
			nearestNPath.clear();
			nearestNPath.addAll(improvedPath);
		}
		return improvedPath;
	}
	
	
	
	
	@Override
	public String toString() {
		String returnString = "";
		for (MapNode node: this.mMapAdjList.keySet()) {
			returnString += node + " ==> " + this.mMapAdjList.get(node) + "\n";
		}
		return "***MapGraph***" +  "\n"  + returnString;
	}

	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		GeographicPoint startP = new GeographicPoint(1.0,1.0);
		MapNode startPN = new MapNode(startP.getX(), startP.getY());
		GeographicPoint startP1 = new GeographicPoint(4.0, 1.0);
		MapNode startP1N = new MapNode(startP1.getX(), startP1.getY());
		GeographicPoint startP2 = new GeographicPoint(4.0, 0.0);
		MapNode startP2N = new MapNode(startP2.getX(), startP2.getY());
		GeographicPoint startP3 = new GeographicPoint(4.0, 2.0);
		MapNode startP3N = new MapNode(startP3.getX(), startP3.getY());
		GeographicPoint startP4 = new GeographicPoint(4.0, -1.0);
		MapNode startP4N = new MapNode(startP4.getX(), startP4.getY());
		GeographicPoint startP5 = new GeographicPoint(5.0, 1.0);
		MapNode startP5N = new MapNode(startP5.getX(), startP5.getY());
		GeographicPoint startP6 = new GeographicPoint(6.5, 0.0);
		MapNode startP6N = new MapNode(startP6.getX(), startP6.getY());
		GeographicPoint startP7 = new GeographicPoint(8.0, -1.0);
		MapNode startP7N = new MapNode(startP7.getX(), startP7.getY());
		GeographicPoint endP= new GeographicPoint(7, 3);
		MapNode endPN = new MapNode(endP.getX(), endP.getY());
		HashSet<MapNode> nodeSet = new HashSet<MapNode>();
		nodeSet.add(startPN);
		nodeSet.add(startP1N);
		nodeSet.add(startP2N);
		nodeSet.add(startP3N);
		nodeSet.add(startP4N);
		nodeSet.add(startP5N);
		nodeSet.add(startP6N);
		nodeSet.add(startP7N);
		nodeSet.add(endPN);
		System.out.println(firstMap);
		
		System.out.println("***Shortest Path***");
		System.out.println(firstMap.aStarSearch(startP5, startP3));
		LinkedList<GeographicPoint> myList = new LinkedList<GeographicPoint>();
		myList = firstMap.nearestNeighbor(nodeSet);
		System.out.println("Greedy path length:\n" + firstMap.lengthOfPath(myList));
		LinkedList<GeographicPoint> twoOptP = firstMap.nearestNeighborWithTwoOpt(nodeSet);
		System.out.println("Improved path ==> " + firstMap.lengthOfPath(twoOptP)
			+ "\n" + twoOptP);
		
//		System.out.println("***Edges***");
//		for (MapGraphEdge e : firstMap.mEdgeList)
//			System.out.println(e.getFrom() + " <=> " + e.getTo());
//		System.out.println("\nPath: " + firstMap.bfs(start, goal));
//		for (MapNode key: firstMap.mMapAdjList.keySet()) {
//			System.out.println(key + ": " + firstMap.mMapAdjList.get(key));
//		}
//		GeographicPoint start = new GeographicPoint(1.0, 1.0);
//		GeographicPoint goal = new GeographicPoint(8.0, -1.0);
//		List<GeographicPoint> pathBFS = firstMap.bfs(start, goal);
//		List<GeographicPoint> pathDijkstra = firstMap.dijkstra(start, goal);
//		List<GeographicPoint> pathAStar = firstMap.aStarSearch(start, goal);
//		System.out.println("***BFS***\n");
//		System.out.println(pathBFS);
//		System.out.println("***Dijkstra***\n");
//		System.out.println(pathDijkstra);
//		System.out.println("***AStar***\n");
//		System.out.println(pathAStar);
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
//		 MapGraph simpleTestMap = new MapGraph();
//			GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
//			
//			GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
//			GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
//			
//			System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
//			List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
//			List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
//			
//			
//			MapGraph testMap = new MapGraph();
//			GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
//			
//			// A very simple test using real data
//			testStart = new GeographicPoint(32.869423, -117.220917);
//			testEnd = new GeographicPoint(32.869255, -117.216927);
//			System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
//			testroute = testMap.dijkstra(testStart,testEnd);
//			testroute2 = testMap.aStarSearch(testStart,testEnd);
//			
//			
//			// A slightly more complex test using real data
//			testStart = new GeographicPoint(32.8674388, -117.2190213);
//			testEnd = new GeographicPoint(32.8697828, -117.2244506);
//			System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
//			testroute = testMap.dijkstra(testStart,testEnd);
//			testroute2 = testMap.aStarSearch(testStart,testEnd);
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		

//		List<GeographicPoint> route = theMap.dijkstra(start,end);
//		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);
		
	}	
}
