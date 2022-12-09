//package th.co.priorsolution.springboot.novice.logging;
//
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import org.springframework.cloud.gcp.logging.StackdriverJsonLayout;
//import th.co.priorsolution.springboot.novice.logging.model.ComplexLog;
//import th.co.priorsolution.springboot.novice.logging.model.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class CustomStackDriverJson extends StackdriverJsonLayout {
//
//    @Override
//    protected Map<String, Object> toJsonMap(ILoggingEvent event) {
//
//        if (event.getArgumentArray() != null) {
//            List<String> customList = new ArrayList<>();
//            for (Object obj : event.getArgumentArray()) {
//                if(obj instanceof Log){
//                    customList.add(obj.toString());
//                }
//                if(obj instanceof ComplexLog){
//                    customList.add(obj.toString());
//                }
//            }
//            String toJoin = String.join(",", customList);
//            String json = "{" + toJoin + "}";
//            if (!"".equalsIgnoreCase(json.trim())) {
//                setCustomJson(json);
//            }
//        }
//        return super.toJsonMap(event);
//    }
//}
