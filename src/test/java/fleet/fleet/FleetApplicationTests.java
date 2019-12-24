package fleet.fleet;

import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import fleet.fleet.services.ShipService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
    private ShipService mOwnerService;
    @Test
    public void testGetAllShips() {
        Ship ship = new Ship();
        ship.setShipName("Eco Arctic");
        ship.setLmoNumber(12121);
        mShipService.create(ship);

        Ship secShip = new Ship();
        secShip.setShipName("Symphony of the seas");
        secShip.setLmoNumber(313113);
        mShipService.create(secShip);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "api/v1/allships",
                HttpMethod.GET, entity, String.class);
        System.out.println("RESPONSE:"+response.toString());
        assertNotNull(response.getBody());
    }
    @Test
    public void testAddShip() {
        Owner owner = new Owner();
        owner.setOwnerName("Anda");

        Ship ship = new Ship();
        ship.setShipName("Symphony of the seas");
        ship.setLmoNumber(313113);

        ship.getOwnerList().add(owner);

        ResponseEntity<Ship> postResponse = restTemplate.postForEntity(getRootUrl() + "api/v1/addShip", ship, Ship.class);
        System.out.println("RESPONSE:"+postResponse.toString());
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
    @Test
    public void testDeleteShip() {
        Ship ship = new Ship();
        ship.setShipName("Ship");
        ship.setLmoNumber(313113);
        mShipService.create(ship);

        int id = ship.getShipId();
        Ship ship1 = restTemplate.getForObject(getRootUrl() + "api/v1/deleteShip/" + id, Ship.class);
        assertNotNull(ship1);
        restTemplate.delete(getRootUrl() + "api/v1/deleteShip/" + id);
        try {
            ship1 = restTemplate.getForObject(getRootUrl() + "api/v1/deleteShip/" + id, Ship.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
    
}
