package Ogni.Shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProductGroup group;
    private String name;
    private String feature;
    private String mainPhotoPath;
}
