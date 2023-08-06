package erp.erp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {

    private UUID uuid;
    private OrderStatusEnum orderStatusEnum;
    private Customer customer;
    private ArrayList<Product> productList;




    public Order() {
        this.uuid = UUID.randomUUID();
    }

}
