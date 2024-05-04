package sk.uniza.fri;

import fri.shapesge.Image;

public class ImageObject extends Image {
    private int x;
    private int y;

    public ImageObject(String imagePath) {
        super(imagePath);
    }

    public ImageObject(String imagePath, int x, int y) {
        super(imagePath, x, y);
        this.x = x;
        this.y = y;
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

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void addX(int x) {
        this.x += x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addY(int y) {
        this.y += y;
    }
}
