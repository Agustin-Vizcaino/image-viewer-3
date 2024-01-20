package software.ulpgc.imageviewer;

import java.awt.*;

public interface ImageFile {
    String getPath();
    int[] resize(int displayWidth, int displayHeight);
}
