import java.awt.*;
import java.util.ArrayList;

public class Layout {
    private ArrayList<Item> items;
    private ArrayList<Item> recentlyClicked;
    private int thisItemIndex;

    public Layout() {
        recentlyClicked = new ArrayList<Item>();
        items = new ArrayList<Item>();
    }

    public void deleteItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (item == items.get(i)) {
                items.remove(i);
                i--;
            }
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
        items.add(i);
        recentlyClicked.add(i);
    }

    public ArrayList<Item> getItems() {
        return recentlyClicked;
    }
}
