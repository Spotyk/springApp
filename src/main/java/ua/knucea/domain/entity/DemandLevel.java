package ua.knucea.domain.entity;

public enum DemandLevel {
    GREEN, YELLOW, RED;

    public String getStatus() {
        return name();
    }
}
