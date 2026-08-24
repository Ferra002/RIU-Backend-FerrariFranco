package com.mindata.riu.infrastructure.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "search")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchEntity {

    @Id
    private Long id;

    @Column(name = "search_id", nullable = false, unique = true)
    private String searchId;

    @Column(name = "hotel_id", nullable = false)
    private String hotelId;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "search_age", joinColumns = @JoinColumn(name = "search_id"))
    @Column(name = "age", nullable = false)
    @OrderColumn(name = "position")
    private List<@Min(0) Integer> ages;

}
