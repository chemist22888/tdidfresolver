package com.sentisquare.tdidfresolver.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Word {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable = false,unique = true)
    String word;
    @Column(nullable = false)
    Double idf;
}
