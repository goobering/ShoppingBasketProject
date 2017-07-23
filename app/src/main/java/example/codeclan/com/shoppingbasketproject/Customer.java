package example.codeclan.com.shoppingbasketproject;

import java.util.Observable;

/**
 * Created by user on 22/07/2017.
 */

public class Customer extends Observable
{
    private Basket basket;
    private LoyaltyCard loyaltyCard;

    public Customer()
    {
    }

    public Basket getBasket()
    {
        return basket;
    }

    public void setBasket(Basket basket)
    {
        if(this.basket == basket)
        {
            return;
        }

        if(this.basket != null)
        {
            this.basket.setCustomer(null);
        }

        this.basket = basket;

        if(this.basket != null)
        {
            this.basket.setCustomer(this);
        }
    }

    public LoyaltyCard getLoyaltyCard()
    {
        return loyaltyCard;
    }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard)
    {
        if(this.loyaltyCard != loyaltyCard)
        {
            this.loyaltyCard = loyaltyCard;
            setChanged();
            notifyObservers();
        }
    }
}
