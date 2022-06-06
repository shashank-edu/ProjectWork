// package com.webapp.springbootweb;

// import com.webapp.springbootweb.entities.Product;
// import com.webapp.springbootweb.repositories.ProductRepository;

// import org.assertj.core.api.Assertions;
// // import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.annotation.Rollback;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = Replace.NONE)
// @Rollback(false)
// public class ProductRepositoryTest {
//     @Autowired
//     private ProductRepository repo;

//     @Test
//     public void testAddNew(){
//         Product product = new Product();
//         product.setName("maggi");
//         product.setPrice(10);
//         product.setProductId("M#4322");
//         product.setbatchNo("#21");

//         Product saveProduct= repo.save(product);

//         // Assertions.assertThat
//         Assertions.assertThat(saveProduct).isNotNull();
//         Assertions.assertThat(saveProduct.getId()).isGreaterThan(0);

//     }
// }
