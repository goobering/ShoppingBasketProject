package example.codeclan.com.shoppingbasketproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 22/07/2017.
 */

public class TestCustomer
{
    private Customer customer;

    @Before
    public void before()
    {
        customer = new Customer();
    }

    @Test
    public void testCreateCustomer()
    {
        assertEquals("Customer", customer.getClass().getSimpleName());
    }

    @Test
    public void testAddLoyaltyCard()
    {
        LoyaltyCard loyaltyCard = new LoyaltyCard();
        customer.setLoyaltyCard(loyaltyCard);
        assertEquals(loyaltyCard, customer.getLoyaltyCard());
    }

    @Test
    public void testAddBasket()
    {
        Basket basket = new Basket();
        customer.setBasket(basket);
        assertEquals(basket, customer.getBasket());
    }
}
