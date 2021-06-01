package bankSafe;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BankVaultTest {

    private BankVault bankVault;
    private Item item;

    @Before
    public void setUp() {
        this.item = new Item("Pet", "9908");
        this.bankVault = new BankVault();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithNotExistCell() throws OperationNotSupportedException {
        this.bankVault.addItem(null, this.item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemWithAlreadySaveItemAtThisCell() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", this.item);
        this.bankVault.addItem("A1", this.item);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAddItemWithAlreadySaveItemInCollection() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", this.item);
        this.bankVault.addItem("A2", this.item);
    }
    @Test
    public void testAddItemCorrectly() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", this.item);
        Item item = this.bankVault.getVaultCells().get("A1");

        assertEquals(item, this.item);
    }

    @Test
    public void removeItemSuccessfully() throws OperationNotSupportedException {
        bankVault.addItem("A1",item);
        String message = bankVault.removeItem("A1", item);
        Map<String, Item> vaultCells = bankVault.getVaultCells();
        assertNull(vaultCells.get("A1"));
        assertEquals("Remove item:" + item.getItemId() + " successfully!",message);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemWithNotExistingCell() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", item);
        this.bankVault.removeItem("a1",item);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemAttCellWithDifferentItem() throws OperationNotSupportedException {

        Item item1 = new Item("test", "test");
        this.bankVault.addItem("A1", item);
        this.bankVault.removeItem("A1",item1);
    }
    @Test
    public void getVaultCellsCorrectly() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", item);
        Map<String, Item> vaultCells = this.bankVault.getVaultCells();

        Item item = vaultCells.get("A1");

        assertEquals(item, this.item);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void getVaultCellsCorrectlyAndModifiableValues() throws OperationNotSupportedException {
        this.bankVault.addItem("A1", item);
        Map<String, Item> vaultCells = this.bankVault.getVaultCells();

        vaultCells.put("a2", this.item);
    }
}