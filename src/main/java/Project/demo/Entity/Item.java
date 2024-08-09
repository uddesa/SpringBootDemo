package Project.demo.Entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class Item {
    @Id
    private Long id;
    @Min(value=0, message="quantity has to be greater than or equal 0")
    @Column(name = "quantity")
    private Long quantity;
    @Min(value=0, message="items bought has to be equal to 0")
    @Column(name = "bought")
    private Long bought;
    @NotBlank(message="Product name can't be blank.")
    @Column(name = "product_name", unique = true, nullable = false, length = Integer.MAX_VALUE)
    private String productName;
    @DecimalMin(value="0.01", message="minimum price has to be 0.01")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;
    public Item() {
        quantity = null;
        bought = null;
        productName = null;
        price = null;
        id = null;
    }
    public Item(Long id, Long quantity, String productName,BigDecimal price) {
        this.id = id;
        this.quantity = quantity;
        this.bought = 0L;
        this.productName = productName;
        this.price = price;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBought(Long bought) {
        this.bought = bought;
    }

    public Long getBought() {
        return bought;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
