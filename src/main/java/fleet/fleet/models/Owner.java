package fleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fleet.fleet.Utils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.OnDeleteAction.CASCADE;

/**
 * Owner class is mapped the owner table from db. Class contains all columns as fields: owner id,
 * owner name, list of ships. This table has MANY TO MANY relation with Ship class. One owner can have many ships.
 * Also, the class contains getters and setters for all properties.
 **/
@Entity
@Table
public class Owner {

    @Id
    @Column(name = Utils.OWNER_ID)
    @GeneratedValue
    private int mOwnerId;
    @Column
    private String mOwnerName;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = Utils.OWNER_LIST)
    private List<Ship> mListShip = new ArrayList<>();

    public Owner() {
    }

    public Owner(String ownerName) {
        mOwnerName = ownerName;
    }

    public int getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(int mOwnerId) {
        this.mOwnerId = mOwnerId;
    }

    public String getOwnerName() {
        return mOwnerName;
    }

    public void setOwnerName(String mOwnerName) {
        this.mOwnerName = mOwnerName;
    }

    @JsonIgnore
    public List<Ship> getListShip() {
        return mListShip;
    }

    public void setListShip(List<Ship> mListShip) {
        this.mListShip = mListShip;
    }

    @Override
    public String toString() {
        return Utils.OWNER_OBJ + Utils.LINE_SYMBOL + mOwnerId + Utils.COMMMA_SYMBOL + mOwnerName;
    }
}
