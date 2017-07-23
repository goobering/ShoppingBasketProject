package example.codeclan.com.shoppingbasketproject;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by user on 22/07/2017.
 */

public class Basket extends Observable
{
    private Customer customer;
    private ArrayList<Item> items = new ArrayList<Item>();

    public Basket()
    {
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        if(this.customer == customer)
        {
            return;
        }

        this.customer = customer;
        setChanged();
        notifyObservers();
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public void addItem(Item item)
    {
        if(item == null)
        {
            throw new IllegalArgumentException("Cannot add a null Item to Basket.");
        }

        items.add(item);
        setChanged();
        notifyObservers();
    }

    public boolean removeItem(Item item)
    {
        boolean changeMade = items.remove(item);
        if(changeMade)
        {
            setChanged();
            notifyObservers();
        }
        return changeMade;
    }

    public int getNumItems()
    {
        return items.size();
    }

    public void empty()
    {
        items.clear();
        setChanged();
        notifyObservers();
    }

    public int getTotalValue()
    {
        int sum = 0;

        for(Item item : items)
        {
            sum += item.getValue();
        }

        return sum;
    }
}
