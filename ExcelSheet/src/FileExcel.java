import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class FileExcel {

	public FileExcel() {}

	@SuppressWarnings("deprecation")
	public static ArrayList<Student> list1() throws IOException 
	{
		ArrayList<Student> list = new ArrayList<Student>();
		
		try 
		{
			FileInputStream fis = new FileInputStream(new File("/Users/abhishekkumar/Desktop/Workbook.xls"));
			@SuppressWarnings("resource")
			HSSFWorkbook book = new HSSFWorkbook(fis);
			HSSFSheet sheet = book.getSheetAt(0);
			FormulaEvaluator formula = book.getCreationHelper().createFormulaEvaluator();
			
			for(Row row:sheet)
			{
				Student stu = new Student();
				for(Cell cell:row)
				{
					switch(formula.evaluateInCell(cell).getCellType())
					{
					case Cell.CELL_TYPE_STRING:
						if(cell.getColumnIndex()==0)
						{
						System.out.print(cell.getStringCellValue()+ "\t"+"\t");
						stu.setName(cell.getStringCellValue());
						}
						else if (cell.getColumnIndex()==3)
						{
						System.out.print(cell.getStringCellValue()+ "\t"+"\t");
						stu.setNotes(cell.getStringCellValue());
						}
						else if(cell.getColumnIndex()==4)
						{
							System.out.println(cell.getStringCellValue()+"\t"+"\t");
						    stu.setEmail(cell.getStringCellValue());	
						}
						break;
						
					case Cell.CELL_TYPE_NUMERIC:
						if(cell.getColumnIndex()==1)
						{
						System.out.print(cell.getNumericCellValue() +"\t" +"\t");
					    stu.setId((int) cell.getNumericCellValue());
						}
						else if (cell.getColumnIndex()==2)
						{
							System.out.print(cell.getNumericCellValue() +"\t" +"\t");
							stu.setMarks((int) cell.getNumericCellValue());
						}
						break;
					}	
				}
				list.add(stu);
				System.out.println();
			}
			
			
		} 
		catch (FileNotFoundException e) 
		{
		
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void mail(ArrayList<Student> list) throws IOException
	{
		Iterator<Student> il = list.iterator();
		while(il.hasNext())
		{
			System.out.println(il.next());
		}
		
		for(Student st : list)
		{
		
		String to = st.getEmail();
		String from = "ak4702328@gmail.com";
		final String username = "ak4702328";
	    final String password = "###########";
	    String host = "smtp.gmail.com";
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(props,
	    	      new javax.mail.Authenticator() {
	    	         protected PasswordAuthentication getPasswordAuthentication() {
	    	            return new PasswordAuthentication(username, password);
	    	         }
	    	      });
	    
	    try {
	         Message message = new MimeMessage(session);

	        
	         message.setFrom(new InternetAddress(from));

	        
	         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));

	 
	         message.setSubject("Grading....");

	        
	         message.setText("Hello "+ st.getName()+" ," +"\n\n"+"Marks: " +st.getMarks() +"\n"+"Reason: "+ st.getNotes() +"\n\n"+ "Regards,"+"\n"+"Abhishek Kumar");

	        
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }

		}
	}
	
	public static void main(String...args) throws IOException
	{
		ArrayList<Student> list = list1();
		FileExcel.mail(list);
	}

}
