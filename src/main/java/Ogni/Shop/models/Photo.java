package Ogni.Shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String path;
}
