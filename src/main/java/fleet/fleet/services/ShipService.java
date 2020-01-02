package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Category;
import fleet.fleet.models.Ship;
import fleet.fleet.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShipService extends CRUDService {

    @Autowired
    private ShipRepository mShipRepository;

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
            throw new ResourceNotFound("Ship not found for this id : " + ship.getShipId());
        }
    }

    @Override
    public void delete(int id) throws ResourceNotFound {
        Optional<Ship> ship = mShipRepository.findById(id);
        if (ship.isPresent()) {
            mShipRepository.delete(ship.get());
        } else {
            throw new ResourceNotFound("Ship not found for this id : " + id);
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
            throw new ResourceNotFound("Ship not found for this id :: " + id);
        }
    }
}
