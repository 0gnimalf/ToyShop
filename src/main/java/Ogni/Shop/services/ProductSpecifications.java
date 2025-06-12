package Ogni.Shop.services;

import Ogni.Shop.models.Product;
import Ogni.Shop.models.ProductGroup;
import Ogni.Shop.models.ProductType;
import org.springframework.data.jpa.domain.Specification;

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
    public static Specification<Product> hasKeyword(String keyword){
        return (root, query, cb) -> {
            if (keyword == null || keyword.isEmpty()) {
                return null;
            }
            String likeKeyword = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.toString(root.get("id")), likeKeyword),
                    cb.like(cb.lower(root.get("name")), likeKeyword),
                    cb.like(cb.lower(root.get("feature")), likeKeyword),
                    cb.like(cb.lower(root.get("group").get("name")), likeKeyword),
                    cb.like(cb.lower(root.get("group").get("description")), likeKeyword),
                    cb.like(cb.lower(root.get("group").get("type")), likeKeyword),
                    cb.like(cb.lower(root.get("group").get("material").get("name")), likeKeyword),
                    cb.like(cb.lower(root.get("group").get("size")), likeKeyword)
            );
        };
    }
}
