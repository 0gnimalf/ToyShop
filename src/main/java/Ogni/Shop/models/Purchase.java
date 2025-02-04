package Ogni.Shop.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserWeb user;
    @ManyToMany
    private List<Product> products;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime time;
    private Double totalPrice;
    private String comment;
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

}
