package fleet.fleet;

import fleet.fleet.models.Category;
import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import fleet.fleet.services.CategoryService;
import fleet.fleet.services.OwnerService;
import fleet.fleet.services.ShipService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = FleetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FleetApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Autowired
    private ShipService mShipService;
    @Autowired
    private OwnerService mOwnerService;
    @Autowired
    private CategoryService mCategoryService;

    @Test
    public void testGetAllShips() {
        addShipsInDb();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "api/v1/allships",
                HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:"+response.toString());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }
    @Test
    public void testAddShip() {
        Ship ship = new Ship();
        ship.setShipName("Symphony 5");
        ship.setLmoNumber(55555);

        ResponseEntity<Ship> postResponse = restTemplate.postForEntity(getRootUrl() + "api/v1/addShip", ship, Ship.class);
        System.out.println("RESPONSE:"+postResponse.toString());
        assertNotNull(postResponse);
        assertTrue(postResponse.getStatusCode().equals(HttpStatus.OK));
    }
    @Test
    public void testDeleteShip() {
        Ship ship = new Ship();
        ship.setShipName("Ship");
        ship.setLmoNumber(313113);
        mShipService.create(ship);

        int id = ship.getShipId();
        restTemplate.delete(getRootUrl() + "api/v1/deleteShip/" + id);
        Ship ship1 = restTemplate.getForObject(getRootUrl() + "api/v1/deleteShip/" + id, Ship.class);
        assertNull(ship1.getShipName());
    }
    @Test
    public void testUpdateShip() {
        Ship ship = new Ship();
        ship.setShipName("Symphony of the seas");
        ship.setLmoNumber(24311);
        mShipService.create(ship);

        Ship updatedShip = new Ship();
        updatedShip.setShipName("Ship 2020");

        ResponseEntity<Ship> postResponse = restTemplate.postForEntity(getRootUrl() + "api/v1/updateShip/" +ship.getShipId(), updatedShip, Ship.class);
        System.out.println("RESPONSE:"+postResponse.toString());
        assertNotNull(postResponse);
        assertTrue(postResponse.getStatusCode().equals(HttpStatus.OK));
    }
    @Test
    public void testGetAllShipDetails() {
        int id = insertShipWithDetails().getmCategoryId();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "api/v1/getShipDetails/"+ id,
                HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:"+response.toString());
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }
    @Test
    public void testDeleteOwner() {
        int id = insertSeveralShipsForOwner().getOwnerId();
        restTemplate.delete(getRootUrl() + "api/v1/deleteOwner/" + id);
        Owner o = restTemplate.getForObject(getRootUrl() + "api/v1/deleteOwner/" + id, Owner.class);
        assertNull(o.getOwnerName());
    }

    @Test
    void testResourceNotFound() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "api/v1/getShipDetails/"+ 45678,
                HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:"+response.toString());
        assertFalse(response.getStatusCode().equals(HttpStatus.OK));
    }

    private Owner insertSeveralShipsForOwner(){
        Owner owner = new Owner();
        owner.setOwnerName("A");

        Ship ship = new Ship();
        ship.setShipName("Ship1");
        ship.setLmoNumber(2121);

        Ship shipSec = new Ship();
        shipSec.setShipName("Ship2");
        shipSec.setLmoNumber(222);


        ship.getOwnerList().add(owner);
        shipSec.getOwnerList().add(owner);

        owner.getListShip().add(ship);
        owner.getListShip().add(shipSec);

        mShipService.create(ship);
        mShipService.create(shipSec);
        mOwnerService.create(owner);
       return owner;

    }

    private Category insertShipWithDetails(){
        Owner owner = new Owner();
        owner.setOwnerName("A");

        Ship ship = new Ship();
        ship.setShipName("Ship1");
        ship.setLmoNumber(2121);

        ship.getOwnerList().add(owner);
        owner.getListShip().add(ship);

        Category category = new Category();
        category.setmShipType("categ1");
        category.setShip(ship);
        category.setmShipTonnage(100);

        mShipService.create(ship);
        mOwnerService.create(owner);
        mCategoryService.create(category);

        return category;
    }

    private void addShipsInDb(){

        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);

        Ship secShip = new Ship();
        secShip.setShipName("Symphony of the seas");
        secShip.setLmoNumber(313113);
        mShipService.create(secShip);
    }





}
