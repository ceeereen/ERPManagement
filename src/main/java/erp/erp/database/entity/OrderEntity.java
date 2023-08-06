package erp.erp.database.entity;


import erp.erp.model.OrderStatusEnum;
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

public class OrderEntity extends BaseEntity {

    @Column
    private OrderStatusEnum orderStatusEnum;

    //orderlist in CustomerEntity
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    //orderlist in ProductEntity
    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> productList;

}
