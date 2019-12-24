package fleet.fleet.repository;

import fleet.fleet.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
