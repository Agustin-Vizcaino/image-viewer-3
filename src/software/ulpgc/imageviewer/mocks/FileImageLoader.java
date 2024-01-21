package software.ulpgc.imageviewer.mocks;

import software.ulpgc.imageviewer.ImageFile;
import software.ulpgc.imageviewer.ImageLoader;
import software.ulpgc.imageviewer.LoadedImage;

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
            System.out.println(i);
            ImageFile buffer = load(i, images.size());
            if (buffer.getImage() != null) images.add(buffer);
        }
    }

    @Override
    public ImageFile get(int index) {
        System.out.println(index + " " + mod(index, images.size()));
        return images.get(mod(index, images.size()));
    }

    int mod(int a, int b) {
        int c = a % b;
        return (c < 0) ? c + b : c;
    }


    private ImageFile load(String path, int index) {
        return new LoadedImage(path, index);
    }
}
