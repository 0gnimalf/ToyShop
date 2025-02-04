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
    private String description;
    @ManyToOne
    private Material material;
    private String size;
    private Double price;
}
