package sk.uniza.fri;

import java.io.File;
import java.util.HashMap;

public class ImageLoader {
    private String actualDirectoryPath;
    private HashMap<Integer, String> imagePaths;
    private int currentIndex;

    public ImageLoader(String directoryPath) {
        this.actualDirectoryPath = directoryPath;
        this.imagePaths = new HashMap<>();
        this.currentIndex = 0;
        this.loadImages(directoryPath);
    }

    private void loadImages(String directoryPath) {
        File directory = new File(directoryPath);
        for (File file : directory.listFiles()) {
            if (file.getName().contains(".png")) {
                this.imagePaths.put(Integer.parseInt(file.getName().replace(".png", "").split(" ")[2]), file.getPath());
            }
        }
    }

    public String getNextImage() {
        if (this.imagePaths.isEmpty()) {
            return null;
        }
        String imagePath = this.imagePaths.get(this.currentIndex);
        this.currentIndex = (this.currentIndex + 1) % this.imagePaths.size();
        return imagePath;
    }

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