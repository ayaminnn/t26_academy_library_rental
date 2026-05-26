package jp.co.metateam.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jp.co.metateam.library.model.RentalManage;
import jp.co.metateam.library.model.RentalManageDto;
import jp.co.metateam.library.repository.RentalManageRepository;
import jp.co.metateam.library.values.RentalStatus;

import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class RentalManageService {

        private final RentalManageRepository rentalManageRepository;

        @Autowired
        public RentalManageService(
                        RentalManageRepository rentalManageRepository) {
                this.rentalManageRepository = rentalManageRepository;
        }

        // 貸出期間の重複チェック
        public boolean existsOverlap(LocalDate rentalDate, LocalDate returnDate) {
                return rentalManageRepository.existsByExpectedRentalOnBetween(rentalDate, returnDate);
        }

        // 貸出登録
        public void save(RentalManageDto dto) {
                RentalManage rentalManage = new RentalManage();

                rentalManage.setEmployeeId(dto.getEmployeeId());
                rentalManage.setStockId(dto.getStockId());
                rentalManage.setExpectedRentalOn(dto.getExpectedRentalOn());
                rentalManage.setExpectedReturnOn(dto.getExpectedReturnOn());
                rentalManage.setStatus(dto.getStatus());
                rentalManage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                rentalManage.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                rentalManageRepository.save(rentalManage);
        }

        public void validateBusinessRule(RentalManageDto dto, BindingResult bindingResult) {
                // 返却予定日 >= 貸出予定日
                if (dto.getExpectedRentalOn() != null
                                && dto.getExpectedReturnOn() != null
                                && dto.getExpectedReturnOn().isBefore(dto.getExpectedRentalOn())) {

                        bindingResult.rejectValue(
                                        "expectedReturnOn",
                                        null,
                                        "「返却予定日」は「貸出予定日」以降の日付を入力してください");
                }

                // ステータスチェック
                if (RentalStatus.RETURNED.getValue().equals(dto.getStatus())
                                || RentalStatus.CANCELED.getValue().equals(dto.getStatus())) {

                        bindingResult.rejectValue("status", null,
                                        "貸出ステータスは「貸出待ち」もしくは「貸出中」を選択してください");
                }

                // 未来日付 × ステータス
                if (dto.getExpectedRentalOn() != null &&
                                dto.getExpectedRentalOn().isAfter(LocalDate.now()) &&
                                !RentalStatus.RENT_WAIT.getValue().equals(dto.getStatus())) {

                        bindingResult.rejectValue("status", null,
                                        "未来日付では「貸出待ち」を選択してください");
                }

                // 過去日付 × ステータス
                if (dto.getExpectedRentalOn() != null &&
                                dto.getExpectedRentalOn().isBefore(LocalDate.now()) &&
                                !RentalStatus.RENTAlING.getValue().equals(dto.getStatus())) {

                        bindingResult.rejectValue("status", null,
                                        "過去日付では「貸出中」を選択してください");
                }
        }

        // 一覧取得
        public List<RentalManage> findAll() {
                return rentalManageRepository.findAll();
        }
}