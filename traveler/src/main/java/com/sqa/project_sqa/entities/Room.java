package com.sqa.project_sqa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "area", nullable = false)
    private Double area;

    @Column(name = "number_people", nullable = false)
    private int numberPeople;

    @Column(name = "big_bed", nullable = false)
    private int bigBed;

    @Column(name = "small_bed", nullable = false)
    private int smallBed;

    @Column(name = "bathtub", nullable = false)
    private  boolean bathtub;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "fee", nullable = false)
    private Double fee;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    @JsonIgnore
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;
}
