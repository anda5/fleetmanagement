package fleet.fleet.models;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class CategoryTest {

    @Test
    void testGettersAndSetters() {
        Category category = new Category("Curse", 212121);
        Assert.isTrue(category.getmShipType().equals("Curse"), "getShipType does not work as expected");
        Assert.isTrue(category.getmShipTonnage() ==212121, "getShipTonnage does not work as expected");
    }

    @Test
    void testShip() {
        Category category = new Category("Curse", 212121);
        Ship ship = new Ship("Ship", 2121);
        category.setShip(ship);
        Assert.isTrue(category.getShip().equals(ship),"getCategory method does not work as expected");
    }

}