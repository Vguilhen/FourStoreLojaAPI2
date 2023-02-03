package com.example.demo.models;

import com.example.demo.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String sku;
    @NonNull
    private int quantity;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
    @Enumerated(EnumType.STRING)
    private ColorEnum color;
    @Enumerated(EnumType.STRING)
    private DepartmentEnum department;
    @Enumerated(EnumType.STRING)
    private SizeEnum size;
    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    public Product(Long id, String sku, String description, int quantity, Double price) {
        super();
        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
        this.description = description;
        this.price = price;
        parseSku();
    }
    public void parseSku() {
        this.category = CategoryEnum.getCategoryEnum(sku.substring(0, 2));
        this.color = ColorEnum.getColorEnum(sku.substring(2, 4));
        this.department = DepartmentEnum.getDepartmentEnum(sku.substring(4, 7));
        this.size = SizeEnum.getSizeEnum(Integer.parseInt(sku.substring(7, 9)));
        this.type = TypeEnum.getTypeEnum(sku.substring(9, 12));
    }
}
