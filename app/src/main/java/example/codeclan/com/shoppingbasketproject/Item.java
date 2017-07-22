package example.codeclan.com.shoppingbasketproject;

/**
 * Created by user on 22/07/2017.
 */

public class Item
{
    private String name;
    private int value;

    public Item()
    {
        name = "";
        value = 0;
    }

    public Item(String name, int value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
