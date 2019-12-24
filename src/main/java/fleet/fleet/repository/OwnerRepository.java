package fleet.fleet.repository;

import fleet.fleet.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface OwnerRepository extends JpaRepository<Owner,Integer> {

}
