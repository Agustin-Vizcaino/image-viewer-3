package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageDisplay;
import software.ulpgc.imageviewer.ImageFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private static final Map<String,Color> colors = Map.of(
            "red", Color.RED,
            "green", Color.GREEN,
            "blue", Color.BLUE
    );
    /*@Override
    public void paint(Graphics g) {
        //C:\Users\Usuario\Pictures\Wallpapers\Captain Harlock.jpg
        g.drawImage("C:\\Users\\Usuario\\Pictures\\Wallpapers\\Captain Harlock.jpg",800,600);
        /*
        for (Paint paint : paints) {
            g.setColor(colors.get(paint.id));
            g.fillRect(paint.offset, 0, 800, 600);
        }
    }*/

    @Override
    public void paint(Graphics g) {
        try {
            Image image = ImageIO.read(new File("C:\\Users\\Usuario\\Pictures\\Wallpapers\\Captain Harlock.jpg"));

            // Uncomment the following code if you want to use the paints list
        for (Paint paint : paints) {
            g.drawImage(image, paint.offset, 0, 100,100,null);
            /*g.setColor(colors.get(paint.id));
            g.fillRect(paint.offset, 0, 800, 600);*/
        }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
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
