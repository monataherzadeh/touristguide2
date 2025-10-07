package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        attraction.setId(rs.getInt("AttractionID PK"));
        attraction.setName(rs.getString("Name"));
        attraction.setDescription(rs.getString("Description"));
        attraction.setLocation(rs.getString("Location"));

        List<String> tags = new ArrayList<>();
        String tag = rs.getString("Tag PK");
        if (tag != null && !tag.isEmpty()) {
            tags.add(tag);
        }
        attraction.setTags(tags);

        return attraction;

    };

    //The TouristRepository constructor has jdbc template assigned, since there is no need to create an instance of jdbcTemplate in the repository class, but is instead injected from outside the class by using Spring which handles the injection.
    //This is constructor injection.
    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //New method -
    public TouristAttraction addAttraction(TouristAttraction attraction) {

        //This contains a query that inserts the fields: name, description and location of the TouristAttraction object
        //int the Attraction table.
        String insertAttractionSql = "INSERT INTO TouristAttraction (Name, Description, Location) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertAttractionSql, attraction.getName(), attraction.getDescription(), attraction.getLocation());

        //Retrieves the ID of the newly inserted Attraction object
        Integer attractionId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        attraction.setId(attractionId);

        //This checks whether there are any tags in the TouristAttraction object, and if true, they are inserted into the
        //tags table.
        if (attraction.getTags() != null && !attraction.getTags().isEmpty()) {
            String insertTagSql = "INSERT INTO Tags (AttractionID, Tag) VALUES (?, ?)";
            for (String tag : attraction.getTags()) {
                jdbcTemplate.update(insertTagSql, attractionId, tag);
            }
        }

        return attraction;
    }

    //old method
    public List<TouristAttraction> getAllAttractionsRepoOld() {
        List<TouristAttraction> attractionList = jdbcTemplate.query("SELECT * FROM Attraction", rowMapper);
        return attractionList;
    }

    //new method
    public List<TouristAttraction> getAllAttractionsRepo() {
        String sql = "SELECT t.AttractionID, t.Name, t.Description, t.Location, tg.Tag " +
                "FROM TouristAttraction t " +
                "LEFT JOIN Tags tg ON t.AttractionID = tg.AttractionID " +
                "ORDER BY t.AttractionID";

        // Query all rows with rowMapper
        List<TouristAttraction> rows = jdbcTemplate.query(sql, rowMapper);

        // Merge rows into one object per attraction
        List<TouristAttraction> attractionList = new ArrayList<>();
        TouristAttraction current = null;
        Integer lastId = null;

        for (TouristAttraction ta : rows) {
            int currentId = ta.getId();

            if (current == null || currentId != lastId) {
                attractionList.add(ta);
                current = ta;
            } else {
                current.getTags().addAll(ta.getTags());
            }
            lastId = currentId;
        }
        return attractionList;
    }

    //*ERROR(?): these queries only take data from one table.
    public TouristAttraction getAttractionByName(String name) {
        String sql = "SELECT * FROM Attraction WHERE Name = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, name);
    }

    //Old method:
    public TouristAttraction getAttractionByIdOld(int id) {
        String sql = "SELECT * FROM Attraction WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    //New method:
    public TouristAttraction getAttractionById(int id) {
        String sql = "SELECT t.AttractionID, t.Name, t.Description, t.Location, tg.Tag " +
                "FROM TouristAttraction t " +
                "LEFT JOIN Tags tg ON t.AttractionID = tg.AttractionID " +
                "WHERE t.AttractionID = ? " +
                "ORDER BY t.AttractionID";

        // Query all rows with rowMapper
        List<TouristAttraction> rows = jdbcTemplate.query(sql, rowMapper, id);

        // Merge rows into a single TouristAttraction object
        TouristAttraction attraction = null;
        for (TouristAttraction ta : rows) {
            if (attraction == null) {
                attraction = ta;
            } else {
                attraction.getTags().addAll(ta.getTags());
            }
        }
        return attraction;
    }

    //update attraction and return the attraction with renewed data for its fields
    public void updateAttraction(String name, String description, String tags, String location) {
        String sql = "UPDATE Attraction SET Name = ? WHERE Name = ?";
        jdbcTemplate.update(sql, name);
    }

    //Alternative version of updateAttraction() method where just the object is passed into the method signature, and the row to be
    //updated is identified by its id.
    //Old method:
    public void updateAttractionWithIDOld(TouristAttraction attraction) {
        String sql = "UPDATE Attraction SET Name = ?, Description = ?, Tags = ?, Location = ? WHERE id = ?";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(),
                attraction.getTags(), attraction.getLocation(), attraction.getId());
    }

    //New method:
    public void updateAttractionWithID(TouristAttraction attraction) {
        // 1. Update main attraction info
        String sql = "UPDATE TouristAttraction SET Name = ?, Description = ?, Location = ? WHERE AttractionID = ?";
        jdbcTemplate.update(sql, attraction.getName(), attraction.getDescription(),
                attraction.getLocation(), attraction.getId());

        // 2. Remove existing tags
        String deleteTagsSql = "DELETE FROM Tags WHERE AttractionID = ?";
        jdbcTemplate.update(deleteTagsSql, attraction.getId());

        // 3. Insert updated tags
        if (attraction.getTags() != null && !attraction.getTags().isEmpty()) {
            String insertTagSql = "INSERT INTO Tags (AttractionID, Tag) VALUES (?, ?)";
            for (String tag : attraction.getTags()) {
                jdbcTemplate.update(insertTagSql, attraction.getId(), tag);
            }
        }
    }

    //name kommer fra UI/fra view, med controller der modtager dette name værdi.
    //old method:
    public TouristAttraction deleteAttractionOld(String name) {
        TouristAttraction attractionToDelete = getAttractionByName(name);
        String sql = "DELETE FROM Attraction WHERE Name = ?";
        int rowsAffected = jdbcTemplate.update(sql, name);

        if (rowsAffected > 0) {
            return attractionToDelete;
        } else {
            return null;
        }
    }

    //new method:
    public TouristAttraction deleteAttraction(String name) {

        TouristAttraction attractionToDelete = getAttractionByName(name);

        if (attractionToDelete == null) {
            return null;
        }
        String deleteTagsSql = "DELETE FROM Tags WHERE AttractionID = ?";
        jdbcTemplate.update(deleteTagsSql, attractionToDelete.getId());

        // 2. Delete the attraction itself
        String deleteAttractionSql = "DELETE FROM TouristAttraction WHERE Name = ?";
        int rowsAffected = jdbcTemplate.update(deleteAttractionSql, name);

        // Return the deleted object if it existed
        if (rowsAffected > 0) {
            return attractionToDelete;
        } else {
            return null;
        }
    }
}




