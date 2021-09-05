package ua.knucea.domain.entity;

import ua.knucea.domain.entity.category.CategoryEntity;
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
public class ProductDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "demand_history_id")
    private DemandHistory demandHistory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @CollectionTable(name = "demand_level", joinColumns = @JoinColumn(name = "product_demand_id"))
    @Enumerated(EnumType.STRING)
    private DemandLevel level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandHistory getDemandHistory() {
        return demandHistory;
    }

    public void setDemandHistory(DemandHistory demandHistory) {
        this.demandHistory = demandHistory;
    }

    public DemandLevel getLevel() {
        return level;
    }

    public void setLevel(DemandLevel level) {
        this.level = level;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
