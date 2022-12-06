package th.co.priorsolution.springboot.novice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import th.co.priorsolution.springboot.novice.model.ResponseModel;
import th.co.priorsolution.springboot.novice.model.nativesql.FilmByCustomerModel;
import th.co.priorsolution.springboot.novice.repository.custom.ReportCustomRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class JasperGeneratorService {


    @Value("${app.jasperFile}")
    private String jasperFile;
    @Value("${app.jasperFolder}")
    private String jasperFolder;

    private ReportCustomRepository reportCustomRepository;

    public JasperGeneratorService(ReportCustomRepository reportCustomRepository) {
        this.reportCustomRepository = reportCustomRepository;
    }

    public void getPdf(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        log.info("getPdf");
        try{
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("Content-Disposition"
                    , "attachment; filename=" + "normal"+ new Date().getTime()+".pdf");
             OutputStream outputStream = httpServletResponse.getOutputStream();
             String jasperFile = this.jasperFolder+this.jasperFile;

            Map<String, Object> parameters = new HashMap();
            parameters.put("customer_id", httpServletRequest.getParameter("customerId"));
            parameters.put("jasper_folder", this.jasperFolder);

             outputStream.write(this.generateCustomerReport(jasperFile, parameters));
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

    public void getCsv(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        log.info("getCsv");
        try{
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("Content-Disposition"
                    , "attachment; filename=" + "csv"+ new Date().getTime()+".csv");
            OutputStream outputStream = httpServletResponse.getOutputStream();

            byte[] csv = this.generateCustomerReportCsv(httpServletRequest.getParameter("customerId"));

            outputStream.write(csv);
            outputStream.flush();

        } catch (Exception e) {
            log.info("getCsv error {}",e.getMessage());

            ResponseModel<Void> result = new ResponseModel<>();
            result.setStatus(500);
            result.setDescription("getCsv error "+e.getMessage());
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

    public void getExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        log.info("getExcel");
        try{
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("Content-Disposition"
                    , "attachment; filename=" + "excel"+ new Date().getTime()+".xlsx");
            OutputStream outputStream = httpServletResponse.getOutputStream();

            Workbook wb = this.generateCustomerReportExcel(httpServletRequest.getParameter("customerId"));
            wb.write(outputStream);
            outputStream.flush();

        } catch (Exception e) {
            log.info("getExcel error {}",e.getMessage());

            ResponseModel<Void> result = new ResponseModel<>();
            result.setStatus(500);
            result.setDescription("getExcel error "+e.getMessage());
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

    public ResponseModel<Void> readCsvFile(MultipartFile file, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        ResponseModel<Void> result = new ResponseModel<>();
        result.setStatus(200);
        result.setDescription("OK");

        log.info("readCsvFile");
        try{

            InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int row = 0;
            while (bufferedReader.ready()){
                int col = 1;
                String lineData = bufferedReader.readLine();
                log.info("line data {}", lineData);
                if(row >0){
                    String[] lineDataArray = lineData.split("\\|");
                    FilmByCustomerModel x = new FilmByCustomerModel();
                    for (int i = 0; i < lineDataArray.length; i++) {
                        if(i == 1){
                            x.setTitle(lineDataArray[i]);
                        }
                        if(i == 2){
                            x.setReleaseYear(Long.parseLong(lineDataArray[i]));
                        }
                        if(i == 3){
                            x.setStoreBranch(lineDataArray[i]);
                        }
                        if(i == 4){
                            x.setStorePostalCode(lineDataArray[i]);
                        }
                    }

                }
                row++;
            }

        } catch (Exception e) {
            log.info("readCsvFile error {}",e.getMessage());
            result.setStatus(500);
            result.setDescription("readCsvFile error "+e.getMessage());

        }
        return result;
    }

    private Workbook generateCustomerReportExcel(String customerId){

        Workbook wb = new HSSFWorkbook();

       Sheet sheet1 = wb.createSheet("sheet1");

        int row  = 5;
        Row headerRow = sheet1.createRow(row++);
        int headerCol = 0;
        Cell h1 = headerRow.createCell(headerCol++);
        h1.setCellValue("no");

        Cell h2 = headerRow.createCell(headerCol++);
        h2.setCellValue("title");

        Cell h3 = headerRow.createCell(headerCol++);
        h3.setCellValue("release year");

        Cell h4 = headerRow.createCell(headerCol++);
        h4.setCellValue("branch");

        Cell h5 = headerRow.createCell(headerCol++);
        h5.setCellValue("branch postal");

        List<FilmByCustomerModel> datas = this.reportCustomRepository.getFilmByCustomerId(customerId);
        for (int i = 0; i < datas.size(); i++) {
            int dataCol = 0;
            int rownum = i+1;
            Row dataRow = sheet1.createRow(row++);
            Cell no = dataRow.createCell(dataCol++);
            no.setCellValue(rownum);

            Cell title = dataRow.createCell(dataCol++);
            title.setCellValue(datas.get(i).getTitle());

            Cell releaseYear = dataRow.createCell(dataCol++);
            releaseYear.setCellValue(datas.get(i).getReleaseYear());

            Cell branch = dataRow.createCell(dataCol++);
            branch.setCellValue(datas.get(i).getStoreBranch());

            Cell branchPostal = dataRow.createCell(dataCol++);
            branchPostal.setCellValue(datas.get(i).getStorePostalCode());
        }

        return wb;
    }

    private byte[] generateCustomerReportCsv(String customerId){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s|%s|%s|%s|%s","no","title","release year", "branch", "branch postal")).append("\r\n");

        List<FilmByCustomerModel> datas = this.reportCustomRepository.getFilmByCustomerId(customerId);

        for (int i = 0; i < datas.size(); i++) {
            int rownum = i+1;

            sb.append(String.format("%d|%s|%d|%s|%s",rownum
                    , datas.get(i).getTitle()
                    ,datas.get(i).getReleaseYear()
                    , datas.get(i).getStoreBranch()
                    , datas.get(i).getStorePostalCode()))
                    .append("\r\n");
        }


        return sb.toString().getBytes();
    }

    private byte[] generateCustomerReport(String jasperFile, Map<String, Object> parameters) throws FileNotFoundException {

        Connection connection = this.reportCustomRepository.getConnection();
        byte[] r = new byte[1024];
        if(null != connection) {
            Instant start = Instant.now();
            log.info("start time ");

            FileInputStream fis = null;
            InputStream inputStream = null;
            JasperPrint jasperPrint = null;
            try {
                fis = new FileInputStream(jasperFile);
                inputStream = fis;
                jasperPrint = JasperFillManager.fillReport(inputStream, parameters, connection);
                r = JasperExportManager.exportReportToPdf(jasperPrint);
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start, finish).toMillis();
                log.info("total time {}",timeElapsed);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw e;
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
