package example.codeclan.com.shoppingbasketproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import example.codeclan.com.shoppingbasketproject.basketdiscounts.IBasketDiscount;
import example.codeclan.com.shoppingbasketproject.itemdiscounts.BuyXGetYFree;

/**
 * Created by user on 22/07/2017.
 */

public class BasketCalculator implements Observer
{
    private Basket basket;
    private ArrayList<IBasketDiscount> basketDiscounts= new ArrayList<IBasketDiscount>();
    private Set<Item> discountedItems = new HashSet<Item>();
    private int calculatedBasketValue;

    public BasketCalculator(Basket basket)
    {
        this.basket = basket;
        basket.addObserver(this);
        calculatedBasketValue = basket.getTotalValue();
    }

    // Caution - discounts will be processed in the order they are added to this list
    public void addBasketDiscount(IBasketDiscount basketDiscount)
    {
        basketDiscounts.add(basketDiscount);
        updateCalculatedBasketValue();
    }

    public void removeBasketDiscount(IBasketDiscount basketDiscount)
    {
        boolean madeChange = basketDiscounts.remove(basketDiscount);
        if(madeChange)
        {
            updateCalculatedBasketValue();
        }
    }

    public void addDiscountedItem(Item item)
    {
        if(item == null)
        {
            throw new IllegalArgumentException("Discounted item cannot be null.");
        }
        this.discountedItems.add(item);
        updateCalculatedBasketValue();
    }

    public void removeDiscountedItem(Item item)
    {
        if(this.discountedItems.remove(item))
        {
            updateCalculatedBasketValue();
        }
    }

    private void updateCalculatedBasketValue()
    {
        calculatedBasketValue = basket.getTotalValue();

        for(Item item : discountedItems)
        {
            calculatedBasketValue -= BuyXGetYFree.getValue(basket, item, 1, 1);
        }

        for(IBasketDiscount basketDiscount: basketDiscounts)
        {
            calculatedBasketValue -= basketDiscount.getValue(basket, calculatedBasketValue);
        }
    }

    public int getCalculatedBasketValue()
    {
        return calculatedBasketValue;
    }

    @Override
    public void update(Observable observable, Object o)
    {
        updateCalculatedBasketValue();
    }
}
