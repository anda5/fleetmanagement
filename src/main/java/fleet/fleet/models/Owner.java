package fleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Owner {

    @Id
    @Column(name = "mOwnerId")
    @GeneratedValue
    private int mOwnerId;
    @Column
    private String mOwnerName;
    @ManyToMany(mappedBy = "mOwnerList",cascade = { CascadeType.ALL })
    private List<Ship> mListShip = new ArrayList<>();

    public Owner(){
    }

    public Owner(String ownerName){
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
    public List<Ship> getListShip() { return mListShip; }

    public void setListShip(List<Ship> mListShip) {this.mListShip = mListShip;}

    public String toString(){
        return "Owner["+mOwnerId+","+mOwnerName+"]";
    }
}
