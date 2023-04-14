package Service;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
//import org.slf4j.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import Model.Course;
import Model.Student;

public class pdfProducer_v1 {
  public pdfProducer_v1() {
  }

  public static void pdfProducer(ArrayList<Course> courses, ArrayList<Student> students) throws Exception {
    // Creating a PdfDocument object
    String dest = "addingTable.pdf";

    PdfWriter writer = new PdfWriter(dest);

    // Creating a PdfDocument object
    PdfDocument pdf = new PdfDocument(writer);

    // Creating a Document object
    Document doc = new Document(pdf);
    // Print Name and email
    for (Student student : students) {
      /*
       * Text text1 = new Text("Tutorialspoint");
       * 
       * // Setting font of the text
       * PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
       * text1.setFont(font);
       * 
       * // Setting font color
       * text1.setFontColor(Color.GREEN);
       * 
       * // Creating text object
       * Text text2 = new Text("Simply Easy Learning");
       * text2.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
       * 
       * // Setting font color
       * text2.setFontColor(Color.BLUE);
       * 
       * // Creating Paragraph
       * Paragraph paragraph1 = new Paragraph();
       * 
       * // Adding text1 to the paragraph
       * paragraph1.add(text1);
       * paragraph1.add(text2);
       * 
       * // Adding paragraphs to the document
       * doc.add(paragraph1);
       */
      Paragraph paragraph1 = new Paragraph(student.getStudentName());
      Paragraph paragraph2 = new Paragraph(student.getStudentEmail());
      doc.add(paragraph1);
      doc.add(paragraph2);
    }
    // String name = students.getStudentName();

    // Creating a table
    float[] pointColumnWidths = { 150F, 150F };
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

    // freely edit

    // Closing the document
    doc.close();
    System.out.println("Table created successfully..");
  }

}
