package com.hzyazilimci.blog.entities.sourceEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "comment_body")
    private String commentBody;

    @CreationTimestamp
    @Column(name = "comment_date")
    private LocalDate commentDate;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}
