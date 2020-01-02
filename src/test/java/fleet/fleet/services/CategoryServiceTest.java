package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Category;
import fleet.fleet.models.Ship;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService mCategoryService;

    @Test
    void create() throws ResourceNotFound {

        Category category = new Category();
        category.setmShipType("Cruise");
        category.setmShipTonnage(208081);

        mCategoryService.create(category);
        Assert.isTrue(mCategoryService.getObjBy(
                category.getmCategoryId()).getmShipType().equals(category.getmShipType()),
                "Object is not created");
    }

    @Test
    void update() throws ResourceNotFound {
        Category category = new Category();
        category.setmShipType("Cruise");
        category.setmShipTonnage(208081);
        mCategoryService.create(category);

        Category updateCategory =  mCategoryService.getObjBy(category.getmCategoryId());
        updateCategory.setmShipType("Crude Oil Tanker");
        mCategoryService.update(updateCategory);

        Assert.isTrue(mCategoryService.getObjBy(
                updateCategory.getmCategoryId()).getmShipType().equals("Crude Oil Tanker"),
                "Object is not updated");
    }

    @Test
    void delete() throws ResourceNotFound {

        Category category = new Category();
        category.setmShipType("Cruise");
        category.setmShipTonnage(208081);
        mCategoryService.create(category);

        mCategoryService.delete(category.getmCategoryId());
        Assert.isTrue(!mCategoryService.getAll().contains(category),
                "Object is not deleted");
    }

    @Test
    void getAll() {

        Category category = new Category();
        category.setmShipType("Cruise");
        category.setmShipTonnage(208081);
        mCategoryService.create(category);
        mCategoryService.create(category);
        Assert.notEmpty(mCategoryService.getAll(),
                "Object list is empty");
    }

    @Test
    void getObjBy() throws ResourceNotFound {

        Category category = new Category();
        category.setmShipType("Cruise");
        category.setmShipTonnage(208081);
        mCategoryService.create(category);
        Assert.notNull(mCategoryService.getObjBy(category.getmCategoryId()),
                " Object is not returned!");
    }
}