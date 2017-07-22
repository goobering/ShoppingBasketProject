package example.codeclan.com.shoppingbasketproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 22/07/2017.
 */

public class TestItem
{
    private Item item;

    @Before
    public void before()
    {
        item = new Item();
    }

    @Test
    public void testCreateItem()
    {
        assertEquals("Item", item.getClass().getSimpleName());
    }

    @Test
    public void testSetName()
    {
        item.setName("testItem");
        assertEquals("testItem", item.getName());
    }

    @Test
    public void testSetValue()
    {
        item.setValue(100);
        assertEquals(100, item.getValue());
    }
}
