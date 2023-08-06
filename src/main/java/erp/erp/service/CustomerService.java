package erp.erp.service;


import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.OrderEntity;
import erp.erp.database.entity.ProductEntity;
import erp.erp.database.repository.CustomerRepository;
import erp.erp.database.repository.OrderRepository;
import erp.erp.model.OrderStatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//bean
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;


    public CustomerEntity createCustomer(String name, String surname) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(name);
        customer.setSurname(surname);

        customerRepository.save(customer);
        return customer;
    }

    //finds all customers in database
    public List<CustomerEntity> getCustomerList() {
        return customerRepository.findAll();
    }
    //find customer in db by name
    public CustomerEntity getCustomerByName(String name) {
        return customerRepository.findAllByNameIgnoreCase(name);
    }

    //will be used for delete
    public CustomerEntity getCustomerByUUID(UUID uuid) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findByUuid(uuid);

        if (customerEntityOptional.isPresent()) {
            return customerEntityOptional.get();
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean deleteCustomerByUUID(UUID uuid) {
        CustomerEntity customerEntity = getCustomerByUUID(uuid);

        if (customerEntity != null) {
            //delete from db
            customerRepository.deleteCustomerEntitiesByUuid(uuid);
            return true;
        } else {
            return false;
        }
    }
    //update //newCustomerEntity requested by RequestBody in controller
    public CustomerEntity updateCustomerByUUID(UUID uuid, CustomerEntity newCustomerEntity) {
        CustomerEntity customerEntity = getCustomerByUUID(uuid);

        if (customerEntity != null) {
            customerEntity.setName(newCustomerEntity.getName());
            customerEntity.setSurname(newCustomerEntity.getSurname());

            customerRepository.save(customerEntity);
            return customerEntity;
        } else {
            return null;
        }
    }

   /* public CustomerEntity addOrderToCustomer(Long customer_id, OrderEntity orderEntity) {
        CustomerEntity customerEntity = customerRepository.findById(customer_id).orElse(null);


        if (customerEntity != null) {
            List<OrderEntity> orderList = customerEntity.getOrderList();
            orderList.add(orderEntity);
            customerEntity.setOrderList(orderList);
            orderEntity.setCustomer(customerEntity);
            return customerRepository.save(customerEntity);
        } else {
            return null;
        }
    }*/

}
