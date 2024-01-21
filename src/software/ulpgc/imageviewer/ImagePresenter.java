package software.ulpgc.imageviewer;

import software.ulpgc.imageviewer.ImageDisplay.*;

public class ImagePresenter {
    private final ImageDisplay display;
    private ImageFile image;
    private ImageLoader loader;

    public ImagePresenter(ImageDisplay display, ImageLoader images) {
        this.display = display;
        this.display.on((Shift) this::shift);
        this.display.on((Released) this::released);
        this.loader = images;
    }

    private void shift(int offset) {
        display.clear();
        display.paint(image, offset);
        if (offset > 0)
            display.paint(loader.get(image.prev()), offset - display.getWidth());
        else
            display.paint(loader.get(image.next()), display.getWidth() + offset);

    }

    private void released(int offset) {
        if (Math.abs(offset) >= display.getWidth() / 2)
            image = offset > 0 ? loader.get(image.prev()) : loader.get(image.next());
        repaint();
    }

    public void show(ImageFile image) {
        this.image = image;
        repaint();
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(image, 0);
    }
}
