import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    }
}

