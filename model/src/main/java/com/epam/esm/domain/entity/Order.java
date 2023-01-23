package com.epam.esm.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = -3312407588923463088L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "purchase_time", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime purchaseTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "gift_certificate_id")
    private Certificate certificate;
}