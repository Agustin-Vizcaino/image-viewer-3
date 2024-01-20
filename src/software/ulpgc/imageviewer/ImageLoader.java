package software.ulpgc.imageviewer;

public interface ImageLoader {
    void setupImages(String[] paths);
    ImageFile get(int index);
}
