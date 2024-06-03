import java.awt.*;
import java.util.ArrayList;

public class Layout {
    private ArrayList<Item> recentlyClicked;

    public Layout() {
        recentlyClicked = new ArrayList<Item>();
    }

    public void deleteTop() {
        if (recentlyClicked.size() != 0) {
            recentlyClicked.remove(recentlyClicked.size() - 1);
        }
    }

    public Item identifyItem(Point p) {
        System.out.println("whomp whomp");
        for (int i = recentlyClicked.size() - 1; i >= 0; i--) {
            if (recentlyClicked.get(i).getRect().contains(p)) {
                System.out.println("heheheh >:3");
                recentlyClicked.add(recentlyClicked.remove(i));
                return recentlyClicked.get(recentlyClicked.size() - 1);
            }
        } return null;
    }

    public void addItem(String path, int width, int height, Item.Type type) {
        Item i = new Item(path, width, height, type);
        recentlyClicked.add(i);
    }

    public ArrayList<Item> getItems() {
        return recentlyClicked;
    }
}