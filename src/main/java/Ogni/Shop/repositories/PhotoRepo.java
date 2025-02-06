package Ogni.Shop.repositories;

import Ogni.Shop.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {
    List<Photo> findByProductId(Long productId);
}
