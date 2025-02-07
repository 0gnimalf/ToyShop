package Ogni.Shop.services;

import Ogni.Shop.models.Product;
import Ogni.Shop.models.ProductGroup;
import Ogni.Shop.models.ProductType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public final class ProductSpecifications {
    private ProductSpecifications(){}

    public static Specification<Product> inGroup(ProductGroup group){
        return (root, query, cb) -> {
            if (group == null) {
                return null;
            }
            return cb.equal(root.get("group"), group);
        };
    }
    public static Specification<Product> belongToType(ProductType type){
        return (root, query, cb) -> {
            if (type == null) {
                return null;
            }
            return cb.equal(root.get("group").get("type"), type);
        };
    }
}
