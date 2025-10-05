package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class TouristRepositoryTest {

    @ExtendWith(MockitoExtension.class)

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TouristRepository repository;

    @Test
    void updateAttraction_executesExpectedSqlUpdate() {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setId(1);
        attraction.setName("Joshua Tree Park");
        attraction.setDescription("A fantastic park in the desert");
        attraction.setTags("Hiking");
        attraction.setLocation("WY");

        // define behavior: jdbcTemplate.update() returns number of affected rows
        when(jdbcTemplate.update(anyString(), anyString(), anyString(), anyString(), anyString(), anyInt())).thenReturn(1);
        //Note: note thata the first anyString() value is the SQL string as the first argument.

        repository.updateAttractionWithID(attraction);

        String expectedSql = "UPDATE Attraction SET Name = ?, Description = ?, Tags = ?, Location = ? WHERE id = ?";

        verify(jdbcTemplate).update(
                eq(expectedSql),
                eq(attraction.getName()),
                eq(attraction.getDescription()),
                eq(attraction.getTags()),
                eq(attraction.getLocation()),
                eq(attraction.getId())
        );
    }
    //NOtes for this test:
    //anyString() is 'a Mockito Argument Matcher'(say wut) that tells the mockito framework to accept any kind
    //of String value passed as arguemnts into this update() method call.

    //The jdbc dependency is the thing being mocked here.

    //This test tests whether the update

    //eq() is a mockito argument matcher, that checks if the value passed to the update() method actually is exactly the
    //same as the expected value(i.e. the TouristAttraction object defined in this test.
    // - the eq() method in the verify() method ensures e.g. that the updateAttractionWithID() didn't change the query String that is supposed
    //   to be generated.

    //Test:
    //This test, tests whether the method deleteAttraction() returns the given TouristAttraction that gets deleted by the
    //amount of rows specified to be returned by the mocked behavior.
    @Test
    void deleteAttraction_returnsAttractionWhenRowDeleted() {
        String name = "Joshua Tree Park";
        TouristAttraction attraction = new TouristAttraction(name, "Description", "Tags", "Location");

        when(repository.getAttractionByName(name)).thenReturn(attraction);
        when(jdbcTemplate.update(anyString(), eq(name))).thenReturn(1);

        TouristAttraction result = repository.deleteAttraction(name);

        String expectedSql = "DELETE FROM Attraction WHERE Name = ?";
        verify(jdbcTemplate).update(eq(expectedSql), eq(name));
        assertEquals(attraction, result);
    }

    //Test
    //This test, tests whether the method actually returns the value 'null' if no rows in the database are
    //found on the WHERE criteria from the sql String. The behavior of the jdbcTemplate.update() is mocked to return 0,
    //which would indicate that no rows had been found.
    @Test
    void deleteAttraction_returnsNullWhenNoRowDeleted() {
        String name = "Joshua Tree Park";
        String description = "A fantastic park in the desert";
        String tags = "Hiking";
        String location = "WY";

        TouristAttraction attraction = new TouristAttraction(name, description, tags, location);

        when(repository.getAttractionByName(name)).thenReturn(attraction);
        when(jdbcTemplate.update(anyString(), eq(name))).thenReturn(0);

        TouristAttraction result = repository.deleteAttraction(name);

        assertNull(result);
    }

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private RowMapper<TouristAttraction> rowMapper;

    @Test
    void getAllAttractionsRepo_returnsListOfAttractions() {
        //Sample TouristAttraction objects defined to be in the List that the method is mocked to return.
        TouristAttraction attraction1 = new TouristAttraction(
                "Joshua Tree Park",
                "A fantastic park in the desert",
                "Hiking",
                "WY"
        );

        TouristAttraction attraction2 = new TouristAttraction(
                "Yellowstone",
                "First National Park in the US",
                "Wildlife",
                "WY"
        );

        List<TouristAttraction> list = List.of(attraction1, attraction2);

        // Mock the jdbcTemplate.query to return the mock list
        when(jdbcTemplate.query(anyString(), eq(rowMapper))).thenReturn(list);

        // Call the repository method
        List<TouristAttraction> result = repository.getAllAttractionsRepo();

        verify(jdbcTemplate).query(eq("SELECT * FROM Attraction"), eq(rowMapper));

        assertEquals(list, result);
    }
}



