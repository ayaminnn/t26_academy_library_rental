package jp.co.metateam.library.model;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 貸出マスタ
 */
@Entity
@Table(name = "RENTAL")
public class RentalManager {

    /** 貸出ID */
    @Id
    @Column(name = "id")
    private Long id;

    /** 社員ID */
    @Column(name = "employee_id")
    private String employeeId;

    /** 在庫管理番号 */
    @Column(name = "stock_id")
    private String stockId;

    /** 貸出予定日 */
    @Column(name = "expected_rental_on")
    private Date expectedRentalOn;

    /** 返却予定日 */
    @Column(name = "expected_return_on")
    private Date expectedReturnOn;

    /** キャンセル日時 */
    @Column(name = "canceled_at")
    private Timestamp canceledAt;

    /** 貸出日時 */
    @Column(name = "rentaled_at")
    private Timestamp rentaledAt;

    /** 返却日時 */
    @Column(name = "returned_at")
    private Timestamp returnedAt;

    /** ステータス */
    @Column(name = "status")
    private Integer status;

    /** 登録日時 */
    @Column(name = "created_at")
    private Timestamp createdAt;

    /** 更新日時 */
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    /** Getters */

    public Long getId() {
        return this.id;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public String getStockId() {
        return this.stockId;
    }

    public Date getExpectedRentalOn() {
        return this.expectedRentalOn;
    }

    public Date getExpectedReturnOn() {
        return this.expectedReturnOn;
    }

    public Timestamp getCanceledAt() {
        return this.canceledAt;
    }

    public Timestamp getRentaledAt() {
        return this.rentaledAt;
    }

    public Timestamp getReturnedAt() {
        return this.returnedAt;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    /** Setters */

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public void setExpectedRentalOn(Date expectedRentalOn) {
        this.expectedRentalOn = expectedRentalOn;
    }

    public void setExpectedReturnOn(Date expectedReturnOn) {
        this.expectedReturnOn = expectedReturnOn;
    }

    public void setCanceledAt(Timestamp canceledAt) {
        this.canceledAt = canceledAt;
    }

    public void setRentaledAt(Timestamp rentaledAt) {
        this.rentaledAt = rentaledAt;
    }

    public void setReturnedAt(Timestamp returnedAt) {
        this.returnedAt = returnedAt;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}