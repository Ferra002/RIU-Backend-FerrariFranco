package com.mindata.riu.writer.infrastructure.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "search")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "search_id", nullable = false, unique = true)
    private String searchId;

    @Column(name = "hotel_id", nullable = false)
    private String hotelId;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    // Used to optimize 'ages' counts. Example: [1,2,3] --> "1,2,3"
    @Column(name = "raw_ages", nullable = false)
    private String rawAges;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "search_age", joinColumns = @JoinColumn(name = "search_id"))
    @Column(name = "age", nullable = false)
    @OrderColumn(name = "position")
    private List<@Min(0) Integer> ages;

    public SearchEntity(
            String searchId,
            String hotelId,
            LocalDate checkIn,
            LocalDate checkOut,
            String rawAges,
            List<Integer> ages
    ) {
        this.searchId = searchId;
        this.hotelId = hotelId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.rawAges = rawAges;
        this.ages = ages;
    }
}
