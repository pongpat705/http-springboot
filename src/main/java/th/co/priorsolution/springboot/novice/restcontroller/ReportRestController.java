package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.service.JasperGeneratorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {

    private JasperGeneratorService jasperGeneratorService;

    public ReportRestController(JasperGeneratorService jasperGeneratorService) {
        this.jasperGeneratorService = jasperGeneratorService;
    }

    @GetMapping("/generate/normal/pdf")
    public void getNormalPdf(@RequestParam("customerId") String customerId
            , HttpServletRequest request, HttpServletResponse response) {
        this.jasperGeneratorService.getPdf(request, response);
    }

    @GetMapping("/generate/normal/csv")
    public void getNormalCsv(HttpServletRequest request, HttpServletResponse response) {
        this.jasperGeneratorService.getCsv(request, response);
    }

    @GetMapping("/generate/normal/excel")
    public void getNormalExcel(HttpServletRequest request, HttpServletResponse response) {
        this.jasperGeneratorService.getExcel(request, response);
    }

    @PostMapping("/upload/csv")
    public ResponseModel<Void> uploadCsvFile(@RequestParam("file") MultipartFile file
            , HttpServletRequest request, HttpServletResponse response){
        return this.jasperGeneratorService.readCsvFile(file, request, response);
    }

}
