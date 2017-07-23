package example.codeclan.com.shoppingbasketproject.basketdiscounts;

import example.codeclan.com.shoppingbasketproject.Basket;
import example.codeclan.com.shoppingbasketproject.basketdiscounts.IBasketDiscount;

/**
 * Created by user on 22/07/2017.
 */

public class TenPercentOnTwentyPoundsDiscount implements IBasketDiscount
{
    @Override
    public int getValue(Basket basket, int currentValue)
    {
        if(currentValue > 2000)
        {
            return (int)(currentValue * 0.1);
        }
        else
        {
            return 0;
        }
    }
}
