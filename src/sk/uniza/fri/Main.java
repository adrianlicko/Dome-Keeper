package sk.uniza.fri;

public class Main {
    public static void main(String[] args) {
        Game.getInstance();
        var manager = Game.getInstance().getManager();
        manager.manageObject(Game.getInstance());
    }
}
