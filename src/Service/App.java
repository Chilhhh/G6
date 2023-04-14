package Service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class App {
	private static final String splitVline = "    |    ";
	private static final String TAB = "    ";

	// �ж��Ƿ�Ϊ16������ɫ
	public static boolean isHexColor(String color) {
		String HEX_PATTERN = "[0-9A-Fa-f]+";
		return color != null && color.length() == 6 && color.matches(HEX_PATTERN);
	}

	// ��16������ɫת��ΪColor����
	private static BaseColor myColor(String hexColor) {
		hexColor = hexColor.replace("#", "");
		if (hexColor.length() == 3) {
			hexColor = hexColor + hexColor;
		}
		if (!isHexColor(hexColor)) {
			hexColor = "ffffff";
		}
		int r = Integer.valueOf(hexColor.substring(0, 2), 16);
		int g = Integer.valueOf(hexColor.substring(2, 4), 16);
		int b = Integer.valueOf(hexColor.substring(4, 6), 16);
		return new BaseColor(r, g, b);
	}

	/**
	 * �������壬����С
	 * 
	 * @param size
	 * @param style
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private static Font myfont(float size, int style) throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("/home/STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		com.itextpdf.text.Font myFont = new com.itextpdf.text.Font(bfChinese, size, style);
		return myFont;
	}

	private static Font myfont(float size, int style, String color) throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("/home/STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		com.itextpdf.text.Font myFont = new com.itextpdf.text.Font(bfChinese, size, style);
		myFont.setColor(myColor(color));
		return myFont;
	}

	/**
	 * ����һ
	 * 
	 * @param text
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private static Paragraph h1(String text) throws DocumentException, IOException {
		Paragraph p = new Paragraph(text, myfont(16, Font.BOLD));
		// p.setSpacingBefore(30f);
		return p;
	}

	/**
	 * ��ǰ��ͼ�ı���
	 * 
	 * @param text ��������
	 * @return
	 * @throws Exception
	 */
	private static PdfPTable add_headtitle_1(String text) throws Exception {
		PdfPTable table = new PdfPTable(2);
		// ʵ�ֵ�Ԫ���ҳ��ʾ
		table.setSplitLate(false);
		table.setSplitRows(true);
		// ����ÿ�п�ȱ���
		int width_ht1[] = { 2, 98 };
		table.setWidths(width_ht1);
		table.getDefaultCell().setBorder(0);
		// ��ǰ��ͼ(����1)��ַ
		// String imagePath2 = "/home/hadoop/Pictures/line.png"; //ͼƬΪ2px*11px
		// Image image21 = Image.getInstance(imagePath2);
		// ��ǰ��ͼ(����2)��Java��
		BufferedImage image = new BufferedImage(2, 11, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(new Color(83, 202, 195)); // #53cac3
		g.fillRect(0, 0, 2, 11);
		Image image21 = Image.getInstance(image, null);

		PdfPCell cellHt1img = new PdfPCell();
		cellHt1img.setBorder(0);
		cellHt1img.setImage(image21);
		cellHt1img.setFixedHeight(1);
		cellHt1img.setPaddingTop(5);
		PdfPCell cellHt1Content = new PdfPCell(h1(text));
		cellHt1Content.setBorder(0);
		// cell21.setLeading(5, 1);
		table.addCell(cellHt1img);
		table.addCell(cellHt1Content);
		return table;
	}

	/**
	 * ����
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Paragraph blankRow() throws Exception {
		Paragraph blankRow = new Paragraph(20f, " ", myfont(20, Font.BOLD));
		return blankRow;
	}

	/**
	 * ����
	 * 
	 * @param leading �м��
	 * @return
	 * @throws Exception
	 */
	private static Paragraph blankRow(float leading) throws Exception {
		Paragraph blankRow = new Paragraph(leading, " ", myfont(20, Font.BOLD));
		return blankRow;
	}

	private static PdfPCell add_cell(Object obj, float lead) throws Exception {
		PdfPCell cell = new PdfPCell(new Paragraph(obj + "", myfont(12, Font.NORMAL)));
		cell.setBorder(0);
		cell.setLeading(lead, 1);
		return cell;
	}

	private static PdfPCell add_cell(Object obj, String color, float lead) throws Exception {
		color = color == null ? "#000000" : color;
		PdfPCell cell = new PdfPCell(new Paragraph(obj + "", myfont(12, Font.NORMAL, color)));
		cell.setBorder(0);
		cell.setLeading(lead, 1);
		return cell;
	}

	private static PdfPCell add_cell(Object obj, TBCell tbCell) throws Exception {
		PdfPCell cell = new PdfPCell(
				new Paragraph(obj + "", myfont(tbCell.getFontSize(), tbCell.getFontStyle(), tbCell.getTextColor())));
		cell.setBorder(tbCell.getBorder());
		cell.setLeading(tbCell.getLead(), 1);
		if (tbCell.getBgcolor() != null && !"".equals(tbCell.getBgcolor())) {
			cell.setBackgroundColor(myColor(tbCell.getBgcolor()));
		}
		if (tbCell.getColspan() != 0) {
			cell.setColspan(tbCell.getColspan());
		}
		if (tbCell.getRowspan() != 0) {
			cell.setRowspan(tbCell.getRowspan());
		}
		if (tbCell.getBorderColor() != null && !"".equals(tbCell.getBorderColor())) {
			cell.setBorderColor(myColor(tbCell.getBorderColor()));
		}
		if (tbCell.getIndent() > 0) {
			cell.setIndent(tbCell.getIndent());
		}
		return cell;
	}

	private static PdfPCell add_cell_speace(TBCell tbCell) throws Exception {
		PdfPCell cell = new PdfPCell(
				new Paragraph(" ", myfont(tbCell.getFontSize(), tbCell.getFontStyle(), tbCell.getTextColor())));
		cell.setBorder(tbCell.getBorder());
		cell.setLeading(tbCell.getLead(), 1);
		if (tbCell.getBgcolor() != null && !"".equals(tbCell.getBgcolor())) {
			cell.setBackgroundColor(myColor(tbCell.getBgcolor()));
		}
		if (tbCell.getColspan() != 0) {
			cell.setColspan(tbCell.getColspan());
		}
		if (tbCell.getRowspan() != 0) {
			cell.setRowspan(tbCell.getRowspan());
		}
		if (tbCell.getBorderColor() != null && !"".equals(tbCell.getBorderColor())) {
			cell.setBorderColor(myColor(tbCell.getBorderColor()));
		}
		if (tbCell.getIndent() > 0) {
			cell.setIndent(tbCell.getIndent());
		}
		return cell;
	}

	/**
	 * �����������巽��
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_workExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// ʵ�ֵ�Ԫ���ҳ��ʾ
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);
		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs1 = TBCell.NEW().bgcolor("#eee").lead(5).borderColor("#eee").fontStyle(Font.BOLD);
		TBCell tbcs2 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1)
				.fontStyle(Font.BOLD);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs4 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1).indent(20);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		workExpTable.addCell(add_cell("���ݴ����ݷ�չ����", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("2019��10��-2020��10��");
		sbf.append(splitVline);
		sbf.append("��Ŀ����");
		sbf.append(splitVline);
		sbf.append("10k~15k");
		sbf.append(splitVline);
		sbf.append("��������Ʒ����");
		sbf.append(splitVline);
		sbf.append("�������ѧ�뼼��");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs4));
		workExpTable.addCell(add_cell("����������", tbcs2));
		StringBuffer sbd = new StringBuffer();
		sbd.append("�ڹ�˾����Ӫ�����Ĳ���ϵͳ������뿪����\n" +
				"ϵͳ����spring cloud��΢����ƽ̨��ǰ����ʹ��vue��ܣ���̨ʹ��spring cloud��Mybatis��ǰ��̨ʹ��json���ݽ�������ʹ����mysql�ֲ�ʽ���ݿ⣬Oracle���ݿ��Լ�redis��Ⱥ��\n"
				+

				"ϵͳʹ��docker��������ʹ��git�������maven����ϵͳ��devops�Զ�������");
		workExpTable.addCell(add_cell(sbd.toString(), tbcs4));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * ��Ŀ��������
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_projectExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// ʵ�ֵ�Ԫ���ҳ��ʾ
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);
		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs1 = TBCell.NEW().bgcolor("#eee").lead(5).borderColor("#eee").fontStyle(Font.BOLD);
		TBCell tbcs2 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1)
				.fontStyle(Font.BOLD);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs4 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1).indent(20);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		workExpTable.addCell(add_cell("�����˹����ܵ��ǻ�У԰ƽ̨", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("��Ŀʱ�䣺2019��11��11��0�㣭2019��11��20��24��");
		sbf.append(splitVline);
		sbf.append("���ν�ɫ����Ŀ����");
		sbf.append(splitVline);
		sbf.append("������λ�����ݴ������о���");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell("��Ŀ������", tbcs2));
		StringBuffer sbd = new StringBuffer();
		sbd.append("�ڹ�˾����Ӫ�����Ĳ���ϵͳ������뿪����\n" +
				"ϵͳ����spring cloud��΢����ƽ̨��ǰ����ʹ��vue��ܣ���̨ʹ��spring cloud��Mybatis��ǰ��̨ʹ��json���ݽ�������ʹ����mysql�ֲ�ʽ���ݿ⣬Oracle���ݿ��Լ�redis��Ⱥ��\n"
				+

				"ϵͳʹ��docker��������ʹ��git�������maven����ϵͳ��devops�Զ�������");
		workExpTable.addCell(add_cell(sbd.toString(), tbcs4));
		workExpTable.addCell(add_cell("����ְ��", tbcs2));
		StringBuffer sbgr = new StringBuffer();
		sbgr.append("�ڹ�˾����Ӫ�����Ĳ���ϵͳ������뿪����\n" +
				"ϵͳ����spring cloud��΢����ƽ̨��ǰ����ʹ��vue��ܣ���̨ʹ��spring cloud��Mybatis��ǰ��̨ʹ��json���ݽ�������ʹ����mysql�ֲ�ʽ���ݿ⣬Oracle���ݿ��Լ�redis��Ⱥ��\n"
				+

				"ϵͳʹ��docker��������ʹ��git�������maven����ϵͳ��devops�Զ�������");
		workExpTable.addCell(add_cell(sbgr.toString(), tbcs4));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * ��������
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_eduExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// ʵ�ֵ�Ԫ���ҳ��ʾ
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);

		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs1 = TBCell.NEW().bgcolor("#eee").lead(5).borderColor("#eee").fontStyle(Font.BOLD);
		TBCell tbcs2 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1)
				.fontStyle(Font.BOLD);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs4 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1).indent(20);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		workExpTable.addCell(add_cell("���ݽ�����ѧ", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("רҵ���������ѧ�뼼��");
		sbf.append(splitVline);
		sbf.append("ѧ��������");
		sbf.append(splitVline);
		sbf.append("ʱ�䣺2015��9��-2019��7��");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * ��ѵ����
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_trainExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// ʵ�ֵ�Ԫ���ҳ��ʾ
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);
		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs1 = TBCell.NEW().bgcolor("#eee").lead(5).borderColor("#eee").fontStyle(Font.BOLD);
		TBCell tbcs2 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1)
				.fontStyle(Font.BOLD);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs4 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1).indent(20);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		workExpTable.addCell(add_cell("��ѵ�γ̣�hadoop�߼�����ʦ��ѵ", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("ʱ�䣺2015��9��-2019��7��");
		sbf.append(splitVline);
		sbf.append("��ѵ��������������ѵ����");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * רҵ����
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_majorSkillExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// ���õ�Ԫ���ҳ��ʾ
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);

		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		StringBuffer sbf = new StringBuffer();
		sbf.append("Linux");
		sbf.append(splitVline);
		sbf.append("5��");
		sbf.append(splitVline);
		sbf.append("����");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * ��������
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_langSkillExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// ���õ�Ԫ���ҳ��ʾ
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);

		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		StringBuffer sbf = new StringBuffer();
		sbf.append("Ӣ��");
		sbf.append(splitVline);
		sbf.append("��˵����������");
		sbf.append(splitVline);
		sbf.append("��д����������");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * �ʸ�֤��
	 * 
	 * @param doc Document����
	 * @throws Exception
	 */
	private static void add_certExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs3 = TBCell.NEW().lead(10).colspan(2).rowspan(1);
		TBCell tbcs5 = TBCell.NEW().lead(1).colspan(2).rowspan(1);
		StringBuffer sbf = new StringBuffer();
		sbf.append("֤�����ƣ� HCIP��Ϊ��֤ICT�߼�����ʦ");
		sbf.append(splitVline);
		sbf.append("���ʱ�䣺2019��10��");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		// String certImgPath =
		// "http://www.ynbdqn.cn/DianNaoPeiXun/UploadFiles_7627/201011/2010111610590444.jpg";
		String certImgPath = "/home/hcert.jpeg";
		Image certImg = Image.getInstance(certImgPath);
		// ����ͼƬ����(�ó����ĵ���ȵ�ͼƬ����Ӧ���)
		float imgWidth = certImg.getScaledWidth();
		BigDecimal bdiw = new BigDecimal("480").divide(new BigDecimal(imgWidth), 2, RoundingMode.HALF_UP);
		BigDecimal bdrs = bdiw.multiply(new BigDecimal("100"));
		certImg.scalePercent(bdrs.floatValue());
		PdfPCell cellCert = new PdfPCell();
		cellCert.setBorder(0);
		cellCert.setImage(certImg);
		workExpTable.addCell(add_cell_speace(tbcs5));
		workExpTable.addCell(cellCert);
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	public static void main(String[] args) {
		try {
			// �����ĵ�
			Document document = new Document(PageSize.A4, 5, 5, 36, 36);
			// �����ĵ�����·��
			PdfWriter.getInstance(document, new FileOutputStream("demo.pdf"));
			document.open();
			Paragraph title = new Paragraph(18f, "���˼���", myfont(22, Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(blankRow(30));
			// ������Ϣ
			PdfPTable tbBaseInfo = new PdfPTable(2);
			PdfPCell cell11 = new PdfPCell(new Paragraph("������", myfont(18, Font.NORMAL)));
			cell11.setBorder(0);
			// cell11.setPaddingTop(5);
			cell11.setPaddingBottom(10);
			cell11.setPaddingLeft(15);
			// ͷ��
			String headImgPath = "/home/hcert.jpeg";
			Image headImg = Image.getInstance(headImgPath);
			// ����ÿ�п�ȱ���
			int width11[] = { 15, 85 };
			tbBaseInfo.setWidths(width11);
			tbBaseInfo.getDefaultCell().setBorder(0);
			PdfPCell cellHimg = new PdfPCell();
			cellHimg.setBorder(0);
			cellHimg.setImage(headImg);
			cellHimg.setColspan(1);// �ϲ���Ԫ��
			cellHimg.setRowspan(3);
			tbBaseInfo.addCell(cellHimg);
			tbBaseInfo.addCell(cell11);
			PdfPCell cell12 = new PdfPCell(
					new Paragraph("Ů" + splitVline + "18��" + splitVline + " ���԰�����" + splitVline + "6�� �� ������", myfont(
							12, Font.NORMAL)));
			cell12.setPaddingBottom(5);
			cell12.setPaddingLeft(15);
			cell12.setBorder(0);
			tbBaseInfo.addCell(cell12);
			PdfPCell cell13 = new PdfPCell(
					new Paragraph("15700000000" + splitVline + "lindaiyu@exapme.com", myfont(12, Font.NORMAL)));
			cell13.setBorder(0);
			cell13.setPaddingLeft(15);
			tbBaseInfo.addCell(cell13);
			document.add(tbBaseInfo);
			// �������
			document.add(blankRow(30));

			document.add(add_headtitle_1("��ְ����"));
			// �������
			document.add(blankRow(5));
			// ��ְ��������
			PdfPTable careerTable = new PdfPTable(4);
			// ���õ�Ԫ���ҳ��ʾ
			careerTable.setSplitLate(false);
			careerTable.setSplitRows(true);

			int careerTableWidth[] = { 15, 35, 15, 35 };
			careerTable.setWidths(careerTableWidth);
			careerTable.addCell(add_cell("��ְ״̬��", "#878787", 10));
			careerTable.addCell(add_cell("��ְ���ǻ�����", 10));
			careerTable.addCell(add_cell("�����ص㣺", "#878787", 10));
			careerTable.addCell(add_cell("�й�-��������", 10));

			careerTable.addCell(add_cell("������ҵ��", "#878787", 10));
			careerTable.addCell(add_cell("��ҵ��λ/�����", 10));
			careerTable.addCell(add_cell("����ְҵ��", "#878787", 10));
			careerTable.addCell(add_cell("��Ŀ����/��ά����/��Ϣ��ȫ����ʦ/ϵͳ�ܹ�ʦ", 10));

			careerTable.addCell(add_cell("����н�ʣ�", "#878787", 10));
			careerTable.addCell(add_cell("10k~15k", 10));
			careerTable.addCell(add_cell("�������ʣ�", "#878787", 10));
			careerTable.addCell(add_cell("ȫְ", 10));
			document.add(careerTable);
			// �������
			document.add(blankRow());

			// ��������
			document.add(add_headtitle_1("��������"));
			document.add(blankRow(5));
			// �������� ���� Begin
			add_workExpTable(document);
			add_workExpTable(document);
			// �������� ���� END

			document.add(blankRow());

			// ��Ŀ����
			document.add(add_headtitle_1("��Ŀ����"));
			document.add(blankRow(5));
			add_projectExpTable(document);

			document.add(blankRow());
			// ��������
			document.add(add_headtitle_1("��������"));
			document.add(blankRow(5));
			add_eduExpTable(document);

			document.add(blankRow());
			// ��ѵ����
			document.add(add_headtitle_1("��ѵ����"));
			document.add(blankRow(5));
			add_trainExpTable(document);

			document.add(blankRow());
			// רҵ����
			document.add(add_headtitle_1("רҵ����"));
			document.add(blankRow(5));
			add_majorSkillExpTable(document);

			document.add(blankRow());
			// ��������
			document.add(add_headtitle_1("��������"));
			document.add(blankRow(5));
			add_langSkillExpTable(document);

			document.add(blankRow());
			// �ʸ�֤��
			document.add(add_headtitle_1("�ʸ�֤�� "));
			document.add(blankRow(5));
			add_certExpTable(document);

			document.close();
			System.out.println("�����ɹ�");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}