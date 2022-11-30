package th.co.priorsolution.springboot.novice.component;

import org.springframework.stereotype.Component;
import th.co.priorsolution.springboot.novice.constant.AppConstant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static th.co.priorsolution.springboot.novice.constant.AppConstant.DD_MM_SS_H_M_S_FORMAT;

@Component
public class DateTimeUtilsComponent {

    final DateTimeFormatter DD_MM_YYY_H_M_S = DateTimeFormatter.ofPattern(DD_MM_SS_H_M_S_FORMAT);

    public LocalDateTime convertDateToLocalDateTime(Date date){
        LocalDateTime y = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return y;
    }

    public String convertLocalDateTimeToString(LocalDateTime localDateTime){
        return DD_MM_YYY_H_M_S.format(localDateTime);
    }

    public LocalDateTime convertStringToLocalDateTime(String dateStr){
        LocalDateTime x = LocalDateTime.from(DD_MM_YYY_H_M_S.parse(dateStr));
        return x;
    }
}
