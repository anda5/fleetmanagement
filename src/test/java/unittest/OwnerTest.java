package unittest;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;


class OwnerTest {

    @Test
    void tetOwnerId() {
        Owner owner = new Owner();
        owner.setOwnerId(1);
        Assert.isTrue(owner.getOwnerId() == 1, "getOwnerId method is not working");
    }

    @Test
    void testOwnerName() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");
        Assert.isTrue(owner.getOwnerName().equals("Anda"), "getOwnerName method is not working");
    }

}