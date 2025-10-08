package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TouristRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TouristRepository repository;

    @Test
    void updateAttraction_executesExpectedSqlUpdate() {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setId(1);
        attraction.setPark("Joshua Tree Park");
        attraction.setInfo("A fantastic park in the desert");
        attraction.setTags(List.of("Hiking"));
        attraction.setLocation("WY");

        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(1);

        repository.updateAttractionWithID(attraction);

        String expectedSql = "UPDATE attraction SET park = ?, info = ?, location = ? WHERE attractionID = ?";

        verify(jdbcTemplate).update(
                eq(expectedSql),
                eq(attraction.getPark()),
                eq(attraction.getInfo()),
                eq(attraction.getLocation()),
                eq(attraction.getId())
        );
    }
}