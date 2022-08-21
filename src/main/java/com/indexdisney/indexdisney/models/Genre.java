package com.indexdisney.indexdisney.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="genres")
@Data
@NoArgsConstructor
public class Genre {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;

    @NotNull
    private String genreName;

    @NotNull
    private String genreImgUrl;


    //Relacion con Movie
    @OneToMany(mappedBy= "genre")
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();
}
