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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.URL;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="characters")
@Data
@NoArgsConstructor
public class DisneyCharacter {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
	private Long characterId;

    @URL
    @NotNull
    private String characterImg;

    @NotNull
    private String characterName;

    @JsonIgnore
    private String characterAge;

    @JsonIgnore
    private String characterWeight;

    @Size(min=1,  max=100)
    @JsonIgnore
    private String characterStory;

    //Relacion con Movie
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ CascadeType.MERGE})
    @JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "movieId")
    @JoinTable(
        name = "wasIn",
        joinColumns = @JoinColumn(name = "character_id", referencedColumnName = "character_id"),
        inverseJoinColumns = @JoinColumn(name= "movie_id", referencedColumnName = "movie_id")
    )
    private List<Movie> movies;  
}

