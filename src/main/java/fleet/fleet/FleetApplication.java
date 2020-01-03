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
public class FleetApplication{

    public static void main(String[] args) {
        SpringApplication.run(FleetApplication.class, args);
    }
}
