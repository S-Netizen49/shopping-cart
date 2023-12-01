package com.shashi.service.impl;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import com.shashi.beans.ProductBean;
import org.junit.jupiter.api.Test;
class OrderServiceImplTest
{
	@Test
	void testGetMostSellingItems()
	{
		OrderServiceImpl osi = new OrderServiceImpl();
        List<ProductBean> l = osi.getMostSellingItems();
        assertTrue(l.size() > 0);
	}
	@Test
	void testGetLeastSellingItems()
	{
		OrderServiceImpl osi = new OrderServiceImpl();
        List<ProductBean> l = osi.getMostSellingItems();
        assertTrue(l.size() > 0);
	}


}
