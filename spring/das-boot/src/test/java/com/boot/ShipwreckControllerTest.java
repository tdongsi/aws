package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

public class ShipwreckControllerTest {
	
	@InjectMocks
	private ShipwreckController sc;
	@Mock
	private ShipwreckRepository repo;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testShipwreckGet() {
		Shipwreck mock = new Shipwreck();
		mock.setId(1L);
		when(repo.findOne(1L)).thenReturn(mock);
		
		Shipwreck wreck = sc.get(1L);
		verify(repo).findOne(1L);
		
//		assertEquals(1L, wreck.getId().longValue());
		assertThat(wreck.getId(), is(1L));
	}

}
