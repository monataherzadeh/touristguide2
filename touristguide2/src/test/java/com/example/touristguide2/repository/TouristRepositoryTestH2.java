package com.example.touristguide2.repository;

import com.example.touristguide2.model.TouristAttraction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;


@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
class TouristRepositoryTestH2 {

    @Autowired
    private TouristRepository repository;

    @Test
    void readAll() {
        List<TouristAttraction> all = repository.getAllAttractionsRepo();

        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getPark()).isEqualTo("Joshua Tree Park");
        assertThat(all.get(1).getPark()).isEqualTo("Yellow Stone Nation Park");
    }

    @Test
    void insertAndReadBack() {
        repository.addAttraction(new TouristAttraction("Yellow Stone National Park", "Yellow Stone Park is abundant i wildlife", List.of("Desert", "Hiking"), "WY", 3));
        var carol = repository.getAttractionById(3);
        assertThat(carol).isNotNull();
        assertThat(carol.getPark()).isEqualTo("Yellow Stone National Park");
    }
}

