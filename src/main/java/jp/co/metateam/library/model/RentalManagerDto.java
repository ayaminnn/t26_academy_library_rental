package jp.co.metateam.library.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * 貸出管理DTO
 */
@Getter
@Setter
public class RentalManagerDto {

    /** 貸出ID */
    private Long id;

    /** 社員ID */
    private String employeeId;

    /** 在庫管理番号 */
    private String stockId;

    /** 貸出予定日 */
    private Date expectedRentalOn;

    /** 返却予定日 */
    private Date expectedReturnOn;

    /** キャンセル日時 */
    private Timestamp canceledAt;

    /** 貸出日時 */
    private Timestamp rentaledAt;

    /** 返却日時 */
    private Timestamp returnedAt;

    /** ステータス */
    private Integer status;

    /** 登録日時 */
    private Timestamp createdAt;

    /** 更新日時 */
    private Timestamp updatedAt;
}