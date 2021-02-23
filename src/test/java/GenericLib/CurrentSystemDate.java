package GenericLib;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class contains the method to get the System's current date
 * @author zubairahmed
 *
 */
public class CurrentSystemDate {

	/**
	 * This method is used to fetch the System's current date
	 * @param dateFormat
	 * @return
	 */
	public String currentSystemDate(String dateFormat) {
		Date d=new Date();
		SimpleDateFormat sd=new SimpleDateFormat(dateFormat);
		String date = sd.format(d);
		return date;
	}
}
