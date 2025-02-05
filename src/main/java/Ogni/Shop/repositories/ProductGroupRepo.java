package Ogni.Shop.repositories;

import Ogni.Shop.models.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepo extends JpaRepository<ProductGroup, Long> {
    boolean existsByName(String name);
}
