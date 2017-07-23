package example.codeclan.com.shoppingbasketproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 22/07/2017.
 */

public class TestBasket
{
    // All currency values use a fairly naive integer/pennies model:
    // 1 integer unit = 1 penny

    private Basket basket;
    private Customer customer;

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

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullItemThrowsException()
    {
        basket.addItem(null);
    }


    @Test
    public void testAddItemToBasket()
    {
        assertEquals(0, basket.getNumItems());
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
    public void testEmptyBasketRemovesItems()
    {
        // Add 10 identical empty items
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
    public void testGetTotalValue()
    {
        assertEquals(0, basket.getTotalValue());

        // Add 10 unique items with prices increasing at 100 penny intervals
        for(int i = 0; i < 10; i++)
        {
            Item item = new Item("testItem" + Integer.toString(i), (i + 1) * 100);
            basket.addItem(item);
        }

        assertEquals(5500, basket.getTotalValue());
    }

    @Test
    public void testEmptyBasketSetsTotalValueZero()
    {
        // Add 10 unique items with prices increasing at 100 penny intervals
        for(int i = 0; i < 10; i++)
        {
            Item item = new Item("testItem" + Integer.toString(i), (i + 1) * 100);
            basket.addItem(item);
        }

        assertEquals(5500, basket.getTotalValue());
        basket.empty();
        assertEquals(0, basket.getTotalValue());
    }

    @Test
    public void testGetCustomer()
    {
        assertEquals(customer, basket.getCustomer());
    }
}
