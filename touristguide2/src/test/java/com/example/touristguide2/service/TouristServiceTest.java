package com.example.touristguide2.service;

import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class TouristServiceTest {

    @ExtendWith(MockitoExtension.class) // Tells JUnit to enable Mockito

    @Mock
    private TouristRepository repository;

    @InjectMocks
    private TouristService service;

    //This test, tests wether the method returns a TouristAttraction object.
    @Test
    void updateAttraction_updatesAndReturnsUpdatedAttraction() {
        //Simple test style arrangement of the fields in a simulatd object.
        String name = "Joshua Tree Park";
        String description = "A fantastic park in the desert";
        String tags = "Hiking";
        String location = "WY";

        TouristAttraction updatedAttraction = new TouristAttraction(name, description, tags, location);

        //The Mock's behavior defined below:
        doNothing().when(repository).updateAttraction(name, description, tags, location);
        when(repository.getAttractionByName(name)).thenReturn(updatedAttraction);

        //The result of the performance of the method in question:
        TouristAttraction result = service.updateAttraction(name, description, tags, location);

        //
        verify(repository).updateAttraction(name, description, tags, location);
        verify(repository).getAttractionByName(name);

        assertEquals(updatedAttraction, result);

    }
}