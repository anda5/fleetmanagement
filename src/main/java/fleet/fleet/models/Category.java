package fleet.fleet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fleet.fleet.Utils;

import javax.persistence.*;

/**
 * Category class is mapped the category table from db. Class contains all columns as fields: category id,
 * ship type, ship tonnage and Ship object.Ships have details about themselves such as Name, ImoNumber, Type, Tonnage
 * Also, the class contains getters and setters for all properties
 **/
@Table
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Utils.CATEG_ID, unique = true, nullable = false)
    private int mCategoryId;
    @Column
    private String mShipType;
    @Column
    private long mShipTonnage;
    @OneToOne
    @JoinColumn(name = Utils.SHIP_ID, referencedColumnName = Utils.SHIP_ID)
    private Ship mShip;

    public Category() {
    }

    public Category(String shipType, long shipTonnage) {
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

    public String toString() {
        return Utils.CATEGORY_OBJ + Utils.LINE_SYMBOL + mCategoryId +
                Utils.COMMMA_SYMBOL + mShipType + Utils.COMMMA_SYMBOL + mShipTonnage;
    }
}
