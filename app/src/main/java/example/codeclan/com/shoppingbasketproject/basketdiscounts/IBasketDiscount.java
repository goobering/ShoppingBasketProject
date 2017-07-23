package example.codeclan.com.shoppingbasketproject.basketdiscounts;

import example.codeclan.com.shoppingbasketproject.Basket;

/**
 * Created by user on 22/07/2017.
 */

public interface IBasketDiscount
{
    int getValue(Basket basket, int currentValue);
}
