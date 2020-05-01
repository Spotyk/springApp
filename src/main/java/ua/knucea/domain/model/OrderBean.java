package ua.knucea.domain.model;

import ua.knucea.domain.entity.Order;

public class OrderBean {

    private Order order;

    private Long totalSum;

    public OrderBean() {
    }

    public OrderBean(Order order, Long totalSum) {
        this.order = order;
        this.totalSum = totalSum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Long totalSum) {
        this.totalSum = totalSum;
    }
}
