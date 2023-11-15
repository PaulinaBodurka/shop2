package pl.com.coders.shop2.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.com.coders.shop2.domain.Category;
import pl.com.coders.shop2.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductRepository {
    @PersistenceContext

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    public Product add(Product product, Long categoryId) throws Exception {
        Category category = entityManager.find(Category.class, categoryId );
        if(category == null){
            throw new Exception("Category with this id not exist" + categoryId);
        }
        entityManager.persist(product);
        entityManager.persist(category);
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());

        return product;
    }

    public Product get(Long id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    @Transactional
    public boolean delete(Long id) {
        Product toDeleteProduct = get(id);
        entityManager.remove(toDeleteProduct);
        return true;
    }

    @Transactional
    public Product update(Product product, Long id) {
        Product old = get(id);
        old.setName(product.getName());
        old.setDescription(product.getDescription());
        old.setPrice(product.getPrice());
        old.setQuantity(product.getQuantity());
        entityManager.merge(old);
        return old;
    }

    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}

