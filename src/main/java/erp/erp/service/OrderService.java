package erp.erp.service;


import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.OrderEntity;
import erp.erp.database.entity.ProductEntity;
import erp.erp.database.repository.CustomerRepository;
import erp.erp.database.repository.OrderRepository;
import erp.erp.database.repository.ProductRepository;
import erp.erp.model.Order;
import erp.erp.model.OrderStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//bean
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;


    //order must be created with customer. since order belongs to customer.
    public OrderEntity createOrder(OrderEntity order, Long customer_id) {
        CustomerEntity customerEntity = customerRepository.findById(customer_id).orElse(null);
        OrderEntity orderEntity = order;
        //default
        orderEntity.setOrderStatusEnum(OrderStatusEnum.PENDING_APPROVAL);
        orderEntity.setCustomer(customerEntity);
        orderRepository.save(order);
        //update orderlist of customer
        customerEntity.getOrderList().add(order);
        return order;
    }

    //finds all customers in database
    public List<OrderEntity> getOrderList(){
        return orderRepository.findAll();
    }
    //add product to order by specialized product_id.
    public OrderEntity addProductToOrder(Long order_id,  Long product_id){
        List<ProductEntity> productList = null;
        OrderEntity orderEntity = orderRepository.findById(order_id).get();
        ProductEntity productEntity = productRepository.findById(product_id).get();
        //productList in orderentity
        productList = orderEntity.getProductList();
        //if there is stock order can be created for product.
        if(productEntity.getStockAmount()>0){
            productList.add(productEntity);
            //decrease stock amount
            productEntity.setStockAmount(productEntity.getStockAmount()-1);
            orderEntity.setProductList(productList);
        }else {
            return null;
        }
        return orderRepository.save(orderEntity);

    }


    //will be used for delete
    public OrderEntity getOrderByUUID(UUID uuid){
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByUuid(uuid);

        if(orderEntityOptional.isPresent()){
            return  orderEntityOptional.get();
        }else {
            return null;
        }
    }

   @Transactional
    public  Boolean deleteOrderByUUID(UUID uuid){
        OrderEntity orderEntity = getOrderByUUID(uuid);

        if(orderEntity!=null){
            orderRepository.deleteOrderEntitiesByUuid(uuid);
            return true;
        }else {
            return  false;
        }
    }
    //orderentity is updated with productEntity and orderEntity since oneTomany and many to many relations are established
    public  OrderEntity updateOrderByUUID(UUID uuid, OrderEntity newOrderEntity, long customer_id, long product_id){
        OrderEntity orderEntity = getOrderByUUID(uuid);
        CustomerEntity customer = customerRepository.findById(customer_id).get();
        ProductEntity product = productRepository.findById(product_id).get();

        if(orderEntity!=null){
            orderEntity.setOrderStatusEnum(orderEntity.getOrderStatusEnum());
            orderEntity.setCustomer(customer);
            //stock must be checked before adding
            if(product.getStockAmount()>0){
                orderEntity.getProductList().add(product);
                product.setStockAmount(product.getStockAmount()-1);
            }
            orderRepository.save(orderEntity);
            return orderEntity;
        }else{
            return null;
        }
    }




}
