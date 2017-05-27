import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by dimad on 23.05.2017.
 */
public class Voronoi extends javax.swing.JApplet
        implements Runnable, ActionListener, MouseListener {

    public static void main (String[] args) {

    }
    public void init () {

    }
    public void run () {

    }
    public void actionPerformed(ActionEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    static class VoronoiPanel extends JPanel {
        public static Color voronoiColor = Color.black;
        public static int pointRadius = 3;

        private Voronoi controller;
        private Triangulation dt;
        private Map<Object, Color> colorTable;
        private Triangle initialTriangle;
        private static int initialSize = 10000;
        private Graphics g;
        private Random random = new Random();

        public VoronoiPanel(Voronoi controller) {
            this.controller = controller;
            initialTriangle = new Triangle(
                    new Pnt(-initialSize, -initialSize),
                    new Pnt( initialSize, -initialSize),
                    new Pnt(           0,  initialSize));
            dt = new Triangulation(initialTriangle);
            colorTable = new HashMap<Object, Color>();
        }

        public void addSite(Pnt point) {
            dt.delaunayPlace(point);
        }

        public void clear() {
            dt = new Triangulation(initialTriangle);
        }

        private Color getColor (Object item) {
            if (colorTable.containsKey(item)) return colorTable.get(item);
            Color color = new Color(Color.HSBtoRGB(random.nextFloat(), 1.0f, 1.0f));
            colorTable.put(item, color);
            return color;
        }

        public void draw (Pnt point) {
            int r = pointRadius;
            int x = (int) point.coord(0);
            int y = (int) point.coord(1);
            g.fillOval(x-r, y-r, r+r, r+r);
        }

        public void draw (Pnt[] polygon, Color fillColor) {
            int[] x = new int[polygon.length];
            int[] y = new int[polygon.length];
            for (int i = 0; i < polygon.length; i++) {
                x[i] = (int) polygon[i].coord(0);
                y[i] = (int) polygon[i].coord(1);
            }
            if (fillColor != null) {
                Color temp = g.getColor();
                g.setColor(fillColor);
                g.fillPolygon(x, y, polygon.length);
                g.setColor(temp);
            }
            g.drawPolygon(x, y, polygon.length);
        }
        public void paintComponent (Graphics g) {
            super.paintComponent(g);
            this.g = g;
            Color temp = g.getColor();
            g.setColor(voronoiColor);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(temp);
            colorTable.clear();
            drawAllVoronoi(false, true);
            temp = g.getColor();
            g.setColor(Color.white);
            drawAllVoronoi(false, false);
            g.setColor(temp);
        }
        public void drawAllVoronoi (boolean withFill, boolean withSites) {
            HashSet<Pnt> done = new HashSet<Pnt>(initialTriangle);
            for (Triangle triangle : dt)
                for (Pnt site: triangle) {
                    if (done.contains(site)) continue;
                    done.add(site);
                    List<Triangle> list = dt.surroundingTriangles(site, triangle);
                    Pnt[] vertices = new Pnt[list.size()];
                    int i = 0;
                    for (Triangle tri: list)
                        vertices[i++] = tri.getCircumcenter();
                    draw(vertices, withFill? getColor(site) : null);
                    if (withSites) draw(site);
                }
        }
    }
}

