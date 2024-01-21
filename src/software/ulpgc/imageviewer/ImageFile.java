package software.ulpgc.imageviewer;

import java.awt.*;

public interface ImageFile {
    String getPath();
    Image getImage();
    int[] resize(int displayWidth, int displayHeight);
    int next();
    int prev();
}
