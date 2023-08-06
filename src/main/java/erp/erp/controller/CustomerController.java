package erp.erp.controller;

import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.OrderEntity;
import erp.erp.model.Customer;
import erp.erp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //in order to list all customers.
    @GetMapping("customer")
    public ResponseEntity<List<CustomerEntity>> getCustomerList() {

        return new ResponseEntity<>(customerService.getCustomerList(), HttpStatus.OK);
    }

    //get customer oby name
    @GetMapping("customer/{name}")
    public ResponseEntity<CustomerEntity> getCustomer(@PathVariable String name) {

        return new ResponseEntity<>(customerService.getCustomerByName(name), HttpStatus.OK);
    }


    //save customer to db.
    @PostMapping("customer")
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody Customer customer) throws Exception {

        CustomerEntity customer1 = customerService.createCustomer(customer.getName(),customer.getSurname());


        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @DeleteMapping("customer/{uuid}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable UUID uuid) {

        Boolean isDeleted = customerService.deleteCustomerByUUID(uuid);
        if(isDeleted){
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.NOT_FOUND);

        }else {
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);

        }
    }

    //update
    @PutMapping("customer/{uuid}")
    public ResponseEntity<CustomerEntity> updateCustomerByUUID(@PathVariable UUID uuid, @RequestBody CustomerEntity newCustomerEntity){

        CustomerEntity customerEntity = customerService.updateCustomerByUUID(uuid, newCustomerEntity);
        if(customerEntity!=null){
            return new ResponseEntity<>(customerEntity, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
