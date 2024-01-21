package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageDisplay;
import software.ulpgc.imageviewer.ImageFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private List<Paint> paints = new ArrayList<>();

    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initShift);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    @Override
    public void paint(ImageFile image, int offset) {
        paints.add(new Paint(image, offset));
        repaint();
    }

    @Override
    public void clear() {
        paints.clear();
    }

    @Override
    public void paint(Graphics g) {
        for (Paint paint : paints) {
            int[] proportions = paint.image.resize(getWidth(), getHeight());
            int x = paint.offset;
            int y = getHeight() / 2 - proportions[1] / 2;

            g.setColor(Color.WHITE);
            g.fillRect(x, 0, getWidth(), getHeight());

            g.drawImage(paint.image.getImage(), x, y, proportions[0], proportions[1], null);
        }
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }

    private record Paint(ImageFile image, int offset) {
    }
}
