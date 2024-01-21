package software.ulpgc.imageviewer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadedImage implements ImageFile {
    private String path;
    private Image image;
    private int index;

    public LoadedImage(String path, int index) {
        try {
            image = ImageIO.read(new File(path));
            this.path = path;
            this.index = index;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Image getImage() {
        return image;
    }
    @Override
    public String getPath() {
        return path;
    }
    @Override
    public int[] resize(int displayWidth, int displayHeight) {
        int[] result = new int[2];

        double widthRatio = (double) displayWidth / image.getWidth(null);
        double heightRatio = (double) displayHeight / image.getHeight(null);

        double scaleFactor = Math.min(widthRatio, heightRatio);

        result[0] = (int) (image.getWidth(null) * scaleFactor);
        result[1] = (int) (image.getHeight(null) * scaleFactor);

        return result;
    }

    @Override
    public int next() {
        return index + 1;
    }

    @Override
    public int prev() {
        return index - 1;
    }
}
