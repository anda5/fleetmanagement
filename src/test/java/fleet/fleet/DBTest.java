package fleet.fleet;

import fleet.fleet.models.Category;
import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class DBTest {

    List<String> ownerList = new ArrayList<>();
    HashMap<String, Long> shipsList = new HashMap<String, Long>();
    HashMap<String, Long> categoryList = new HashMap<String, Long>();

    @Test
    void dbTest() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        populateDataForDb();
        createDB(session);
        session.getTransaction().commit();
        readData(session);

        session.clear();
        session.beginTransaction();
        updateDB(session);
        session.getTransaction().commit();
        readData(session);

        session.clear();
        session.beginTransaction();
        deleteDB(session);
        session.getTransaction().commit();
        readData(session);

        session.close();
    }

    private void createDB(Session session) {
        for (int i = 0; i < getOwnersList().size(); i++) {
            Owner owner = getOwnersList().get(i);
            owner.getListShip().addAll(getShips());
            session.save(owner);
        }
        for (int i = 0; i < getShips().size(); i++) {
            Ship ship = getShips().get(i);
            ship.getOwnerList().addAll(getOwnersList());
            Category category = getCategories().get(i);
            category.setShip(ship);
            session.save(ship);
            session.save(category);
        }
    }

    private void updateDB(Session session) {
        Owner owner = session.get(Owner.class, 1);
        owner.setOwnerName("Anda");
        session.update(owner);

        Ship ship = session.get(Ship.class, 1);
        ship.setShipName("Ship Name Update");
        session.update(ship);

        Category category = session.get(Category.class, 1);
        category.setmShipType("New category");
        session.update(category);
    }

    private void deleteDB(Session session) {
        Owner owner = session.get(Owner.class, 1);
        session.delete(owner);

        Category category = session.get(Category.class, 1);
        session.delete(category.getShip());
        session.delete(category);

    }


    private void readData(Session session) {
        Query queryOwner = session.createQuery("from Owner");
        List<Owner> fetchedData = queryOwner.list();
        for (Owner owner : fetchedData) {
            System.out.println(owner.toString());
        }
        Query queryCategory = session.createQuery("from Category ");
        List<Category> fetchedDataCat = queryCategory.list();
        for (Category category : fetchedDataCat) {
            System.out.println(category.toString());
        }
        Query queryShip = session.createQuery("from Ship");
        List<Ship> fetchedDataShip = queryShip.list();
        for (Ship ship : fetchedDataShip) {
            System.out.println(ship.toString());
        }
    }

    private void populateDataForDb() {
        ownerList.add("Holland America");
        ownerList.add("Captain America");
        ownerList.add("Carnival");

        shipsList.put("Symphony of the seas", 1234567l);
        shipsList.put("Eco Arctic", 4567890l);
        shipsList.put("Explorer Spirit", 13456l);

        categoryList.put("Cruise", 208081l);
        categoryList.put("Crude Oil Tanker ", 19554l);
        categoryList.put("LPG Tanker ", 57657l);
    }

    private List<Owner> getOwnersList() {
        List<Owner> owners = new ArrayList<>();
        for (String ownerName : ownerList) {
            Owner owner = new Owner(ownerName);
            owners.add(owner);
        }
        return owners;
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        for (Map.Entry mapElement : categoryList.entrySet()) {
            String categType = (String) mapElement.getKey();
            long shipTonnage = (long) mapElement.getValue();
            Category category = new Category(categType, shipTonnage);
            categories.add(category);
        }
        return categories;
    }

    private List<Ship> getShips() {
        List<Ship> shipList = new ArrayList<>();
        for (Map.Entry mapElement : shipsList.entrySet()) {
            String shipName = (String) mapElement.getKey();
            long lmoNumber = (long) mapElement.getValue();
            Ship ship = new Ship(shipName, lmoNumber);
            shipList.add(ship);
        }
        return shipList;
    }
}
