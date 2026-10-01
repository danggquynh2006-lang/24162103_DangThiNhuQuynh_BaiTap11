package vn.iotstar.services.impl;
import java.util.List;
import vn.iotstar.dao.CategoryDao_24162103;
import vn.iotstar.entity.Category_24162103;
import vn.iotstar.services.ICategoryService_24162103;

public class CategoryServiceImpl_24162103 implements ICategoryService_24162103 {
    private CategoryDao_24162103 categoryDao = new CategoryDao_24162103();

    @Override
    public List<Category_24162103> findAll() { return categoryDao.findAll(); }

    @Override
    public long countVideosByCategory(int categoryId) { return categoryDao.countVideosByCategory(categoryId); }
}