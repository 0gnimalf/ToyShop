package Ogni.Shop.services;

import Ogni.Shop.models.Product;
import Ogni.Shop.models.ProductGroup;
import Ogni.Shop.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
//        return repo.getAll(specification, pageable);}
    public Page<Product> getAll(Pageable pageable) {return productRepo.findAll(pageable);}
    public Product getById(Long id) {return productRepo.findById(id).get();}
    public List<Product> getByGroup(ProductGroup group){
        Specification<Product> spec = Specification
                .where(ProductSpecifications.inGroup(group));
        return productRepo.findAll(spec);
    }
}
