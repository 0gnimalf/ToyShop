package Ogni.Shop.services;

import Ogni.Shop.models.Product;
import Ogni.Shop.models.UserWeb;
import Ogni.Shop.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
//    public Page<Product> getPage(String keyword, Pageable pageable) {
//        Specification<Flight> specification = Specification
//                .where(FlightSpecifications.hasKeyword(keyword))
//                .and(FlightSpecifications.inDateRange(startDate, endDate));
//
//        return repo.findAll(specification, pageable);}
    public Page<Product> findAll(Pageable pageable) {return productRepo.findAll(pageable);}
}
