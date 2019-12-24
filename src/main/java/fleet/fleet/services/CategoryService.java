package fleet.fleet.services;

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
        if (obj != null) {
           return mCategRepository.save(category);
        }
        return null;
    }

    @Override
    public Category update(Object object) {
        Category category = (Category) object;
        Optional<Category> optionalCategory = mCategRepository.findById(category.getmCategoryId());
        if (optionalCategory.isPresent()) {
            Category existentCategory = optionalCategory.get();
            existentCategory.setmShipTonnage(category.getmShipTonnage());
            existentCategory.setmShipType(category.getmShipType());
            existentCategory.setShip(category.getShip());

           return mCategRepository.save(existentCategory);
        }
       return null;
    }

    @Override
    public void delete(int id) {
        Optional<Category> category = mCategRepository.findById(id);
        if (category.isPresent()) {
            mCategRepository.delete(category.get());
        }
    }

    @Override
    public List<Object> getAll() {
        List<Category> categoryList = mCategRepository.findAll();
        List<Object> objectList = new ArrayList<Object>(categoryList);
        return objectList;
    }

    @Override
    public Category getObjBy(int id) {
        Optional<Category> category = mCategRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }
        return null;
    }
}
