import java.util.*;

/**
 * Created by dimad on 22.05.2017.
 */
public class Triangulation  extends AbstractSet<Triangle> {
    private Triangle mostRecent = null;
    private Graph<Triangle> triGraph;

    public Triangulation (Triangle triangle) {
        triGraph = new Graph<Triangle>();
        triGraph.add(triangle);
        mostRecent = triangle;
    }
    @Override
    public Iterator<Triangle> iterator () {
        return triGraph.nodeSet().iterator();
    }

    @Override
    public int size () {
        return triGraph.nodeSet().size();
    }

    @Override
    public String toString () {
        return "Triangulation with " + size() + " triangles";
    }
    public boolean contains (Object triangle) {
        return triGraph.nodeSet().contains(triangle);
    }

    public Triangle neighborOpposite (Pnt site, Triangle triangle) {
        if (!triangle.contains(site))
            throw new IllegalArgumentException("Bad vertex; not in triangle");
        for (Triangle neighbor: triGraph.neighbors(triangle)) {
            if (!neighbor.contains(site)) return neighbor;
        }
        return null;
    }

    public Set<Triangle> neighbors(Triangle triangle) {
        return triGraph.neighbors(triangle);
    }

    public List<Triangle> surroundingTriangles (Pnt site, Triangle triangle) {
        if (!triangle.contains(site))
            throw new IllegalArgumentException("Site not in triangle");
        List<Triangle> list = new ArrayList<Triangle>();
        Triangle start = triangle;
        Pnt guide = triangle.getVertexButNot(site);        // Affects cw or ccw
        while (true) {
            list.add(triangle);
            Triangle previous = triangle;
            triangle = this.neighborOpposite(guide, triangle); // Next triangle
            guide = previous.getVertexButNot(site, guide);     // Update guide
            if (triangle == start) break;
        }
        return list;
    }

}
