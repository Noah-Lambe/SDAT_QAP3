package com.keyin.club.QAP3.members;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyin.club.QAP3.tournaments.Tournaments;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate membershipStartDate;
    private int membershipDurationInMonths;

    @ManyToMany
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    @JsonIgnoreProperties("members")
    private List<Tournaments> tournaments = new ArrayList<>();

    public List<Tournaments> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournaments> tournaments) {
        this.tournaments = tournaments;
    }

    public Members() {}

    public Members(Long id, String name, String address, String email, String phoneNumber, LocalDate membershipStartDate, int membershipDurationInMonths) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipDurationInMonths = membershipDurationInMonths;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public int getMembershipDurationInMonths() {
        return membershipDurationInMonths;
    }

    public void setMembershipDurationInMonths(int membershipDurationInMonths) {
        this.membershipDurationInMonths = membershipDurationInMonths;
    }

    @Override
    public String toString() {
        return "Members{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipStartDate=" + membershipStartDate +
                ", membershipDurationInMonths=" + membershipDurationInMonths +
                '}';
    }
}
