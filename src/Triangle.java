import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by dimad on 22.05.2017.
 */
public class Triangle extends ArraySet<Pnt>  {
    private int idNumber;
    private Pnt circumcenter = null;

    private static int idGenerator = 0;
    public static boolean moreInfo = false;

    public Triangle (Pnt... vertices) {
        this(Arrays.asList(vertices));
    }

    public Triangle (Collection<? extends Pnt> collection) {
        super(collection);
        idNumber = idGenerator++;
        if (this.size() != 3)
            throw new IllegalArgumentException("Triangle must have 3 vertices");
    }

    @Override
    public String toString () {
        if (!moreInfo) return "Triangle" + idNumber;
        return "Triangle" + idNumber + super.toString();
    }
    @Override
    public boolean add (Pnt vertex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Pnt> iterator () {
        return new Iterator<Pnt>() {
            private Iterator<Pnt> it = Triangle.super.iterator();
            public boolean hasNext() {return it.hasNext();}
            public Pnt next() {return it.next();}
            public void remove() {throw new UnsupportedOperationException();}
        };
    }

    @Override
    public int hashCode () {
        return (int)(idNumber^(idNumber>>>32));
    }

    @Override
    public boolean equals (Object o) {
        return (this == o);
    }
}
