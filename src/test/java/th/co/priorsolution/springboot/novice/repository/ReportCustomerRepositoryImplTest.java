package th.co.priorsolution.springboot.novice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import th.co.priorsolution.springboot.novice.model.nativesql.FilmByCustomerModel;
import th.co.priorsolution.springboot.novice.repository.custom.ReportCustomRepository;

import java.sql.Connection;
import java.util.List;

public class ReportCustomerRepositoryImplTest implements ReportCustomRepository {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public List<FilmByCustomerModel> getFilmByCustomerId(String customerId) {
        String json = "[\n  {\n    \"title\": \"MURDER ANTITRUST\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"JUGGLER HARDLY\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"DOGMA FAMILY\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"PILOT HOOSIERS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"INSIDER ARIZONA\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"COLOR PHILADELPHIA\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"ACADEMY DINOSAUR_SUPER_LONG_LENGHT_LENGHT_SUPER_LONG_LENGHT_LENGHT\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"EFFECT GLADIATOR\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"STORY SIDE\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"WORKING MICROCOSMOS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"CAROL TEXAS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"SEARCHERS WAIT\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"STRAIGHT HOURS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"LOVELY JINGLE\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"PICKUP DRIVING\",\n    \"release_year\": 2006,\n    \"store_branch\": \"47 MySakila Drive\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"TRUMAN CRAZY\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"INTERVIEW LIAISONS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"BETRAYED REAR\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"CAPER MOTIONS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"CALIFORNIA BIRDS\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"UNBREAKABLE KARATE\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"HEAVEN FREEDOM\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  },\n  {\n    \"title\": \"FOREVER CANDIDATE\",\n    \"release_year\": 2006,\n    \"store_branch\": \"28 MySQL Boulevard\",\n    \"store_postal_code\": \"\"\n  }\n]";
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        List<FilmByCustomerModel> filmByCustomerModels = null;
        try {
            filmByCustomerModels = mapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return filmByCustomerModels;
    }
}
