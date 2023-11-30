package com.shashi.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.shashi.beans.ProductBean;

public class ProductServiceImplTest {

    private OrderServiceImpl orderDaoMock;
    private ProductServiceImpl productServiceImpl; // Assuming this is the class being tested

    @Before
    public void setUp() {
        // Initialize your class and mock dependencies
        orderDaoMock = Mockito.mock(OrderServiceImpl.class);
        
        // Initialize the class being tested
        productServiceImpl = new ProductServiceImpl(orderDaoMock);
    }

    @Test
    public void testGetMostSoldItemIds() {
        // Set up mock data
        List<ProductBean> mockProducts = new ArrayList<>();
        ProductBean product1 = new ProductBean("1", "Product1", null, null, 0, 0, null);
        ProductBean product2 = new ProductBean("2", "Product2", null, null, 0, 0, null);
        mockProducts.add(product1);
        mockProducts.add(product2);

        // Set up mock behavior for productDao.getAllProducts()
        Mockito.when(orderDaoMock.countSoldItem("1")).thenReturn(5); // Adjust as needed
        Mockito.when(orderDaoMock.countSoldItem("2")).thenReturn(7); // Adjust as needed

        // Execute the method
        String[] result = productServiceImpl.getMostSoldItemIds();

        // Assert the result
        assertEquals(1, result.length); // Adjust as needed
        assertEquals("2", result[0]); // Adjust as needed
    }
}
