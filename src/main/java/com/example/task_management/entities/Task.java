package com.example.task_management.entities;

import com.example.task_management.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false)
    String title;

    String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    Status status;

    @Column(name = "created_date", nullable = false)
    Instant createdDate;

    @Column(name = "updated_date", nullable = false)
    Instant updatedDate;

    @Column(nullable = false)
    Instant deadline;

    @ManyToOne
    @JoinColumn(name = "executor_id", nullable = false)
    User executor;

}
