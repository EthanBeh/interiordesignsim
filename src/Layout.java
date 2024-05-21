import java.util.ArrayList;

public class Layout {
    private ArrayList<Item> items;
    private int thisItemIndex;

    public Layout() {
        items = new ArrayList<Item>();
    }

    public void findItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (item == items.get(i)) {
                thisItemIndex = i;
            }
        }
    }

    public void addItem(String path, int width, int height, Item.Type type) {
        Item i = new Item(path, width, height, type);
        items.add(i);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
