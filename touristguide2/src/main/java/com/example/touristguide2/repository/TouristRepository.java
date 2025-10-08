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
        attraction.setId(rs.getInt("attractionID"));
        attraction.setPark(rs.getString("park"));
        attraction.setInfo(rs.getString("info"));
        attraction.setLocation(rs.getString("location"));

        List<String> tags = new ArrayList<>();
        String tag = rs.getString("tag");
        if (tag != null && !tag.isEmpty()) {
            tags.add(tag);
        }
        attraction.setTags(tags);
        return attraction;
    };

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        String insertAttractionSql = "INSERT INTO attraction (park, info, location) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertAttractionSql, attraction.getPark(), attraction.getInfo(), attraction.getLocation());

        Integer attractionId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        attraction.setId(attractionId);

        if (attraction.getTags() != null && !attraction.getTags().isEmpty()) {
            String insertTagSql = "INSERT INTO tags (attractionID, tag) VALUES (?, ?)";
            for (String tag : attraction.getTags()) {
                jdbcTemplate.update(insertTagSql, attractionId, tag);
            }
        }

        return attraction;
    }

    public List<TouristAttraction> getAllAttractionsRepo() {
        String sql = "SELECT a.attractionID, a.park, a.info, a.location, t.tag " +
                "FROM attraction a " +
                "LEFT JOIN tags t ON a.attractionID = t.attractionID " +
                "ORDER BY a.attractionID";

        List<TouristAttraction> rows = jdbcTemplate.query(sql, rowMapper);
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

    public TouristAttraction getAttractionByName(String park) {
        String sql = "SELECT a.attractionID, a.park, a.info, a.location, t.tag " +
                "FROM attraction a " +
                "LEFT JOIN tags t ON a.attractionID = t.attractionID " +
                "WHERE a.park = ?";

        List<TouristAttraction> rows = jdbcTemplate.query(sql, rowMapper, park);

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

    public TouristAttraction getAttractionById(int id) {
        String sql = "SELECT a.attractionID, a.park, a.info, a.location, t.tag " +
                "FROM attraction a " +
                "LEFT JOIN tags t ON a.attractionID = t.attractionID " +
                "WHERE a.attractionID = ? " +
                "ORDER BY a.attractionID";

        List<TouristAttraction> rows = jdbcTemplate.query(sql, rowMapper, id);

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

    public void updateAttractionWithID(TouristAttraction attraction) {
        String sql = "UPDATE attraction SET park = ?, info = ?, location = ? WHERE attractionID = ?";
        jdbcTemplate.update(sql, attraction.getPark(), attraction.getInfo(), attraction.getLocation(), attraction.getId());

        String deleteTagsSql = "DELETE FROM tags WHERE attractionID = ?";
        jdbcTemplate.update(deleteTagsSql, attraction.getId());

        if (attraction.getTags() != null && !attraction.getTags().isEmpty()) {
            String insertTagSql = "INSERT INTO tags (attractionID, tag) VALUES (?, ?)";
            for (String tag : attraction.getTags()) {
                jdbcTemplate.update(insertTagSql, attraction.getId(), tag);
            }
        }
    }

    public TouristAttraction deleteAttraction(String park) {
        TouristAttraction attractionToDelete = getAttractionByName(park);

        if (attractionToDelete == null) {
            return null;
        }

        String deleteTagsSql = "DELETE FROM tags WHERE attractionID = ?";
        jdbcTemplate.update(deleteTagsSql, attractionToDelete.getId());

        String deleteAttractionSql = "DELETE FROM attraction WHERE park = ?";
        int rowsAffected = jdbcTemplate.update(deleteAttractionSql, park);

        return rowsAffected > 0 ? attractionToDelete : null;
    }
}
