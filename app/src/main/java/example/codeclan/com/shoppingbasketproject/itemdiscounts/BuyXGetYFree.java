package example.codeclan.com.shoppingbasketproject.itemdiscounts;

import java.util.Collections;

import example.codeclan.com.shoppingbasketproject.Basket;
import example.codeclan.com.shoppingbasketproject.Item;

/**
 * Created by user on 22/07/2017.
 */

public class BuyXGetYFree
{
    public static int getValue(Basket basket, Item item, int numBought, int numFree)
    {
        if(!basket.getItems().contains(item))
        {
            return 0;
        }

        int numItems = Collections.frequency(basket.getItems(), item);
        return (int)(item.getValue() * (numItems/(numBought + numFree)) * numFree);
    }
}
