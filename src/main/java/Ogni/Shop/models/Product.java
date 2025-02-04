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
    @Enumerated(EnumType.STRING)
    private ProductType type;
    @ManyToOne
    private ProductGroup group;
    private String name;
}
