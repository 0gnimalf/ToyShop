package Ogni.Shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 3000)
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductType type;
    @ManyToOne
    private Material material;
    private String size;
    private Double price;
}
