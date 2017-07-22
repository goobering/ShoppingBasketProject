package example.codeclan.com.shoppingbasketproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 22/07/2017.
 */

public class TestBasket
{
    private Basket basket;
    private Customer customer;

    // All currency values use a fairly naive integer/pennies model:
    // 1 integer unit = 1 penny

    @Before
    public void before()
    {
        customer = new Customer();
        basket = new Basket();

        customer.setBasket(basket);
    }

    @Test
    public void testCreateBasket()
    {
        assertEquals("Basket", basket.getClass().getSimpleName());
    }

    @Test
    public void testAddItemToBasket()
    {
        Item item = new Item();
        basket.addItem(item);
        assertEquals(1, basket.getNumItems());
    }

    @Test
    public void testRemoveItemFromBasket()
    {
        Item item = new Item();
        basket.addItem(item);
        assertEquals(1, basket.getNumItems());
        basket.removeItem(item);
        assertEquals(0, basket.getNumItems());
    }

    @Test
    public void testEmptyBasket()
    {
        for(int i = 0; i < 10; i++)
        {
            Item item = new Item();
            basket.addItem(item);
        }

        assertEquals(10, basket.getNumItems());
        basket.empty();
        assertEquals(0, basket.getNumItems());
    }

    @Test
    public void testBuyOneGetOneFreeThreeItems()
    {
        // Add 3 identical £1 items to the basket
        for(int i = 0; i < 3; i++)
        {
            Item item = new Item("testItem", 100);
            basket.addItem(item);
        }

        assertEquals(200, basket.getDiscountedValue());
    }

    @Test
    public void testBuyOneGetOneFreeManyItems()
    {
        // Add 300000 identical £1 items to the basket
        for(int i = 0; i < 300000; i++)
        {
            Item item = new Item("testItem", 100);
            basket.addItem(item);
        }

        // Discount here reflects both the 'buy one get one free' discount and the '10% off over £20' discount
        assertEquals(180000, basket.getDiscountedValue());
    }

    @Test
    public void testTenPercentOffTwentyPoundTotal()
    {
        // Add 20 unique £1 items to the basket
        for(int i = 0; i < 20; i++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        assertEquals(1800, basket.getDiscountedValue());
    }

    @Test
    public void testLoyaltyCardDiscount()
    {
        // Add 100 unique £1 items to the basket
        for(int i = 0; i < 100; i ++)
        {
            Item item = new Item("testItem" + Integer.toString(i), 100);
            basket.addItem(item);
        }

        // Default Customer has no loyalty card
        // 10% off total > £20 has been applied
        assertEquals(9000, basket.getDiscountedValue());

        // Add loyalty card to customer and re-test
        customer.setLoyaltyCard(new LoyaltyCard());
        assertEquals(8820, basket.getDiscountedValue());
    }
}
