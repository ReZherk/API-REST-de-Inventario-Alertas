package com.ReZherk.alertas_inventario.web.dto;

public class AlertResponseDTO {
    public Long id;
    public Long productId;
    public String productSku;
    public String productName;
    public String type;
    public String message;
    public Boolean acknowledged;
    public String createdAt;
}
