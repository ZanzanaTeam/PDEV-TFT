package formatDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FormatDate {

	public static Date ToUtilDate(LocalDate dateLocal) {
		
		Instant instant = Instant.from(dateLocal.atStartOfDay(ZoneId.systemDefault()));
		Date date = Date.from(instant);
		return date ; 

	}
	public static LocalDate asLocalDate(Date date) {
	    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	    }
}
