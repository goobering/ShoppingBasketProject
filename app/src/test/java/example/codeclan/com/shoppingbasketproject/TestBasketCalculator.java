package example.codeclan.com.shoppingbasketproject;

import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.shoppingbasketproject.basketdiscounts.LoyaltyCardDiscount;
import example.codeclan.com.shoppingbasketproject.basketdiscounts.TenPercentOnTwentyPoundsDiscount;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 22/07/2017.
 */

public class TestBasketCalculator
{
    // All currency values use a fairly naive integer/pennies model:
    // 1 integer unit = 1 penny

    private BasketCalculator basketCalculator;
    private Basket basket;
    private Customer customer;

    @Before
    public void before()
    {
        customer = new Customer();
        basket = new Basket();
        customer.setBasket(basket);
        basketCalculator = new BasketCalculator(basket);

        // This is less than ideal, but difficult to work-around while maintaining
        // de-coupled Customer/BasketCalculator.
        customer.addObserver(basketCalculator);
    }

    @Test
    public void testAddBasketItemChangesCalculatedTotal()
    {
        assertEquals(0, basketCalculator.getCalculatedBasketValue());
        basket.addItem(new Item("testItem", 10));
        assertEquals(10, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testRemoveBasketItemChangesCalculatedTotal()
    {
        Item testItem = new Item("testItem", 10);
        basket.addItem(testItem);
        assertEquals(10, basketCalculator.getCalculatedBasketValue());
        basket.removeItem(testItem);
        assertEquals(0, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testEmptyBasketValueToZero()
    {
        // Add 10 unique £1 items
        for(int i = 0; i < 10; i++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(1000, basketCalculator.getCalculatedBasketValue());
        basket.empty();
        assertEquals(0, basketCalculator.getCalculatedBasketValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullDiscountItemThrowsException()
    {
        basketCalculator.addDiscountedItem(null);
    }

    @Test
    public void testBuyOneGetOneFreeThreeItems()
    {
        Item item = new Item("testItem", 100);

        // Add 3 identical £1 items to the basket
        for(int i = 0; i < 3; i++)
        {
            basket.addItem(item);
        }

        basketCalculator.addDiscountedItem(item);
        assertEquals(200, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testBuyOneGetOneFreeFourItems()
    {
        Item item = new Item("testItem", 100);

        // Add 3 identical £1 items to the basket
        for(int i = 0; i < 4; i++)
        {
            basket.addItem(item);
        }

        basketCalculator.addDiscountedItem(item);
        assertEquals(200, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testBuyOneGetOneFreeManyItemsEven()
    {
        Item item = new Item("testItem", 100);

        // Add 30000 identical £1 items to the basket
        for(int i = 0; i < 30000; i++)
        {
            basket.addItem(item);
        }

        basketCalculator.addDiscountedItem(item);
        assertEquals(1500000, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testBuyOneGetOneFreeManyItemsOdd()
    {
        Item item = new Item("testItem", 100);

        // Add 30001 identical £1 items to the basket
        for(int i = 0; i < 30001; i++)
        {
            basket.addItem(item);
        }

        basketCalculator.addDiscountedItem(item);
        assertEquals(1500100, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testRemoveItemDiscountChangesCalculatedValue()
    {
        Item item = new Item("testItem", 100);

        // Add 3 identical £1 items to the basket
        for(int i = 0; i < 3; i++)
        {
            basket.addItem(item);
        }

        basketCalculator.addDiscountedItem(item);
        assertEquals(200, basketCalculator.getCalculatedBasketValue());
        basketCalculator.removeDiscountedItem(item);
        assertEquals(300, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testAddTenPercentOffTwentyPoundTotalDiscount()
    {
        basketCalculator.addBasketDiscount(new TenPercentOnTwentyPoundsDiscount());

        // Add 21 unique £1 items to the basket
        for(int i = 0; i < 21; i++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(1890, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testRemoveTenPercentOffTwentyPoundTotalDiscount()
    {
        TenPercentOnTwentyPoundsDiscount tenPercent = new TenPercentOnTwentyPoundsDiscount();
        basketCalculator.addBasketDiscount(tenPercent);

        // Add 21 unique £1 items to the basket
        for(int i = 0; i < 21; i++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(1890, basketCalculator.getCalculatedBasketValue());

        basketCalculator.removeBasketDiscount(tenPercent);
        assertEquals(2100, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testAddLoyaltyCardDiscount()
    {
        customer.setLoyaltyCard(new LoyaltyCard());
        basketCalculator.addBasketDiscount(new LoyaltyCardDiscount());

        // Add 100 unique £1 items to the basket
        for(int i = 0; i < 100; i ++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(9800, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testRemoveLoyaltyCardDiscount()
    {
        customer.setLoyaltyCard(new LoyaltyCard());
        LoyaltyCardDiscount loyaltyDiscount = new LoyaltyCardDiscount();
        basketCalculator.addBasketDiscount(loyaltyDiscount);

        // Add 100 unique £1 items to the basket
        for(int i = 0; i < 100; i ++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(9800, basketCalculator.getCalculatedBasketValue());

        basketCalculator.removeBasketDiscount(loyaltyDiscount);
        assertEquals(10000, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testItemAndLoyaltyDiscount()
    {
        basketCalculator.addBasketDiscount(new LoyaltyCardDiscount());

        // Add 100 unique £1 items to the basket
        for(int i = 0; i < 100; i ++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        //Add duplicate 10 items to the basket and the discounted/BOGOF list
        for(int i = 0; i < 10; i++)
        {
            Item duplicateItem = basket.getItems().get(i);
            basket.addItem(duplicateItem);
            basketCalculator.addDiscountedItem(duplicateItem);
        }

        // Add loyalty card to customer
        customer.setLoyaltyCard(new LoyaltyCard());

        assertEquals(9800, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testItemAndTenPercentDiscount()
    {
        basketCalculator.addBasketDiscount(new TenPercentOnTwentyPoundsDiscount());

        // Add 100 unique £1 items to the basket
        for(int i = 0; i < 100; i ++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        //Add duplicate 10 items to the basket and the discounted/BOGOF list
        for(int i = 0; i < 10; i++)
        {
            Item duplicateItem = basket.getItems().get(i);
            basket.addItem(duplicateItem);
            basketCalculator.addDiscountedItem(duplicateItem);
        }

        assertEquals(110, basket.getNumItems());
        assertEquals(9000, basketCalculator.getCalculatedBasketValue());
    }

    @Test
    public void testLoyaltyCardRemoveCustomer()
    {
        customer.setLoyaltyCard(new LoyaltyCard());
        basketCalculator.addBasketDiscount(new LoyaltyCardDiscount());

        // Add 100 unique £1 items to the basket
        for(int i = 0; i < 100; i ++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(9800, basketCalculator.getCalculatedBasketValue());

        basket.setCustomer(null);
        assertEquals(10000, basketCalculator.getCalculatedBasketValue());
    }
}
