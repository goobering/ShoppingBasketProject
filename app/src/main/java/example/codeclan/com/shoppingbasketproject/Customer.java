package example.codeclan.com.shoppingbasketproject;

/**
 * Created by user on 22/07/2017.
 */

public class Customer
{
    private Basket basket;
    private LoyaltyCard loyaltyCard;

    public Customer()
    {
        basket = null;
        loyaltyCard = null;
    }

    public Basket getBasket()
    {
        return basket;
    }

    public void setBasket(Basket basket)
    {
        this.basket = basket;
    }

    public LoyaltyCard getLoyaltyCard()
    {
        return loyaltyCard;
    }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard)
    {
        this.loyaltyCard = loyaltyCard;
    }
}
