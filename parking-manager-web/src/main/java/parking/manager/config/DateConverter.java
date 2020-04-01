package parking.manager.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: huang
 * Date: created in 2020/3/28 15:57
 * Description: 全局日期类型转换器
 * @author 24626
 */
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        return parseDate(s, "yyyy-MM-dd HH:mm:ss");
    }


    private Date parseDate(String source, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
