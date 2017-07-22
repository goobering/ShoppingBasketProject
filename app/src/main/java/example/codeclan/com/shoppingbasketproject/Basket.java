package example.codeclan.com.shoppingbasketproject;

import java.util.ArrayList;

/**
 * Created by user on 22/07/2017.
 */

public class Basket
{
    private ArrayList<Item> items;

    public Basket()
    {
        items = new ArrayList<Item>();
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public int getNumItems()
    {
        return items.size();
    }

    public boolean removeItem(Item item)
    {
        return items.remove(item);
    }

    public void empty()
    {
        items.clear();
    }

    public int getDiscountedValue()
    {
        return -1;
    }
}
