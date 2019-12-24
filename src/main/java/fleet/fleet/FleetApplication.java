package fleet.fleet;


import fleet.fleet.controller.ApiController;
import fleet.fleet.models.Owner;
import fleet.fleet.models.Ship;
import fleet.fleet.repository.OwnerRepository;
import fleet.fleet.services.CRUDService;
import fleet.fleet.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackageClasses = ApiController.class)
@ComponentScan(value = "fleet.fleet")
public class FleetApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FleetApplication.class, args);
    }

    @Autowired
    OwnerService repository;
    @Override
    public void run(String...args) throws Exception {



        // Create an employee
        Owner employee = new Owner();
        employee.setOwnerName("Anda");

        // Create project1
        Ship project = new Ship();
        project.setShipName("Employee Management System");

        Ship project1 = new Ship();
        project.setShipName("Employee Management System");

        Ship projec = new Ship();
        projec.setShipName("pl");

        List<Ship> shipList =employee.getListShip();
        shipList.add(project);
        shipList.add(project1);
        shipList.add(projec);
        employee.setListShip(shipList);

        project.getOwnerList().add(employee);
        project1.getOwnerList().add(employee);




        repository.create(employee);

        repository.delete(1);
    }
}
