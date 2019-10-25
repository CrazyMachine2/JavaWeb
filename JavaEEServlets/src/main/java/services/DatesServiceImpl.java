package services;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesServiceImpl implements DatesService {
    @Override
    public String getNowFormatted() {
        SimpleDateFormat formatted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatted.format(date);
    }
}
