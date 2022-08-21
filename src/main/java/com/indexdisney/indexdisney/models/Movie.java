package com.indexdisney.indexdisney.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Range;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="movies")
@Data
@NoArgsConstructor
public class Movie {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
	private Long movieId;

    @NotNull
    private String movieImgUrl;

    @NotNull
    private String movieTitle;
    
    @NotNull
    private java.sql.Date movieReleaseDate;

    @Range(min=0, max=10)
    private Float movieScore;

    //Relación con DisneyCharacter
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(
        name = "wasIn",
        joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "movie_id"),
        inverseJoinColumns = @JoinColumn(name= "character_id", referencedColumnName = "character_id")
    )
    private List<DisneyCharacter> characters;


    //Relación con Genre
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    @JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "genreId")
    private Genre genre;
}
