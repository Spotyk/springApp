package ua.knucea.domain.entity;

import org.springframework.format.annotation.DateTimeFormat;
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
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class DemandHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "demand_history_date")
    private Timestamp demandDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Timestamp getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Timestamp demandDate) {
        this.demandDate = demandDate;
    }
}
