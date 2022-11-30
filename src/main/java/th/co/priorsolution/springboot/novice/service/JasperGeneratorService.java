package th.co.priorsolution.springboot.novice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.repository.custom.ReportCustomRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@Slf4j
public class JasperGeneratorService {


    @Value("${app.jasperFile}")
    private String jasperFile;

    private ReportCustomRepository reportCustomRepository;

    public JasperGeneratorService(ReportCustomRepository reportCustomRepository) {
        this.reportCustomRepository = reportCustomRepository;
    }

    public void getPdf(HttpServletResponse httpServletResponse){

        log.info("getPdf");
        try{
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("Content-Disposition"
                    , "attachment; filename=" + "normal"+ new Date().getTime()+".pdf");
             OutputStream outputStream = httpServletResponse.getOutputStream();
             outputStream.write(this.normalGenerate(this.jasperFile));
             outputStream.flush();

        } catch (Exception e) {
            log.info("getPdf error {}",e.getMessage());

            ResponseModel<Void> result = new ResponseModel<>();
            result.setStatus(500);
            result.setDescription("getPdf error "+e.getMessage());
            ObjectMapper mapper = new ObjectMapper();
            httpServletResponse.setHeader("Content-Disposition"
                    , "inline");
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            OutputStream outputStream = null;
            try {
                outputStream = httpServletResponse.getOutputStream();
                outputStream.write(mapper.writeValueAsBytes(result));
                outputStream.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

    }

    private byte[] normalGenerate(String jasperFile) throws FileNotFoundException {

        RestTemplate restTemplate = new RestTemplate();

        Connection connection = this.reportCustomRepository.getConnection();
        byte[] r = new byte[1024];
        if(null != connection) {
            Instant start = Instant.now();
            log.info("start time ");
            Map<String, Object> parameters = new HashMap();

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(jasperFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw e;
            }
            InputStream inputStream = fis;
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(inputStream, parameters, connection);
            } catch (JRException e) {
                e.printStackTrace();
            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                r = JasperExportManager.exportReportToPdf(jasperPrint);
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start, finish).toMillis();
                log.info("total time {}",timeElapsed);
            } catch (JRException e) {
                e.printStackTrace();
            }
        }

        return r;
    }

    public byte[] virtualizerGenerate() {

        Connection connection = this.reportCustomRepository.getConnection();

        byte[] r = new byte[1024];
        if(null != connection) {
            Instant start = Instant.now();
            log.info("start time ");
            // creating the virtualizer
            JRFileVirtualizer virtualizer = new JRFileVirtualizer(2, "/home/pongpat/Documents/data/out");
            //Preparing parameters
            Map<String, Object> parameters = new HashMap();
            parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

            FileInputStream fis = null;
            try {
                fis = new FileInputStream("/home/pongpat/JaspersoftWorkspace/huge-record/jasper-huge.jasper");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            InputStream inputStream = fis;
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(inputStream, parameters, connection);
            } catch (JRException e) {
                e.printStackTrace();
            } finally {
                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                r = JasperExportManager.exportReportToPdf(jasperPrint);
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start, finish).toMillis();
                log.info("total time {}",timeElapsed);
            } catch (JRException e) {
                e.printStackTrace();
            }
        }

        return r;
    }
}
