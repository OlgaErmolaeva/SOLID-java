package learning.patterns.visitor._6another_domain;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

interface Element {
    void accept(Visitor visitor);
}

class Circle implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    Point center; int radius;
}
class Line implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    Point start, end;
}
class Rectangle implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    Point upperLeft; int width, height;
}

interface Visitor {
    void visit(Circle event);
    void visit(Line event);
    void visit(Rectangle event);
}

class ElementRenderer implements Visitor {
    Graphics g;

    public ElementRenderer(Graphics g) {
        this.g = g;
    }

    @Override
    public void visit(Circle circle) {
        g.drawOval(circle.center.x, circle.center.y, circle.radius, circle.radius);
    }

    @Override
    public void visit(Line line) {
        g.drawLine(line.start.x, line.start.y, line.end.x, line.end.y);
    }

    @Override
    public void visit(Rectangle rect) {
        g.drawRect(rect.upperLeft.x, rect.upperLeft.y, rect.width, rect.height);
    }
}

class ElementExporter1 implements Visitor {
    @Override
    public void visit(Circle circle) {
        output.append("Circle, center=").append(circle.center).append(", radius=").append(circle.radius).append("<br/>");
    }
    @Override
    public void visit(Line line) {
        output.append("Line, start=").append(line.start).append(", end=").append(line.end).append("<br/>");
    }

    @Override
    public void visit(Rectangle rect) {
        output.append("Rectangle, ")
                .append("upperLeft=").append(rect.upperLeft)
                .append(", width=").append(rect.width)
                .append(", height=").append(rect.height)
                .append("<br/>");
    }

    StringBuilder output = new StringBuilder("<html>");

    public String getOutput() {
        return output.toString();
    }
}

class PropertyRandomizer implements Visitor {

    final Random random;

    PropertyRandomizer(Random random) {
        this.random = random;
    }

    @Override
    public void visit(Circle circle) {
        circle.center = randomPoint();
        circle.radius = randomLength();
    }

    @Override
    public void visit(Line line) {
        line.start = randomPoint();
        line.end = randomPoint();
    }

    @Override
    public void visit(Rectangle rect) {
        rect.upperLeft = randomPoint();
        rect.width = randomLength();
        rect.height = randomLength();
    }

    private Point randomPoint() {
        return new Point(random.nextInt(800)+100, random.nextInt(800)+100);
    }

    private int randomLength() {
        return random.nextInt(500) + 100;
    }
}


public class CAD {

    public static final List<Supplier<Element>> SHAPE_FACTORIES = new ArrayList<>(Arrays.asList(Circle::new, Line::new, Rectangle::new));

    List<Element> elements = new ArrayList<>();
    private JPanel canvas = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            processWith(new ElementRenderer(g));
        }
    };

    private void processWith(Visitor visitor) {
        elements.forEach(e -> e.accept(visitor));
    }

    private void addRandomShape() {
        Random random = new Random();
        Element newShape = SHAPE_FACTORIES.get(random.nextInt(SHAPE_FACTORIES.size())).get();
        newShape.accept(new PropertyRandomizer(random));
        elements.add(newShape);
        canvas.repaint();
    }

    private void export() {
        ElementExporter1 exporter = new ElementExporter1();
        processWith(exporter);
        JOptionPane.showMessageDialog(canvas, new JLabel(exporter.getOutput()));
    }

    void buildGUI() {
        JFrame f = new JFrame("CAD");
        f.add(toolbar(), BorderLayout.NORTH);
        f.add(canvas);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    private JComponent toolbar() {
        JPanel p = new JPanel();
        p.add(button("Add random shape", this::addRandomShape));
        p.add(button("Export", this::export));
        return p;
    }

    private JButton button(String name, Runnable action) {
        JButton jButton = new JButton(name);
        jButton.addActionListener(e -> action.run());
        return jButton;
    }


    public static void main(String[] args) throws InterruptedException {
        CAD cad = new CAD();
        cad.buildGUI();
    }
}


