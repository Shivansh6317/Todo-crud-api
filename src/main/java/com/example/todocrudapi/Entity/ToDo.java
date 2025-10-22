package com.example.todocrudapi.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false)
    private String title;

    private String description;

    private Date deadline;

    @NotBlank(message = "CreatedBy cannot be blank")
    @Column(nullable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
