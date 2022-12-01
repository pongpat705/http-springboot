package th.co.priorsolution.springboot.novice.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import th.co.priorsolution.springboot.novice.service.JasperGeneratorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/generate")
public class ReportRestController {

    private JasperGeneratorService jasperGeneratorService;

    public ReportRestController(JasperGeneratorService jasperGeneratorService) {
        this.jasperGeneratorService = jasperGeneratorService;
    }

    @GetMapping("/normal/pdf")
    public void getNormalPdf(@RequestParam("customerId") String customerId, HttpServletRequest request, HttpServletResponse response) {
        this.jasperGeneratorService.getPdf(request, response);
    }
}
