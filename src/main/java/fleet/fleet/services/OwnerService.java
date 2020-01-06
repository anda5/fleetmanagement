package fleet.fleet.services;

import fleet.fleet.Utils;
import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import fleet.fleet.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OwnerService extends CRUDService {

    @Autowired
    private OwnerRepository mOwnerRepo;

    @Autowired
    private ShipService mShipService;

    @Override
    public Owner create(Object obj) {
        Owner owner = (Owner) obj;
        return mOwnerRepo.save(owner);
    }

    @Override
    public Owner update(Object object) throws ResourceNotFound {
        Owner owner = (Owner) object;
        Optional<Owner> optionalOwner = mOwnerRepo.findById(owner.getOwnerId());
        if (optionalOwner.isPresent()) {
            Owner extentOwner = optionalOwner.get();
            extentOwner.setOwnerName(owner.getOwnerName());
            extentOwner.setListShip(owner.getListShip());
            return mOwnerRepo.save(extentOwner);
        } else {
            throw new ResourceNotFound(Utils.OWNER_NOT_FOUND_EX + owner.getOwnerId());
        }
    }

    @Override
    public void delete(int id) throws ResourceNotFound {
        Optional<Owner> ownerOptional = mOwnerRepo.findById(id);
        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            List<Ship> shipList = new ArrayList<>();
            shipList.addAll(owner.getListShip());
            for (Ship ship : shipList) {
                owner.getListShip().remove(ship);
                ship.getOwnerList().remove(owner);
                mShipService.update(ship);
                update(owner);
            }
            mOwnerRepo.delete(owner);
        } else {
            throw new ResourceNotFound(Utils.OWNER_NOT_FOUND_EX + id);
        }
    }

    @Override
    public List<Object> getAll() {
        List<Owner> ownerList = mOwnerRepo.findAll();
        List<Object> objectList = new ArrayList<Object>(ownerList);
        return objectList;
    }

    @Override
    public Owner getObjBy(int id) throws ResourceNotFound {
        Optional<Owner> ownerOptional = mOwnerRepo.findById(id);
        if (ownerOptional.isPresent()) {
            return ownerOptional.get();
        } else {
            throw new ResourceNotFound(Utils.OWNER_NOT_FOUND_EX + id);
        }
    }
}
