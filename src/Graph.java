import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by dimad on 22.05.2017.
 */
public class Graph<N> {
    private Map<N, Set<N>> theNeighbors =
            new HashMap<N, Set<N>>();
    private Set<N> theNodeSet =
            Collections.unmodifiableSet(theNeighbors.keySet());


    public void add (N node) {
        if (theNeighbors.containsKey(node)) return;
        theNeighbors.put(node, new ArraySet<N>());
    }

    public void add (N nodeA, N nodeB) throws NullPointerException {
        theNeighbors.get(nodeA).add(nodeB);
        theNeighbors.get(nodeB).add(nodeA);
    }

    public void remove (N node) {
        if (!theNeighbors.containsKey(node)) return;
        for (N neighbor: theNeighbors.get(node))
            theNeighbors.get(neighbor).remove(node);    // Remove "to" links
        theNeighbors.get(node).clear();                 // Remove "from" links
        theNeighbors.remove(node);                      // Remove the node
    }


    public Set<N> neighbors (N node) throws NullPointerException {
        return Collections.unmodifiableSet(theNeighbors.get(node));
    }

    public Set<N> nodeSet () {
        return theNodeSet;
    }
}
