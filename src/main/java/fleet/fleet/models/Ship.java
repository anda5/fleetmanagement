package fleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fleet.fleet.Utils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ship class is mapped the ship table from db. Class contains all columns as fields: ship id,
 * ship name, lmo nummber, list of owners. This table has MANY TO MANY relation with Owner class.
 * One ship can have many owners. Also, the class contains getters and setters for all properties.
 **/
@Entity
@Table
public class Ship {

    @Id
    @Column(name = Utils.SHIP_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mShipId;
    @Column
    private String mShipName;
    @Column
    private long mLmoNumber;
    @ManyToMany(fetch = FetchType.EAGER,
    cascade = {
        CascadeType.PERSIST,
                CascadeType.MERGE
    })
    @JoinTable(name = Utils.JOINED_TABLE,
            joinColumns = {@JoinColumn(name = Utils.SHIP_ID,updatable = true) },
            inverseJoinColumns = {@JoinColumn(name = Utils.OWNER_ID, updatable = true)})
    private Set<Owner> mOwnerList = new HashSet<>();

    public Ship() {
    }

    public Ship(String shipName, long lmoNumber) {
        mShipName = shipName;
        mLmoNumber = lmoNumber;
    }

    public int getShipId() {
        return mShipId;
    }

    public void setShipId(int mShipId) {
        this.mShipId = mShipId;
    }

    public String getShipName() {
        return mShipName;
    }

    public void setShipName(String mShipName) {
        this.mShipName = mShipName;
    }

    public long getLmoNumber() {
        return mLmoNumber;
    }

    public void setLmoNumber(long mLmoNumber) {
        this.mLmoNumber = mLmoNumber;
    }

    public Set<Owner> getOwnerList() {
        return mOwnerList;
    }

    public void setOwnerList(Set<Owner> mOwnerList) {
        this.mOwnerList = mOwnerList;
    }

    @Override
    public String toString() {
        return Utils.SHIP_OBJ + Utils.LINE_SYMBOL + mShipId +
                Utils.COMMMA_SYMBOL + mShipName + Utils.COMMMA_SYMBOL + mLmoNumber;
    }
}
