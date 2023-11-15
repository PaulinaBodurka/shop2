package pl.com.coders.shop2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.com.coders.shop2.domain.Category;
import pl.com.coders.shop2.domain.Product;
import pl.com.coders.shop2.repository.ProductRepository;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product product, Category category) throws Exception {
        return productRepository.add(product, category.getId());

    }

    public Product get(Long id) {
        return productRepository.get(id);
    }

    public boolean delete(Long id) {
        return productRepository.delete(id);
    }

    public Product update(Product product, Long id) {
        return productRepository.update(product,id);
    }
}
