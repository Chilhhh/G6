package Model;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.slf4j.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table;  

public class pdfProducer {      
   public pdfProducer()  {                   
  }

  public static void pdfProducer(ArrayList<Course> courses) throws Exception {
    // Creating a PdfDocument object   
    String dest = "addingTable.pdf";   
      
    PdfWriter writer = new PdfWriter(dest);       
          
    // Creating a PdfDocument object      
    PdfDocument pdf = new PdfDocument(writer);                  
       
            
    // Creating a Document object       
    Document doc = new Document(pdf);  
    // Creating a table       
    float [] pointColumnWidths = {150F, 150F};   
    Table table = new Table(pointColumnWidths); 

    // sort list

    // Adding cells to the table
    for (Course course : courses) {
      table.addCell(new Cell().add(new Paragraph(course.getCourseName())));
      String score = String.valueOf(course.getCourseScore());    
      table.addCell(new Cell().add(new Paragraph(score)));
    }                     
       
    // Adding Table to document        
    doc.add(table);                  
       
    // Closing the document       
    doc.close();
    System.out.println("Table created successfully..");
  }
     
}
