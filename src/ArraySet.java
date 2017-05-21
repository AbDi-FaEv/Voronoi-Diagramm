import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

public class ArraySet<E> extends AbstractSet<E> {

    private ArrayList<E> items;

    @Override
    public boolean add(E item) {
        if (items.contains(item)) return false;
        return items.add(item);
    }

    @Override
    public Iterator<E> iterator() {
        return items.iterator();
    }

    @Override
    public int size() {
        return items.size();
    }
}
