package fleet.fleet.services;

import java.util.List;

public abstract class CRUDService {

    abstract Object create(Object obj);
    abstract Object update(Object object);
    abstract void delete(int id);
    abstract List<Object> getAll();
    abstract Object getObjBy(int id);
}
