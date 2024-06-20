package com.example.easystay.model.entity;

import com.example.easystay.core.report.loging.CrudEventListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity(name = "payments")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(CrudEventListener.class)//Loglama için gerekli.
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    private double paymentAmount;
    private String paymentMethod;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;
}
