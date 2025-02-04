package Ogni.Shop.repositories;

import Ogni.Shop.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Long> {
}
