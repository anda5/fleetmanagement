package unittest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Owner {

    @Id
    @Column(name = "mOwnerId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int mOwnerId;
    @Column
    private String mOwnerName;
    @ManyToMany(mappedBy = "mOwnerList",fetch=FetchType.LAZY)
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

    public List<Ship> getListShip() { return mListShip; }

    public void setListShip(List<Ship> mListShip) {this.mListShip = mListShip;}

    public String toString(){
        return "Owner["+mOwnerId+","+mOwnerName+"]";
    }
}
