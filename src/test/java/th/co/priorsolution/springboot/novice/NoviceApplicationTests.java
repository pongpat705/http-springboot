package th.co.priorsolution.springboot.novice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import th.co.priorsolution.springboot.novice.model.nativesql.FilmByCustomerModel;
import th.co.priorsolution.springboot.novice.repository.ReportCustomerRepositoryImplTest;
import th.co.priorsolution.springboot.novice.repository.custom.ReportCustomRepository;
import th.co.priorsolution.springboot.novice.service.JasperGeneratorService;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;


class NoviceApplicationTests {



	@Test
	public void testReadCsvFromInputStreamExpectIndex16Correct() throws IOException {
		JasperGeneratorService jasperGeneratorService = new JasperGeneratorService(null);

		File testFile = new File(getClass().getResource("/csv1670298697341.csv").getFile());
		FileInputStream fileInputStream = new FileInputStream(testFile);

		List<FilmByCustomerModel> result = jasperGeneratorService.readCsvFromInputStream(fileInputStream);
		Assertions.assertTrue(result.get(16).getTitle().equals("INTERVIEW LIAISONS"));
	}

	public void testGenerateCsv() throws IOException {

		ReportCustomRepository reportCustomRepository = new ReportCustomerRepositoryImplTest();
		JasperGeneratorService jasperGeneratorService = new JasperGeneratorService(reportCustomRepository);

		String filename = "csv"+ LocalDateTime.now().getNano()+".csv";
		File file = new File("/home/pongpat/Documents/Fluke-Machine/downloads/recruit-prior/trainning/06122022/"+filename);
		FileOutputStream fileOutputStream = new FileOutputStream(file);


		jasperGeneratorService.generateCustomerReportCsv("444", fileOutputStream);
	}

}
