package pl.com.coders.shop2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.com.coders.shop2.domain.Category;
import pl.com.coders.shop2.domain.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    private Category category;

    @BeforeEach
    void setUp(){
        this.category = Category.builder()
                .name("Elektronika")
                .build();
    }

    @Test
    void add() {
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
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }
}