package erp.erp.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Product {

    private UUID uuid;
    private String name;
    private int stockAmount;
    private BigDecimal price;
    private ArrayList<Order> orderList;



    public Product(){this.uuid = UUID.randomUUID();}

    /*public ArrayList<Long> getIDofOrderList(){
        ArrayList idList = new ArrayList<>();
        for(Order order: this.orderList){
            idList.add(order.getOrderId());
        }
        return idList;

    }*/
}
