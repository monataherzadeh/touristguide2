package com.example.touristguide2.service;

import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TouristServiceTest {
    @Mock
    private TouristRepository repository;

    @InjectMocks
    private TouristService service;

    @Test
    void updateAttraction_updatesAndReturnsUpdatedAttraction() {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setId(1); // antager at ID er nødvendigt for at hente den opdaterede attraction
        attraction.setPark("Joshua Tree Park");
        attraction.setInfo("A fantastic park in the desert");
        attraction.setTags(List.of("Hiking", "desert"));
        attraction.setLocation("WY");

        TouristAttraction updatedAttraction = new TouristAttraction();
        updatedAttraction.setId(1);
        updatedAttraction.setPark("Joshua Tree Park");
        updatedAttraction.setInfo("A fantastic park in the desert");
        updatedAttraction.setTags(List.of("Hiking","desert"));
        updatedAttraction.setLocation("WY");

        // Act
        doNothing().when(repository).updateAttractionWithID(attraction);
        when(repository.getAttractionById(1)).thenReturn(updatedAttraction);

        TouristAttraction result = service.updateAttraction(attraction);

        // Assert
        verify(repository).updateAttractionWithID(attraction);
        verify(repository).getAttractionById(1);
        assertEquals(updatedAttraction, result);
    }
}