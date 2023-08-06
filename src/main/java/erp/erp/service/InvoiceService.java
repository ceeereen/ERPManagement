package erp.erp.service;


import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.InvoiceEntity;
import erp.erp.database.entity.OrderEntity;
import erp.erp.database.entity.ProductEntity;
import erp.erp.database.repository.CustomerRepository;
import erp.erp.database.repository.InvoiceRepository;
import erp.erp.database.repository.OrderRepository;
import erp.erp.model.OrderStatusEnum;
import jakarta.persistence.Column;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//bean
@Service
public class InvoiceService {


    @Autowired
    OrderRepository orderRepository;
    @Autowired
    InvoiceRepository invoiceRepository;


    public InvoiceEntity createInvoice(InvoiceEntity invoiceEntity, Long order_id) {
        InvoiceEntity invoiceEntity1 = invoiceEntity;
        //order is added to invoice
        addOrderToInvoice(invoiceEntity1, order_id);
        //updated invoice is added to db
        invoiceRepository.save(invoiceEntity1);
        return invoiceEntity;
    }

    public List<InvoiceEntity> getInvoiceList() {
        return invoiceRepository.findAll();
    }

    //add order to invoice. By this, product and customer also added to invoice.
    public void addOrderToInvoice(InvoiceEntity invoiceEntity, Long order_id) {
        BigDecimal kdvTotalPrice = BigDecimal.ZERO;
        BigDecimal normalTotalPrice = BigDecimal.ZERO;
        OrderEntity orderEntity = orderRepository.findById(order_id).get();
        orderEntity.setOrderStatusEnum(OrderStatusEnum.APPROVED);
        //amount of products in invoice is size of product list
        invoiceEntity.setNumberOfProduct(orderEntity.getProductList().size());
        for (ProductEntity product : orderEntity.getProductList()) {
            if (invoiceEntity.getNameList() != null) {
                //add product name to invoice
                invoiceEntity.getNameList().add(product.getName());
                //update price if KDVapplicable and set it to invoice
                if (product.getIsKdvApplicable()) {
                    invoiceEntity.setKdvPrice(product.getPrice().multiply(BigDecimal.valueOf(1.2)));
                    invoiceEntity.setNormalPrice(product.getPrice());
                    normalTotalPrice.add(product.getPrice());
                    kdvTotalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(1.2))).add(normalTotalPrice);
                    //if kdv is not applicable set normal price
                } else {
                    invoiceEntity.setNormalPrice(product.getPrice());
                    normalTotalPrice.add(product.getPrice());
                }
                //if nameList in invoice is null
            } else {
                List<String> nameList = new ArrayList<>();
                nameList.add(product.getName());
                invoiceEntity.setNameList(nameList);
                //update price if KDVapplicable and set it to invoice
                if (product.getIsKdvApplicable()) {
                    invoiceEntity.setKdvPrice(product.getPrice().multiply(BigDecimal.valueOf(1.2)));
                    invoiceEntity.setNormalPrice(product.getPrice());
                    invoiceEntity.setNormalTotalPrice(normalTotalPrice.add(product.getPrice()));
                    invoiceEntity.setKdvTotalPrice(kdvTotalPrice.add(product.getPrice().multiply(BigDecimal.valueOf(1.2))).
                            add(normalTotalPrice));
                    //if kdv is not applicable set normal price
                } else {
                    invoiceEntity.setNormalPrice(product.getPrice());
                    normalTotalPrice.add(product.getPrice());
                }

            }

        }
    }

}
