/**
 * 
 */
package roadgraph;

import java.util.Comparator;

/**
 * @author aggreyah
 *
 */
public class MapNodeDistComparator implements Comparator<MapNode> {

	@Override
	public int compare(MapNode o1, MapNode o2) {
		// TODO Auto-generated method stub
		if (o1.getDistance() < o2.getDistance())
			return -1;
		else if (o1.getDistance() == o2.getDistance())
			return 0;
		else
			return 1;
	}
}
