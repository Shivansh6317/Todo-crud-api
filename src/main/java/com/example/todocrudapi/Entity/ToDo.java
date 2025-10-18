package com.example.todocrudapi.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "todos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private Date deadline;

    @Column(nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
