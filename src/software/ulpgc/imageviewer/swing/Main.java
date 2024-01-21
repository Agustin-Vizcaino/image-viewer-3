package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageFile;
import software.ulpgc.imageviewer.ImageLoader;
import software.ulpgc.imageviewer.ImagePresenter;
import software.ulpgc.imageviewer.mocks.FileImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    //final static List<String> paths = new ArrayList<String>();
    final static String path = "C:\\Users\\Usuario\\Pictures\\Wallpapers\\";
    //final static String path = "C:\\Users\\Usuario\\Desktop";
    private static ImageLoader loader;
    static {
        loader = new FileImageLoader();
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        loader.setupImagesFromDir(path);
        if (loader.imageAmount() > 0) {
            ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay(), loader);
            presenter.show(image());
            frame.setVisible(true);
        } else {
            System.out.println("Error: no images found");
        }
    }

    private static ImageFile image() {
        return loader.get(0);
    }
}
