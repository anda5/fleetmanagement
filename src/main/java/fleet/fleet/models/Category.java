package fleet.fleet.models;

import javax.persistence.*;

@Table
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "categid", unique = true, nullable = false)
    private int mCategoryId;
    @Column
    private String mShipType;
    @Column
    private long mShipTonnage;
    @OneToOne
    @JoinColumn(name = "shipId", referencedColumnName = "shipId")
    private Ship mShip;

    public Category(){}

    public Category(String shipType, long shipTonnage){
        mShipType = shipType;
        mShipTonnage = shipTonnage;
    }

    public int getmCategoryId() {
        return mCategoryId;
    }

    public void setmCategoryId(int mCategoryId) {
        this.mCategoryId = mCategoryId;
    }
    public String getmShipType() {
        return mShipType;
    }

    public void setmShipType(String mShipType) {
        this.mShipType = mShipType;
    }

    public long getmShipTonnage() {
        return mShipTonnage;
    }

    public void setmShipTonnage(long mShipTonnage) {
        this.mShipTonnage = mShipTonnage;
    }

    public Ship getShip() {
        return mShip;
    }

    public void setShip(Ship mShip) {
        this.mShip = mShip;
    }

    public String toString(){
        return "Category["+mCategoryId+","+ mShipType+","+mShipTonnage+"]";
    }
}
