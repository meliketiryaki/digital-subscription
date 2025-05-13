package com.meliketiryaki.digital_subscription.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName; 
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private User user;
}
