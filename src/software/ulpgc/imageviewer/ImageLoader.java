package software.ulpgc.imageviewer;

public interface ImageLoader {
    void setupImagesFromArray(String[] paths);
    void setupImagesFromDir(String path);
    int imageAmount();
    ImageFile get(int index);
}
