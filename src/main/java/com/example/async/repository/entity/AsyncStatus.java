package com.example.async.repository.entity;

import com.example.async.vo.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AsyncStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private String userId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String message;
}
