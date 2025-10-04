package com.example.touristguide2;

import com.example.touristguide2.model.Tags;
import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import com.example.touristguide2.service.TouristService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//Integration testing performed here.
//@SpringBootTest calls the whole SpringBoot context, which might be unecessary elsewhere.

@ExtendWith(MockitoExtension.class) // Tells JUnit to enable Mockito
class TouristServiceTest {

	@Mock
	private TouristRepository repository;

	@InjectMocks
	private TouristService service;

	@Test
	//This test does the following:
	//Defines the behavior for the mock, calls the addAttraction() method, verifies that the method actually calls the
	//the repository, and that the service class method here, actually returns the same object as defined right below.
	void addAttraction_callsRepository() {
		TouristAttraction attraction = new TouristAttraction("Joshua Tree Park", "A fantastic park in the desert", List.of(Tags.HIKING, Tags.DESERT), "WY");

		when(repository.addAttraction(attraction)).thenReturn(attraction);

		TouristAttraction result = service.addAttraction(attraction);

		verify(repository).addAttraction(attraction);

		assertEquals(attraction, result);
	}




}



@SpringBootTest
class Touristguide2ApplicationTests {

	@Test
	void contextLoads() {
	}

}
