
public class Pnt {

    private double[] coordinates;

    public Pnt (double... coords) {
        coordinates = new double[coords.length];
        System.arraycopy(coords, 0, coordinates, 0, coords.length);
    }

    @Override
    public String toString () {
        if (coordinates.length == 0) return "Pnt()";
        String result = "Pnt(" + coordinates[0];
        for (int i = 1; i < coordinates.length; i++)
            result = result + "," + coordinates[i];
        result = result + ")";
        return result;
    }

    @Override
    public boolean equals (Object other) {
        if (!(other instanceof Pnt)) return false;
        Pnt p = (Pnt) other;
        if (this.coordinates.length != p.coordinates.length) return false;
        for (int i = 0; i < this.coordinates.length; i++)
            if (this.coordinates[i] != p.coordinates[i]) return false;
        return true;
    }

    @Override
    public int hashCode () {
        int hash = 0;
        for (double c: this.coordinates) {
            long bits = Double.doubleToLongBits(c);
            hash = (31*hash) ^ (int)(bits ^ (bits >> 32));
        }
        return hash;
    }

    public double coord (int i) {
        return this.coordinates[i];
    }

    public int dimension () {
        return coordinates.length;
    }

    public int dimCheck (Pnt p) {
        int len = this.coordinates.length;
        if (len != p.coordinates.length)
            throw new IllegalArgumentException("Dimension mismatch");
        return len;
    }

    public Pnt extend (double... coords) {
        double[] result = new double[coordinates.length + coords.length];
        System.arraycopy(coordinates, 0, result, 0, coordinates.length);
        System.arraycopy(coords, 0, result, coordinates.length, coords.length);
        return new Pnt(result);
    }

    public double dot (Pnt p) {
        int len = dimCheck(p);
        double sum = 0;
        for (int i = 0; i < len; i++)
            sum += this.coordinates[i] * p.coordinates[i];
        return sum;
    }

    public Pnt subtract (Pnt p) {
        int len = dimCheck(p);
        double[] coords = new double[len];
        for (int i = 0; i < len; i++)
            coords[i] = this.coordinates[i] - p.coordinates[i];
        return new Pnt(coords);
    }

    public Pnt add (Pnt p) {
        int len = dimCheck(p);
        double[] coords = new double[len];
        for (int i = 0; i < len; i++)
            coords[i] = this.coordinates[i] + p.coordinates[i];
        return new Pnt(coords);
    }

    public Pnt bisector (Pnt point) {
        dimCheck(point);
        Pnt diff = this.subtract(point);
        Pnt sum = this.add(point);
        double dot = diff.dot(sum);
        return diff.extend(-dot / 2);
    }   
}
