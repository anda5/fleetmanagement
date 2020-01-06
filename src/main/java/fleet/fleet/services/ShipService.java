package fleet.fleet.services;

import fleet.fleet.Utils;
import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Category;
import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import fleet.fleet.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShipService extends CRUDService {

    @Autowired
    private ShipRepository mShipRepository;

    @Autowired
    private OwnerService mOwnerService;

    @Autowired
    private CategoryService mCategoryService;

    @Override
    public Ship create(Object obj) {
        Ship ship = (Ship) obj;
        return mShipRepository.save(ship);
    }

    @Override
    public Ship update(Object object) throws ResourceNotFound {
        Ship ship = (Ship) object;
        Optional<Ship> optionalShip = mShipRepository.findById(ship.getShipId());
        if (optionalShip.isPresent()) {
            Ship existentShip = optionalShip.get();
            existentShip.setShipName(ship.getShipName());
            existentShip.setLmoNumber(ship.getLmoNumber());
            existentShip.setOwnerList(ship.getOwnerList());

            return mShipRepository.save(existentShip);
        } else {
            throw new ResourceNotFound(Utils.SHIP_NOT_FOUND_EX + ship.getShipId());
        }
    }

    @Override
    public void delete(int id) throws ResourceNotFound {
        Optional<Ship> shipOptional = mShipRepository.findById(id);
        if (shipOptional.isPresent()) {
            Ship ship = shipOptional.get();
            List<Owner> ownerList = new ArrayList<>();
            ownerList.addAll(ship.getOwnerList());
            for (Owner owner: ownerList){
                ship.getOwnerList().remove(owner);
                owner.getListShip().remove(ship);
                mOwnerService.update(owner);
                update(ship);
            }
            for (Object object : mCategoryService.getAll()) {
               Category category = (Category) object;
                if (category.getShip().equals(ship)) {
                    mCategoryService.delete(category.getCategoryId());
                }
            }
            mShipRepository.delete(ship);
        } else {
            throw new ResourceNotFound(Utils.SHIP_NOT_FOUND_EX + id);
        }
    }

    @Override
    public List<Object> getAll() {
        List<Ship> shipList = mShipRepository.findAll();
        List<Object> objectList = new ArrayList<Object>(shipList);
        return objectList;
    }

    @Override
    public Ship getObjBy(int id) throws ResourceNotFound {
        Optional<Ship> ship = mShipRepository.findById(id);
        if (ship.isPresent()) {
            return ship.get();
        } else {
            throw new ResourceNotFound(Utils.SHIP_NOT_FOUND_EX + id);
        }
    }
}
