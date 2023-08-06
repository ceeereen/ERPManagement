package erp.erp.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import erp.erp.model.Customer;
import erp.erp.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "product_uuid"
        )
)
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "product_id"
        )
)

//person entity tablosunu oluştur.
@Data
@EqualsAndHashCode(callSuper = true)

public class ProductEntity extends BaseEntity {

    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private int stockAmount;
    @Column
    Boolean isKdvApplicable;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<OrderEntity> orderList;

}
