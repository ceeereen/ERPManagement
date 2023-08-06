package erp.erp.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import erp.erp.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
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
public class InvoiceEntity extends BaseEntity {

    @Column
    private List<String> nameList;
    @Column
    private int numberOfProduct;
    @Column
    private BigDecimal kdvPrice;
    @Column
    private BigDecimal normalPrice;
    @Column
    private BigDecimal kdvTotalPrice;
    @Column
    private BigDecimal normalTotalPrice;





}
