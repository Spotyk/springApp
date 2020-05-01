package ua.knucea.domain.entity;

public enum OrderStatus {
    DONE, CANCELED, IN_PROGRESS;

    public String getStatus() {
        return name();
    }
}
