package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageFile;
import software.ulpgc.imageviewer.ImageLoader;
import software.ulpgc.imageviewer.ImagePresenter;
import software.ulpgc.imageviewer.mocks.FileImageLoader;

public class Main {
    final static String[] paths = new String[] { "C:\\Users\\Usuario\\Pictures\\Wallpapers\\Captain Harlock.jpg", "C:\\Users\\Usuario\\Pictures\\Wallpapers\\What If.jpg", "C:\\Users\\Usuario\\Pictures\\Wallpapers\\Oblivion.jpg" };
    private static ImageLoader loader;
    static {
        loader = new FileImageLoader();
    }
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        loader.setupImages(paths);
        ImagePresenter presenter = new ImagePresenter(frame.getImageDisplay(), loader);
        presenter.show(image());
        frame.setVisible(true);
    }

    private static ImageFile image() {
        return loader.get(0);
    }
}
