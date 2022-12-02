package th.co.priorsolution.springboot.novice.model.nativesql;

import lombok.Data;

@Data
public class FilmByCustomerModel {
    private String title;
    private long releaseYear;
    private String storeBranch;
    private String storePostalCode;

}
