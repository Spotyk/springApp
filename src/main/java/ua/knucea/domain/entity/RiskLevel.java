package ua.knucea.domain.entity;

public enum RiskLevel {
    GREEN, YELLOW, RED;

    public String getStatus() {
        return name();
    }
}
