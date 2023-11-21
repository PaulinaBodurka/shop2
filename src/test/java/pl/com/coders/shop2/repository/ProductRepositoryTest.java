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
                .id(1L)
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();

        productRepository.deleteByProduct(product);


        // jeśli chcę teraz usunąć produkt i chcę np zrobić metodę deletebyproduct którą dodałam w ProductRepository,
        // to mam stworzyć w Controllerze jeszcze jeden DeleteMapping czy można dodać do obecnego mappinga jeszcze
        // @RequestParam Product product    return productService.delete(id,product) i zmienić jeszcze productService
        // dlaczego w tym teście jest attempt to create delete event with null entity
    }

    @Test
    void update() {
        Product product = Product.builder()
                .id(1L)
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();

        Product updated = productRepository.update(product,1L );
        updated.setName("jablko");

        assertEquals("jablko", product.getName());
    }
    // dlaczego jest tak? Cannot invoke "pl.com.coders.shop2.domain.Product.setName(String)" because "old" is null
    //próbowałam zrobić z old obiekt ale też nie działa


    @Test
    void findAll() {
        List<Product> products = new ArrayList<>();
        Product product = Product.builder()
                .id(1L)
                .name("pomarancza")
                .description("opis")
                .quantity(20)
                .price(new BigDecimal("40.5"))
                .category(category)
                .build();

        int count = products.size();

        assertEquals(1, productRepository.products.size());
    }
    //dlaczego "this.productRepository.products" is null

}