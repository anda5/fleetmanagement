package fleet.fleet.controller;


import fleet.fleet.Utils;
import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Category;
import fleet.fleet.models.Ship;
import fleet.fleet.services.CategoryService;
import fleet.fleet.services.OwnerService;
import fleet.fleet.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST API version 1 for Fleet Management
 **/
@RestController
@RequestMapping(Utils.API_V1)
public class ApiController {

    @Autowired
    private ShipService mShipService;
    @Autowired
    private CategoryService mCategService;
    @Autowired
    private OwnerService mOwnerService;

    /**
     * Get all ships from ship table
     *
     * @return List of Ship objects
     **/
    @GetMapping("/allships")
    public ResponseEntity<List<Ship>> getAllShips() {
        ShipService shipService = mShipService;
        List<Ship> shipList = (List<Ship>) (Object) shipService.getAll();
        return new ResponseEntity<List<Ship>>(shipList, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Add new ship
     *
     * @param ship Ship object
     * @return Ship object created
     **/
    @PostMapping("/addShip")
    public ResponseEntity<Ship> addShip(@Valid @RequestBody Ship ship) {
        Ship shipCreated = mShipService.create(ship);
        return new ResponseEntity<Ship>(shipCreated, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Ship is updated
     *
     * @param shipid (int) of ship object to update
     * @param ship  Ship object that will be updated
     * @return Ship object after update
     * @throws ResourceNotFound exception
     **/
    @PostMapping("/updateShip/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable(value = "id") int shipid,
                                           @Valid @RequestBody Ship ship) throws ResourceNotFound {
        Ship foundedShip = mShipService.getObjBy(shipid);
        foundedShip.setShipName(ship.getShipName());
        foundedShip.setOwnerList(ship.getOwnerList());
        foundedShip.setLmoNumber(ship.getLmoNumber());
        Ship shipUpdated = mShipService.update(foundedShip);
        return new ResponseEntity<Ship>(shipUpdated, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete ship
     *
     * @param id (int) of ship object to delete
     * @return HttpStatus OK if ship is deleted, EXPECTATION_FAILED instead
     * @throws ResourceNotFound exception
     **/
    @DeleteMapping("deleteShip/{id}")
    public HttpStatus deleteShip(@PathVariable("id") int id) throws ResourceNotFound {
        mShipService.delete(id);
        return mShipService.getObjBy(id) != null ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
    }

    /**
     * Get all the details that can be obtained about the ship
     *
     * @param id (int) of ship object for obtaining the details
     * @return Category object, will contain all the details that is needed
     * @throws ResourceNotFound exception
     **/
    @GetMapping("/getShipDetails/{id}")
    public ResponseEntity<Category> getAllShipDetails(@PathVariable("id") int id) throws ResourceNotFound {
        Category category = (Category) mCategService.getObjBy(id);
        mCategService.delete(category.getmCategoryId());
        return new ResponseEntity<Category>(category, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete owner
     *
     * @param id (int) of owner to be deleted
     * @return HttpStatus OK if owner is deleted, EXPECTATION_FAILED instead
     * @throws ResourceNotFound exception
     **/
    @DeleteMapping("/deleteOwner/{id}")
    public HttpStatus deleteOwner(@PathVariable("id") int id) throws ResourceNotFound {
        mOwnerService.delete(id);
        return mOwnerService.getObjBy(id) != null ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
    }
}
