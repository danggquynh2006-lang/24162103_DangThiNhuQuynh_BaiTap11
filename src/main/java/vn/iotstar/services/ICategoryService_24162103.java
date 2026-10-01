package vn.iotstar.services;
import java.util.List;
import vn.iotstar.entity.Category_24162103;

public interface ICategoryService_24162103 {
    List<Category_24162103> findAll();
    long countVideosByCategory(int categoryId);
}