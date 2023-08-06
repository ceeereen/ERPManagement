package erp.erp.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import erp.erp.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "customer_uuid"
        )
)
@AttributeOverride(
        name = "id",
        column = @Column(
                name = "customer_id"
        )
)
//person entity tablosunu oluştur.
@Data
public class CustomerEntity extends BaseEntity {

    @Column
    private String name;
    @Column
    private String surname;

    @JsonIgnore //in order to prevent infinite loop
    //customer is in OrderEntity
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orderList;


}
