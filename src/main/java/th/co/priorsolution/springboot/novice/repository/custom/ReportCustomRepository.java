package th.co.priorsolution.springboot.novice.repository.custom;

import th.co.priorsolution.springboot.novice.model.nativesql.FilmByCustomerModel;

import java.sql.Connection;
import java.util.List;

public interface ReportCustomRepository {

    public Connection getConnection();

    public List<FilmByCustomerModel> getFilmByCustomerId(String customerId);
}
