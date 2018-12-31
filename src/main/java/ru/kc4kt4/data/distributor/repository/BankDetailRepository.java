package ru.kc4kt4.data.distributor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kc4kt4.data.distributor.entity.BankDetail;

public interface BankDetailRepository extends JpaRepository<BankDetail, String> {
}
