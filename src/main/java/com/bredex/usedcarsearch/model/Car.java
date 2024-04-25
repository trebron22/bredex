package com.bredex.usedcarsearch.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue
    public Long id;

    @Column(length = 20)
    @Size(max = 20, message = "Type must be less than 20 character")
    private String brand;

    @Column(length = 20)
    @Size(max = 20, message = "Type must be less than 20 character")
    private String type;

    @Column(length = 200)
    @Size(max = 200, message = "Description must be less than 200 character")
    private String description;

    @Column(length = 10)
    private Long price;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
