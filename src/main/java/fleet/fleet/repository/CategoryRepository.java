package fleet.fleet.repository;

import fleet.fleet.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
