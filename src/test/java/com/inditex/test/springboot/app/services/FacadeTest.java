package com.inditex.test.springboot.app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditex.test.springboot.app.TestData;
import com.inditex.test.springboot.app.data.ProductRate;
import com.inditex.test.springboot.app.data.RateSelection;

@ExtendWith(MockitoExtension.class)
class FacadeTest {

	@Mock
	ZaraProductRateService service;

	@InjectMocks
	FacadeImpl facade;

	@Test
	@DisplayName("petición a las 16:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at16Oclock() {

		String date = "14, 06, 2020";
		String time = "16:00";
		Long productId = 35455L;
		Long brandId = 1L;
		
		
		when(service.findMostPriorityPriceBySelection(any(RateSelection.class))).thenReturn(TestData.PRODUCT_RATE_1);
			

		ProductRate productRate = facade.findMostPriorityPriceBySelection(date, time, productId, brandId);
		assertNotNull(productRate);
		

		assertEquals(TestData.PRODUCT_RATE_1.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_1.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_1.getProductId(), productRate.getProductId());
	}
	
	@Test
	@DisplayName("petición a las 21:00 del día 14 del producto 35455 para la brand 1")
	void checkProductRatesForDate14at21Oclock() {


		when(service.findMostPriorityPriceBySelection(any(RateSelection.class))).thenReturn(TestData.PRODUCT_RATE_2);

		ProductRate productRate = facade.findMostPriorityPriceBySelection("14-06-2020", "21:00", 35455L, 1L);
		
		assertEquals(TestData.PRODUCT_RATE_2.getPrice(), productRate.getPrice());
		assertEquals(TestData.PRODUCT_RATE_2.getPriceList(), productRate.getPriceList());
		assertEquals(TestData.PRODUCT_RATE_2.getProductId(), productRate.getProductId());
	}


}
