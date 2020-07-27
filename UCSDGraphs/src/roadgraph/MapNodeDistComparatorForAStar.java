package roadgraph;

import java.util.Comparator;

import geography.GeographicPoint;

/**
 * @author aggreyah
 *
 */
public class MapNodeDistComparatorForAStar implements Comparator<MapNode> {
	private GeographicPoint mGoal;
	public MapNodeDistComparatorForAStar(GeographicPoint goal) {
		// TODO Auto-generated constructor stub
		this.mGoal = goal;
	}

	@Override
	public int compare(MapNode o1, MapNode o2) {
		// TODO Auto-generated method stub
		if (o1.getDistance() + o1.heuristic(o1.getPoint(), mGoal) < o2.getDistance() + o2.heuristic(o2.getPoint(), mGoal))
			return -1;
		else if (o1.getDistance() + o1.heuristic(o1.getPoint(), mGoal) 
		== o2.getDistance() + o2.heuristic(o2.getPoint(), mGoal))
			return 0;
		else
			return 1;
	}
}