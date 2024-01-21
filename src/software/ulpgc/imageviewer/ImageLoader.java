package software.ulpgc.imageviewer;

public interface ImageLoader {
    void setupImagesFromArray(String[] paths);
    void setupImagesFromDir(String path);
    ImageFile get(int index);
}
