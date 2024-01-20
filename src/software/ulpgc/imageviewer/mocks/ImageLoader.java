package software.ulpgc.imageviewer.mocks;

import software.ulpgc.imageviewer.ImageFile;
import software.ulpgc.imageviewer.ImageLoader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

public class FileImageLoader implements ImageLoader {

    private List<ImageFile> images = new ArrayList<ImageFile>();

    public void setupImages(String[] paths) {
        for (String i : paths) {
            try {

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ImageFile load(String path) {
        return new ImageFile(path) {
            private String path;
            private File image;

            public void ImageFile(String path) {
                load(this.path);
            }
            @Override
            public String getPath() {
                return path;
            }

            @Override
            public int[] resize(int displayWidth, int displayHeight) {
                return new int[0];
            }

            @Override
            public String id() {
                return ids[i];
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % ids.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i -1 : ids.length-1);
            }
        };
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String id() {
                return ids[i];
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % ids.length);
            }

            @Override
            public Image prev() {
                return imageAt(i > 0 ? i -1 : ids.length-1);
            }
        };
    }
}
