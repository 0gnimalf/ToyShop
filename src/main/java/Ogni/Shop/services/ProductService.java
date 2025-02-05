package Ogni.Shop.services;

import Ogni.Shop.models.UserWeb;
import Ogni.Shop.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
//    public List<UserWeb> get5Random(){
//        return productRepo.
//    }
}
