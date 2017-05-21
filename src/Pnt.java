
public class Pnt {

    private double[] coordinates;

    public Pnt (double... coords) {
        coordinates = new double[coords.length];
        System.arraycopy(coords, 0, coordinates, 0, coords.length);
    }

    public double coord (int i) {
        return this.coordinates[i];
    }

    public int dimension () {
        return coordinates.length;
    }
}
