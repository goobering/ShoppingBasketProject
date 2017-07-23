package example.codeclan.com.shoppingbasketproject.basketdiscounts;

import example.codeclan.com.shoppingbasketproject.Basket;
import example.codeclan.com.shoppingbasketproject.basketdiscounts.IBasketDiscount;

/**
 * Created by user on 22/07/2017.
 */

public class LoyaltyCardDiscount implements IBasketDiscount
{
    @Override
    public int getValue(Basket basket, int currentValue)
    {
        if(basket.getCustomer() == null || basket.getCustomer().getLoyaltyCard() == null)
        {
            return 0;
        }
        else
        {
            return (int)(currentValue * 0.02);
        }
    }
}
