package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TouristAttraction> rowMapper = (rs, rowNum) -> {
        TouristAttraction attraction = new TouristAttraction();
        attraction.setName(rs.getString("Name"));
        attraction.setDescription(rs.getString("Description"));
        attraction.setTags(rs.getString("Tag"));
        attraction.setLocation(rs.getString("Location"));
        return attraction;
    };
    //The TouristRepository constructor has jdbc template assigned, since there is no need to create an instance of jdbcTemplate in the repository class, but is instead injected from outside the class by using Spring which handles the injection.
    //This is constructor injection.
    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
            String sql = "INSERT INTO Attraction (Name, Description, Tag, Location) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql,attraction.getName(), attraction.getDescription(), attraction.getTags(), attraction.getLocation());
            return attraction;
    }

    public List<TouristAttraction> getAllAttractionsRepo() {
        List<TouristAttraction> attractionList = jdbcTemplate.query("SELECT * FROM Attraction", rowMapper);
        return attractionList;
    }

    public TouristAttraction getAttractionByName(String name) {
        String sql = "SELECT * FROM Attraction WHERE Name = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, name);
    }


    //update attraction and return the attraction with renewed data for its fields
    public void updateAttraction(String name, String description, String tags, String location) {
        String sql = "UPDATE Attraction SET Name = ? WHERE Name = ?";
        jdbcTemplate.update(sql, name);
    }
    //Alternative version of updateAttraction() method where just the object is passed into the method signature, and the row to be
    //updated is identified by its id.
    public void updateAttractionWithID(TouristAttraction attraction) {
        String sql = "UPDATE Attraction SET Name = ?, Description = ?, Tags = ?, Location = ? WHERE id = ?";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(),
                attraction.getTags(), attraction.getLocation(), attraction.getId());
    }



    //name kommer fra UI/fra view, med controller der modtager dette name værdi.
    public TouristAttraction deleteAttraction(String name) {
        TouristAttraction attractionToDelete = getAttractionByName(name);
        String sql = "DELETE FROM Attraction WHERE Name = ?";
        int rowsAffected = jdbcTemplate.update(sql, name);

        if (rowsAffected > 0) {
            return attractionToDelete;
        } else {
            return null;
        }
    }



}
