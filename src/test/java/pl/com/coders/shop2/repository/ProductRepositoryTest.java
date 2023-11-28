package pl.com.coders.shop2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.com.coders.shop2.domain.Category;
import pl.com.coders.shop2.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.ClassUtils.isPresent;

@ActiveProfiles("test")
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public CategoryRepository categoryRepository;
    private Category category;


    @BeforeEach
    void setUp(){
        this.category = Category.builder()
                .id(1L)
                .name("Elektronika")
                .build();
    }

    @Test
    void add() {
        category = categoryRepository.save(category);
        Product product = Product.builder()
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();
        Product addedProduct = productRepository.add(product);
        assertEquals(product, addedProduct);
    }

    @Test
    void get() {
        Product product = Product.builder()
                .id(1L)
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();

        assertEquals(1L, product.getId());
    }

    @Test
    void deleteByProduct() {
                Product product = Product.builder()
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();

        productRepository.add(product);
        int count1 = productRepository.findAll().size();
        productRepository.deleteByProduct(product);
        int count2 = productRepository.findAll().size();
        assertEquals(1, count1- count2);
    }

    @Test
    void update() {
        Product product = Product.builder()
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();

        productRepository.add(product);
        product.setName("jablko");

        Product updated = productRepository.update(product);
        updated.setName("jablko");

        assertEquals("jablko", product.getName());
    }

    @Test
    void findAll() {
        for(int i = 1; i <= 10; i++ ) {
            Product product = Product.builder()
                    .name("pomarancza" + i)
                    .description("opis")
                    .quantity(20)
                    .price(new BigDecimal("40.5"))
                    .category(category)
                    .build();
            productRepository.add(product);
        }

        List<Product> products = productRepository.findAll();
        assertEquals(10, productRepository.findAll().size());
    }
}
