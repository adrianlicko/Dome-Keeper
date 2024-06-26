package sk.uniza.fri.game;

import fri.shapesge.Image;

/**
 * Represents an image object that can be moved and changed.
 *
 * @author Adrian Licko
 * @version 1.0
 * @since 1.0
 */
public class ImageObject extends Image {
    private final String imagePath;
    private int x;
    private int y;
    private int imageWidth;
    private int imageHeight;

    /**
     * Constructor for the ImageObject class.
     *
     * @param imagePath - path to the image
     */
    public ImageObject(String imagePath) {
        super(imagePath, 0, 0);
        this.imagePath = imagePath;
    }

    /**
     * Constructor for the ImageObject class.
     *
     * @param imagePath - path to the image
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public ImageObject(String imagePath, int x, int y) {
        super(imagePath, x, y);
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for the ImageObject class.
     *
     * @param imagePath - path to the image
     * @param x - x coordinate
     * @param y - y coordinate
     * @param imageWidth - width of the image
     * @param imageHeight - height of the image
     */
    public ImageObject(String imagePath, int x, int y, int imageWidth, int imageHeight) {
        this(imagePath, x, y);
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public void moveVertical(int distance) {
        super.moveVertical(distance);
        this.addY(distance);
    }

    @Override
    public void moveHorizontal(int distance) {
        super.moveHorizontal(distance);
        this.addX(distance);
    }

    @Override
    public void changePosition(int x, int y) {
        super.changePosition(x, y);
        this.x = x;
        this.y = y;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public int getX() {
        return this.x;
    }

    public void addX(int x) {
        this.x += x;
    }

    /**
     * Returns the x coordinate of the center of the image.
     * @return - the x coordinate of the center of the image
     */
    public int getHitX() {
        return this.x + (this.imageWidth / 2);
    }

    public int getY() {
        return this.y;
    }

    public void addY(int y) {
        this.y += y;
    }

    /**
     * Returns the y coordinate of the center of the image.
     * @return - the y coordinate of the center of the image
     */
    public int getHitY() {
        return this.y + (this.imageHeight / 2);
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public int getImageHeight() {
        return this.imageHeight;
    }
}
