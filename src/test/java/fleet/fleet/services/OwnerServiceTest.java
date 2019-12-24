package fleet.fleet.services;

import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OwnerServiceTest {


    @Autowired
    private OwnerService mOwnerService;

    @Test
    void create() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");
        mOwnerService.create(owner);
        Assert.isTrue(mOwnerService.getObjBy(
                owner.getOwnerId()).getOwnerName().equals(owner.getOwnerName()),
                "Object is not created");
    }

    @Test
    void update() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");
        mOwnerService.create(owner);

        Owner updatedOwner =  mOwnerService.getObjBy(owner.getOwnerId());
        updatedOwner.setOwnerName("Maria");

        mOwnerService.update(updatedOwner);

        Assert.isTrue(mOwnerService.getObjBy(
                owner.getOwnerId()).getOwnerName().equals("Maria"),
                "Object is not updated");
    }

    @Test
    void delete() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");
        mOwnerService.create(owner);

        mOwnerService.delete(owner.getOwnerId());
        Assert.isTrue(!mOwnerService.getAll().contains(owner),
                "Object is not deleted");
    }

    @Test
    void getAll() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");
        mOwnerService.create(owner);
        mOwnerService.create(owner);
        Assert.notEmpty(mOwnerService.getAll(),
                "Object list is empty");
    }

    @Test
    void getObjBy() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");
        mOwnerService.create(owner);
        Assert.notNull(mOwnerService.getObjBy(owner.getOwnerId()),
                " Object is not returned!");
    }

}