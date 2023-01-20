package hellojpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;


    @Column(name = "USERNAME")
    private String username;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    // 기간 Period
    @Embedded

    private Period period;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }


    // 주소

    @Embedded
    private Address HomeAddress;

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns =
            @JoinColumn(name="MEMBER_ID"))
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(name="ADDRESS", joinColumns =
//            @JoinColumn(name="MEMBER_ID"))
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public Address getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        HomeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }


    public void setTeam(Team team) {
        this.team = team;
    }
}
