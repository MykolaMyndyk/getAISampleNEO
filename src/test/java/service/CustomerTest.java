package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        List<OrderLine> orderLines = new ArrayList<>();
        this.customer = new Customer("John Doe", orderLines);
    }

    @Test
    void testAddProduct() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        customer.addProduct(product1);

        assertEquals(1, customer.getOrderLines().size());
        assertEquals(1, customer.count);
    }

    @Test
    void testAddProductNull() {
        customer.addProduct(null);

        assertEquals(0, customer.getOrderLines().size());
        assertEquals(0, customer.count);
    }

    @Test
    void testAddProductExisting() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        customer.addProduct(product1);

        OrderLine product2 = new OrderLine();
        product2.setName("Product1");
        product2.setCode("001");
        customer.addProduct(product2);

        assertEquals(1, customer.getOrderLines().size());
        assertEquals(1, customer.count);
    }

    @Test
    void testAddProductWithSameNameDifferentCode() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        customer.addProduct(product1);

        OrderLine product2 = new OrderLine();
        product2.setName("Product1");
        product2.setCode("002");
        customer.addProduct(product2);

        assertEquals(2, customer.getOrderLines().size());
        assertEquals(2, customer.count);
    }

    @Test
    void testAddProductWithDifferentNameSameCode() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        customer.addProduct(product1);

        OrderLine product2 = new OrderLine();
        product2.setName("Product2");
        product2.setCode("001");
        customer.addProduct(product2);

        assertEquals(2, customer.getOrderLines().size());
        assertEquals(2, customer.count);
    }

    @Test
    void testAddProductWithExistingProductIncreaseQuantity() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setQuantity(1);
        customer.addProduct(product1);

        OrderLine product2 = new OrderLine();
        product2.setName("Product1");
        product2.setCode("001");
        product2.setQuantity(1);
        customer.addProduct(product2);

        assertEquals(1, customer.getOrderLines().size());
        assertEquals(1, customer.count);
        assertEquals(2, customer.getOrderLines().get(0).getQuantity());
    }

    @Test
    void testCalculateSum() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(100);
        product1.setQuantity(2);
        customer.addProduct(product1);

        assertEquals(200, customer.calculateSum(""));
    }

    @Test
    void testCalculateSumWithAvoidName() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(100);
        product1.setQuantity(2);
        customer.addProduct(product1);

        assertEquals(0, customer.calculateSum("Product1"));
    }

    @Test
    void testCalculateSumMultipleProducts() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(100);
        product1.setQuantity(2);
        customer.addProduct(product1);

        OrderLine product2 = new OrderLine();
        product2.setName("Product2");
        product2.setCode("002");
        product2.setPrice(50);
        product2.setQuantity(3);
        customer.addProduct(product2);

        assertEquals(350, customer.calculateSum(""));
    }

    @Test
    void testCalculateSumMultipleProductsWithAvoidName() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(100);
        product1.setQuantity(2);
        customer.addProduct(product1);

        OrderLine product2 = new OrderLine();
        product2.setName("Product2");
        product2.setCode("002");
        product2.setPrice(50);
        product2.setQuantity(3);
        customer.addProduct(product2);

        assertEquals(150, customer.calculateSum("Product1"));
    }

    @Test
    void testCalculateSumWithNegativePrice() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(-100);
        product1.setQuantity(2);
        customer.addProduct(product1);

        assertEquals(-200, customer.calculateSum(""));
    }

    @Test
    void testCalculateSumWithZeroQuantity() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(100);
        product1.setQuantity(0);
        customer.addProduct(product1);

        assertEquals(0, customer.calculateSum(""));
    }

    @Test
    void testCalculateSumWithNegativeQuantity() {
        OrderLine product1 = new OrderLine();
        product1.setName("Product1");
        product1.setCode("001");
        product1.setPrice(100);
        product1.setQuantity(-2);
        customer.addProduct(product1);

        assertEquals(-200, customer.calculateSum(""));
    }
}