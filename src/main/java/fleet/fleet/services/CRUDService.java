package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;
import java.util.List;

/**
 * CRUDService class used for be extended in each service: CategoryService, OwnerService, ShipService. And all the
 * classes should overwrite all the CRUD methods: create, update, delete, getAll and getObjBy
 **/
public abstract class CRUDService {

    /**
     * Create object
     * @param obj Object type (types: Category, Owner, Ship)
     * @return Object type (types: Category, Owner, Ship)
     **/
    abstract Object create(Object obj);
    /**
     * Update object
     * @param  object Object (types: Category, Owner, Ship)
     * @return Object type (types: Category, Owner, Ship)
     * @throws ResourceNotFound if object is not found for update
     **/
    abstract Object update(Object object) throws ResourceNotFound;
    /**
     * Delete object
     * @param  id int
     * @throws ResourceNotFound if object is not found for delete
     **/
    abstract void delete(int id) throws ResourceNotFound;
    /**
     * Get All objects
     * @return  list of Objects
     **/
    abstract List<Object> getAll();
    /**
     * Get object by id
     * @param  id int
     * @return Object object founded (types: Category, Owner, Ship)
     * @throws ResourceNotFound if object is not found for delete
     **/
    abstract Object getObjBy(int id) throws ResourceNotFound;
}
