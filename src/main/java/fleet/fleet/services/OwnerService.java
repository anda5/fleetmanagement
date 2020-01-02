package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Category;
import fleet.fleet.models.Owner;
import fleet.fleet.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OwnerService extends CRUDService {

    @Autowired
    OwnerRepository mOwnerRepo;

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
            throw new ResourceNotFound("Owner not found for this id : " + owner.getOwnerId());
        }
    }

    @Override
    public void delete(int id) throws ResourceNotFound {
        Optional<Owner> ownerOptional = mOwnerRepo.findById(id);
        if (ownerOptional.isPresent()) {
            mOwnerRepo.delete(ownerOptional.get());
        } else {
            throw new ResourceNotFound("Owner not found for this id : " + id);
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
            throw new ResourceNotFound("Owner not found for this id : " + id);
        }
    }
}
