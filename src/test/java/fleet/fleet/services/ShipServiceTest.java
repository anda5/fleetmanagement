package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShipServiceTest {

    @Autowired
    private ShipService mShipService;

    @Test
    void create() throws ResourceNotFound {
        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);
        Assert.isTrue(mShipService.getObjBy(
                ship.getShipId()).getShipName().equals(ship.getShipName()),
                "Object is not created");
    }

    @Test
    void update() throws ResourceNotFound {
        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);

        Ship updateShip = mShipService.getObjBy(ship.getShipId());
        updateShip.setShipName("Explorer Spirit");
        mShipService.update(updateShip);

        Assert.isTrue(mShipService.getObjBy(
                updateShip.getShipId()).getShipName().equals("Explorer Spirit"),
                "Object is not updated");
    }

    @Test
    void delete() throws ResourceNotFound {
        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);

        mShipService.delete(ship.getShipId());
        Assert.isTrue(!mShipService.getAll().contains(ship),
                "Object is not deleted");
    }

    @Test
    void getAll() {
        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);
        mShipService.create(ship);
        Assert.notEmpty(mShipService.getAll(),
                "Object list is empty");
    }

    @Test
    void getObjBy() throws ResourceNotFound {
        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);
        Assert.notNull(mShipService.getObjBy(ship.getShipId()),
                " Object is not returned!");
    }
}