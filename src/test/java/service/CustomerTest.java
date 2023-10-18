package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        // Initialize a new Customer with an empty orderLines list before each test
        customer = new Customer("John", 1000, new ArrayList<>());
    }

    @Test
    void testAddProduct_NewProduct() {
        OrderLine newProduct = new OrderLine();
        newProduct.setName("Product A");
        newProduct.setCode("A123");
        newProduct.setQuantity(1);
        newProduct.setPrice(10);

        customer.addProduct(newProduct);

        assertEquals(1, customer.getOrderLines().size());
    }

    @Test
    void testAddProduct_ExistingProduct() {
        OrderLine existingProduct = new OrderLine();
        existingProduct.setName("Product B");
        existingProduct.setCode("B456");
        existingProduct.setQuantity(2);
        existingProduct.setPrice(15);

        customer.addProduct(existingProduct);

        OrderLine updatedProduct = new OrderLine();
        updatedProduct.setName("Product B");
        updatedProduct.setCode("B456");
        updatedProduct.setQuantity(1);
        updatedProduct.setPrice(15);

        customer.addProduct(updatedProduct);

        assertEquals(1, customer.getOrderLines().size());
        assertEquals(3, customer.getOrderLines().get(0).getQuantity());
    }

    @Test
    void testAddProduct_NullParameter() {
        assertThrows(NullPointerException.class, () -> customer.addProduct(null));
    }

    @Test
    void testAddProduct_EmptyName() {
        OrderLine product = new OrderLine();
        product.setCode("C789");
        product.setQuantity(1);
        product.setPrice(20);

        customer.addProduct(product);

        assertEquals(0, customer.getOrderLines().size());
    }

    @Test
    void testAddProduct_DifferentName() {
        OrderLine productA = new OrderLine();
        productA.setName("Product A");
        productA.setCode("A123");
        productA.setQuantity(1);
        productA.setPrice(10);

        OrderLine productB = new OrderLine();
        productB.setName("Product B");
        productB.setCode("B456");
        productB.setQuantity(1);
        productB.setPrice(15);

        customer.addProduct(productA);
        customer.addProduct(productB);

        assertEquals(2, customer.getOrderLines().size());
    }

    @Test
    void testAddProduct_NegativeQuantity() {
        OrderLine product = new OrderLine();
        product.setName("Product C");
        product.setCode("C789");
        product.setQuantity(-1);
        product.setPrice(20);

        customer.addProduct(product);

        assertEquals(0, customer.getOrderLines().size());
    }

    @Test
    void testCalculateSum_NoExclusion() {
        OrderLine productA = new OrderLine();
        productA.setName("Product A");
        productA.setQuantity(2);
        productA.setPrice(10);

        OrderLine productB = new OrderLine();
        productB.setName("Product B");
        productB.setQuantity(1);
        productB.setPrice(15);

        customer.addProduct(productA);
        customer.addProduct(productB);

        int totalSum = customer.calculateSum("");
        assertEquals(35, totalSum);
    }

    @Test
    void testCalculateSum_WithExclusion() {
        OrderLine productA = new OrderLine();
        productA.setName("Product A");
        productA.setQuantity(2);
        productA.setPrice(10);

        OrderLine productB = new OrderLine();
        productB.setName("Product B");
        productB.setQuantity(1);
        productB.setPrice(15);

        customer.addProduct(productA);
        customer.addProduct(productB);

        int totalSum = customer.calculateSum("Product A");
        assertEquals(15, totalSum);
    }

    @Test
    void testCalculateSum_EmptyOrder() {
        int totalSum = customer.calculateSum("Product X");
        assertEquals(0, totalSum);
    }

    @Test
    void testCalculateSum_NullExclusion() {
        OrderLine productA = new OrderLine();
        productA.setName("Product A");
        productA.setQuantity(2);
        productA.setPrice(10);

        customer.addProduct(productA);

        int totalSum = customer.calculateSum(null);
        assertEquals(20, totalSum);
    }

    @Test
    void testCalculateSum_InvalidExclusion() {
        OrderLine productA = new OrderLine();
        productA.setName("Product A");
        productA.setQuantity(2);
        productA.setPrice(10);

        customer.addProduct(productA);

        int totalSum = customer.calculateSum("Product B");
        assertEquals(20, totalSum);
    }

    @Test
    void testCalculateSum_NegativePrice() {
        OrderLine productA = new OrderLine();
        productA.setName("Product A");
        productA.setQuantity(2);
        productA.setPrice(-10);

        customer.addProduct(productA);

        int totalSum = customer.calculateSum("");
        assertEquals(0, totalSum);
    }
}