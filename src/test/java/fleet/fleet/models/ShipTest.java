package fleet.fleet.models;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class ShipTest {

    @Test
    void testShipId() {
        Ship ship = new Ship();
        ship.setShipId(2);
        Assert.isTrue(ship.getShipId()==2, "getShipId method is not working");
    }

    @Test
    void testShipName() {
        Ship ship = new Ship();
        ship.setShipName("ShipName");
        Assert.isTrue(ship.getShipName().equals("ShipName"), "getShipName method is not working");
    }

    @Test
    void testLmoNumber() {
        Ship ship = new Ship();
        ship.setLmoNumber(2121121121);
        Assert.isTrue(ship.getLmoNumber() == 2121121121, "getShipLmoNumber method is not working");
    }

}