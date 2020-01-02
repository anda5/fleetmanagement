package fleet.fleet.services;

import fleet.fleet.exception.ResourceNotFound;
import fleet.fleet.models.Category;
import fleet.fleet.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryService extends CRUDService {

    @Autowired
    private CategoryRepository mCategRepository;


    @Override
    public Category create(Object obj) {
        Category category = (Category) obj;
        return mCategRepository.save(category);
    }

    @Override
    public Category update(Object object) throws ResourceNotFound {
        Category category = (Category) object;
        Optional<Category> optionalCategory = mCategRepository.findById(category.getmCategoryId());
        if (optionalCategory.isPresent()) {
            Category existentCategory = optionalCategory.get();
            existentCategory.setmShipTonnage(category.getmShipTonnage());
            existentCategory.setmShipType(category.getmShipType());
            existentCategory.setShip(category.getShip());

            return mCategRepository.save(existentCategory);
        } else {
            throw new ResourceNotFound("Category not found for this id : " + category.getmCategoryId());
        }
    }

    @Override
    public void delete(int id) throws ResourceNotFound {
        Optional<Category> category = mCategRepository.findById(id);
        if (category.isPresent()) {
            mCategRepository.delete(category.get());
        } else {
            throw new ResourceNotFound("Categroy not found for this id :" + id);
        }
    }

    @Override
    public List<Object> getAll() {
        List<Category> categoryList = mCategRepository.findAll();
        List<Object> objectList = new ArrayList<Object>(categoryList);
        return objectList;
    }

    @Override
    public Category getObjBy(int id) throws ResourceNotFound {
        Optional<Category> category = mCategRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new ResourceNotFound("Category not found for this id : " + id);
        }
    }
}
