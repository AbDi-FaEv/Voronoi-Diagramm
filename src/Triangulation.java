import java.util.AbstractSet;
import java.util.Iterator;

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
}
