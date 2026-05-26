package jp.co.metateam.library.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.metateam.library.model.RentalManage;

@Repository
public interface RentalManageRepository
                extends JpaRepository<RentalManage, Long> {
        boolean existsByExpectedRentalOnBetween(LocalDate start, LocalDate end);
}