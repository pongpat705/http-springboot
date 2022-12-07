package th.co.priorsolution.springboot.novice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import th.co.priorsolution.springboot.novice.component.EmployeeModelTransformComponent;
import th.co.priorsolution.springboot.novice.component.EmployeeValidatorComponent;
import th.co.priorsolution.springboot.novice.model.EmployeeModel;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.FilmByCustomerModel;
import th.co.priorsolution.springboot.novice.repository.EmployeeRepository;
import th.co.priorsolution.springboot.novice.repository.EmployeeRepositoryTestImpl;
import th.co.priorsolution.springboot.novice.service.EmployeeService;
import th.co.priorsolution.springboot.novice.service.JasperGeneratorService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

}
