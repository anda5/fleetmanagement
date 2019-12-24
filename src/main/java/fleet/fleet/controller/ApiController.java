package fleet.fleet.controller;


import fleet.fleet.models.Category;
import fleet.fleet.models.Ship;
import fleet.fleet.services.CategoryService;
import fleet.fleet.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ApiController {

    @Autowired
    private ShipService mShipService;
    @Autowired
    private CategoryService mCategService;
    @Autowired
    private CategoryService mOwnerService;

    @GetMapping("/allships")
    public ResponseEntity<List<Ship>> getAllShips() {
       ShipService shipService =  mShipService;
       List<Ship> shipList = (List<Ship>)(Object)shipService.getAll();
       return new ResponseEntity<List<Ship>>(shipList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/addShip")
    public ResponseEntity<Ship> addShip(@Valid @RequestBody Ship ship) {
        Ship shipCreated = mShipService.create(ship);
        return new ResponseEntity<Ship>(shipCreated, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/updateShip/{id}")
    public ResponseEntity<Ship> updateShip(@PathVariable(value = "id") int shipid,
                                           @Valid @RequestBody Ship ship) {
        Ship foundedShip = mShipService.getObjBy(shipid);
        foundedShip.setShipName(ship.getShipName());
        foundedShip.setOwnerList(ship.getOwnerList());
        foundedShip.setLmoNumber(ship.getLmoNumber());
        Ship shipCreated = mShipService.update(foundedShip);
        return new ResponseEntity<Ship>(shipCreated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("deleteShip/{id}")
    public HttpStatus deleteShip(@PathVariable("id") int id) {
        mShipService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/getShipDetails/{id}")
    public ResponseEntity<Category> getAllShipDetails(@PathVariable("id") int id) {
        Category category = (Category) mCategService.getObjBy(id);
        return new ResponseEntity<Category>(category, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteOwner/{id}")
    public HttpStatus deleteOwner(@PathVariable("id") int id) {
        mOwnerService.delete(id);
        return HttpStatus.OK;
    }
}
