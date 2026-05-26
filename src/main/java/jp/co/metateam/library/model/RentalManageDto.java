package jp.co.metateam.library.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 貸出管理DTO
 */
@Getter
@Setter
public class RentalManageDto {

  /** 貸出ID */
  private Long id;

  /** 社員ID */
  @NotBlank(message = "社員番号は必須です")
  private String employeeId;

  /** 在庫管理番号 */
  @NotBlank(message = "在庫管理番号は必須です")
  private String stockId;

  /** 貸出予定日 */
  @NotNull(message = "貸出予定日は必須です")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate expectedRentalOn;

  /** 返却予定日 */
  @NotNull(message = "返却予定日は必須です")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate expectedReturnOn;

  /** キャンセル日時 */
  private Timestamp canceledAt;

  /** 貸出日時 */
  private Timestamp rentaledAt;

  /** 返却日時 */
  private Timestamp returnedAt;

  /** ステータス */
  @NotNull(message = "ステータスは必須です")
  private Integer status;

  /** 登録日時 */
  private Timestamp createdAt;

  /** 更新日時 */
  private Timestamp updatedAt;
}