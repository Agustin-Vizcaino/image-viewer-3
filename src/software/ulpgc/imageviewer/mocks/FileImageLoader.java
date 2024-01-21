package software.ulpgc.imageviewer.mocks;

import software.ulpgc.imageviewer.ImageFile;
import software.ulpgc.imageviewer.ImageLoader;
import software.ulpgc.imageviewer.LoadedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Image;

public class FileImageLoader implements ImageLoader {

    private List<ImageFile> images = new ArrayList<ImageFile>();
    @Override
    public void setupImagesFromArray(String[] paths) {
        for (String i : paths) {
            ImageFile buffer = load(i, images.size());
            if (buffer.getImage() != null) images.add(buffer);
        }
    }

    @Override
    public void setupImagesFromDir(String path) {
        List<String> imagePaths = new ArrayList<>();
        File directory = new File(path);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") ||
                            name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".gif") ||
                            name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".tiff"));

            if (files != null) {
                Arrays.stream(files).map(File::getAbsolutePath).forEach(imagePaths::add);
            }
        }

        for (String i : imagePaths) {
            ImageFile buffer = load(i, images.size());
            if (buffer.getImage() != null) images.add(buffer);
        }
    }

    @Override
    public ImageFile get(int index) {
        System.out.println(index + " " + index + " " + images.size());
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
