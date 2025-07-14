package com.domain.app.category.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "category", uniqueConstraints = @UniqueConstraint(columnNames = "name") )
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 7)
    private String color;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(length = 255)
    private String description;
}
