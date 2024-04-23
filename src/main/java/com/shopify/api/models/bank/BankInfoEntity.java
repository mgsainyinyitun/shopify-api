package com.shopify.api.models.bank;

import com.shopify.api.models.image.ImageEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "bank_info")
public class BankInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false,unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",nullable = true)
    private ImageEntity image;

    private String uid;

    @PostPersist
    protected void onCreate() {
        String uuid = UUID.randomUUID().toString();
        String ID = String.valueOf(id);
        String name = this.getClass().getSimpleName().replace("Entity","");
        uid = name+ ID + uuid;
    }
}
