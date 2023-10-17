package service;

import java.util.List;

/**
 * Represents a customer, with a list of order lines and a count of products.
 */
public class Customer {

    private List<OrderLine> orderLines;
    public int count;

    /**
     * Creates a new customer with the specified name and order lines.
     * Also updates the count of products.
     *
     * @param name       the name of the customer.
     * @param orderLines the list of order lines of the customer.
     */
    public Customer(String name, List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        updateCount();
    }

    /**
     * Adds a product to the customer's order lines.
     * If the product already exists in the order lines, its quantity is increased by 1.
     * The count of products is updated after a product is added.
     *
     * @param product the product to be added.
     */
    public void addProduct(OrderLine product) {
        if (product != null) {
            for (OrderLine existingProduct : orderLines) {
                if (existingProduct.getName().equals(product.getName()) &&
                        existingProduct.getCode().equals(product.getCode())) {
                    existingProduct.setQuantity(existingProduct.getQuantity() + 1);
                    updateCount();
                    return;
                }
            }
            orderLines.add(product);
            updateCount();
        }
    }

    /**
     * Calculates the sum of the prices of all order lines, excluding those with the specified name.
     *
     * @param avoidName the name of the order lines to be excluded from the calculation.
     * @return the total sum.
     */
    public int calculateSum(String avoidName) {
        int sum = 0;
        for (OrderLine orderLine : orderLines) {
            if (!orderLine.getName().equals(avoidName)) {
                sum += orderLine.getPrice() * orderLine.getQuantity();
            }
        }
        return sum;
    }

    /**
     * Returns the list of order lines of the customer.
     *
     * @return the list of order lines.
     */
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * Updates the count of products with the size of the order lines.
     */
    private void updateCount() {
        count = orderLines.size();
    }

}

/**
 * Represents an order line, with a name, code, quantity, and price.
 */
class OrderLine {
    private String name;
    private String code;
    private int quantity;
    private int price;

    /**
     * Returns the name of the order line.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the order line.
     *
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the quantity of the order line.
     *
     * @return the quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the order line.
     *
     * @param quantity the quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the price of the order line.
     *
     * @return the price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the order line.
     *
     * @param price the price to set.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the code of the order line.
     *
     * @return the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the order line.
     *
     * @param code the code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

}