package sk.uniza.fri.game;

import java.io.File;
import java.util.HashMap;

/**
 * Represents an image loader that loads images from a directory.
 *
 * @author Adrian Licko, Copilot (small part of the code)
 * @version 1.0
 * @since 1.0
 */
public class ImageLoader {
    private String actualDirectoryPath;
    private HashMap<Integer, String> imagePaths;
    private int currentIndex;

    /**
     * Constructor for the ImageLoader class.
     *
     * @param directoryPath - path to the directory where the images are stored
     */
    public ImageLoader(String directoryPath) {
        this.actualDirectoryPath = directoryPath;
        this.imagePaths = new HashMap<>();
        this.currentIndex = 0;
        this.loadImages(directoryPath);
    }

    /**
     * Loads images from the specified directory.
     * @param directoryPath - path to the directory where the images are stored
     */
    private void loadImages(String directoryPath) {
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (file.getName().contains(".png")) {
                this.imagePaths.put(Integer.parseInt(file.getName().replace(".png", "").split(" ")[2]), file.getPath());
            }
        }
    }

    /**
     * Returns the next image from the list of images.
     * Loops back to the first image if the last image was returned.
     *
     * @return - the next image from the list of images
     */
    public String getNextImage() {
        if (this.imagePaths.isEmpty()) {
            return null;
        }
        String imagePath = this.imagePaths.get(this.currentIndex);
        this.currentIndex = (this.currentIndex + 1) % this.imagePaths.size();
        return imagePath;
    }

    /**
     * Returns the next image from the list of images.
     * Returns null if the last image was returned.
     *
     * @return - the next image from the list of images
     */
    public String getNextImageWithoutLoop() {
        if (this.imagePaths.isEmpty()) {
            return null;
        }
        String imagePath = this.imagePaths.get(this.currentIndex);
        this.currentIndex = (this.currentIndex + 1) % this.imagePaths.size();
        if (this.currentIndex == 0) {
            return null;
        }

        return imagePath;
    }

    /**
     * Changes the directory to the specified directory.
     *
     * @param directoryPath - path to the different directory where the images are stored
     */
    public void changeDirectory(String directoryPath) {
        if (this.actualDirectoryPath.equals(directoryPath)) {
            return;
        }
        this.actualDirectoryPath = directoryPath;
        this.imagePaths.clear();
        this.currentIndex = 0;
        this.loadImages(directoryPath);
    }
}