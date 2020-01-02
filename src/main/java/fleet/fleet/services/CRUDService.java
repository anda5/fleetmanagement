package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;

import java.util.List;

public abstract class CRUDService {

    abstract Object create(Object obj);
    abstract Object update(Object object) throws ResourceNotFound;
    abstract void delete(int id) throws ResourceNotFound;
    abstract List<Object> getAll();
    abstract Object getObjBy(int id) throws ResourceNotFound;
}
