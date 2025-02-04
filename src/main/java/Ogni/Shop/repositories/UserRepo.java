package Ogni.Shop.repositories;

import Ogni.Shop.models.UserWeb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserWeb, Long> {
}
