package unittest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Ship {

    @Id
    @Column(name = "shipId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int mShipId;
    @Column
    private String mShipName;
    @Column
    private long mLmoNumber;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name = "ShipOwner",
            joinColumns ={ @JoinColumn(name ="shipId", updatable = true,insertable = true)},
            inverseJoinColumns = {@JoinColumn(name = "mOwnerId", updatable = true,insertable = true)})
    private List<Owner> mOwnerList = new ArrayList<>();

    public Ship(){

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

    public void setLmoNumber(int mLmoNumber) {
        this.mLmoNumber = mLmoNumber;
    }

    public List<Owner> getOwnerList() {
        return mOwnerList;
    }

    public void setOwnerList(List<Owner> mOwnerList) {
        this.mOwnerList = mOwnerList;
    }

    public String toString() {
        return "Ship[" + mShipId + "," + mShipName + "," + mLmoNumber + "]";
    }
}
