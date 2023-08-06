package erp.erp.database.repository;


import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {



}
