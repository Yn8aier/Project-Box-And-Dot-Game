package demo1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DotsBoxes {
//    private List<Edge> edges = new ArrayList<>();
//    private List<Dot> dots = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Dot> dots = new ArrayList<>();
    private Color currentColor = Color.RED;

    public DotsBoxes(int canvasWidth, int canvasHeight) {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 300);
        StdDraw.setYscale(0, 300);
        initialize();
    }

    public void initialize() {
        edges.add(new Edge(75, 70, 150, 10));
        edges.add(new Edge(75, 220, 150, 10));
        edges.add(new Edge(70, 75, 10, 150));
        edges.add(new Edge(220, 75, 10, 150));

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                dots.add(new Dot(75 + 150 * i, 75 + 150 * j, 15));
            }
        }
    }

    public void update() {
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        boolean foundEdge = false;
        for (Edge edge : edges) {
            if (edge.isFree()) {
                if (!foundEdge && edge.getBounds().contains(mousePoint)) {
                    edge.setColor(currentColor);
                    edge.setVisible(true);

                    if (isMousePressed) {
                        currentColor = currentColor == Color.RED ? Color.BLUE : Color.RED;
                        edge.setFree(false);
                    }
                    foundEdge = true; // Avoid multiple selections.
                } else {
                    edge.setVisible(false);
                }
            }
        }
    }

    public void paint() {
        StdDraw.clear();
        // Paint edges first, so dots will be on the top layer.
        for (Edge edge: edges) {
            edge.paint();
        }
        for (Dot dot: dots) {
            dot.paint();
        }
//      edges.forEach(Edge::paint);
//      dots.forEach(Dot::paint);
        StdDraw.show();
    }

    public static void main(String[] args) {
        DotsBoxes game = new DotsBoxes(400, 400);

        while (true) {
            game.update();
            game.paint();
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
