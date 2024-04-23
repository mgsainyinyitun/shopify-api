package com.shopify.api.models.trade;

import com.shopify.api.constant.TRADING_STATE;
import com.shopify.api.models.product.ProductEntity;
import com.shopify.api.models.user.UserEntity;
import com.shopify.api.utils.IdUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "trade_history")
public class TradeHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trader_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "trade_product_id")
    private ProductEntity product;

    @Column(name = "order_Id",unique = true)
    private String orderId;

    private Integer taskNumber;

    private  Double orderPrice;

    @Enumerated(EnumType.STRING)
    private TRADING_STATE state;

    @CreationTimestamp
    @Column(name = "traded_at", nullable = false, updatable = false)
    private LocalDateTime tradedAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = true)
    private LocalDateTime updatedAt;

    @PostPersist
    protected void onCreate() {
        String uui = IdUtils.generateRandomId(id);
        orderId = id + uui;
    }
}
