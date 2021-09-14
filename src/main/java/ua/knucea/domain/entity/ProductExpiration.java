package ua.knucea.domain.entity;

import ua.knucea.domain.entity.product.ProductEntity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductExpiration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "expiration_history_id")
    private ExpirationHistory expirationHistory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @CollectionTable(name = "expirateion_level", joinColumns = @JoinColumn(name = "product_expiration_id"))
    @Enumerated(EnumType.STRING)
    private RiskLevel level;

    @Column(name = "days_left")
    private Long daysLeft;

    public Long getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(Long daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExpirationHistory getExpirationHistory() {
        return expirationHistory;
    }

    public void setExpirationHistory(ExpirationHistory expirationHistory) {
        this.expirationHistory = expirationHistory;
    }

    public RiskLevel getLevel() {
        return level;
    }

    public void setLevel(RiskLevel level) {
        this.level = level;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
