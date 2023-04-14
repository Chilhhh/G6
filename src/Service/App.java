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

	// 判断是否为16进制颜色
	public static boolean isHexColor(String color) {
		String HEX_PATTERN = "[0-9A-Fa-f]+";
		return color != null && color.length() == 6 && color.matches(HEX_PATTERN);
	}

	// 将16进制颜色转换为Color对象
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
	 * 设置字体，及大小
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
	 * 标题一
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
	 * 带前导图的标题
	 * 
	 * @param text 标题文字
	 * @return
	 * @throws Exception
	 */
	private static PdfPTable add_headtitle_1(String text) throws Exception {
		PdfPTable table = new PdfPTable(2);
		// 实现单元格跨页显示
		table.setSplitLate(false);
		table.setSplitRows(true);
		// 设置每列宽度比例
		int width_ht1[] = { 2, 98 };
		table.setWidths(width_ht1);
		table.getDefaultCell().setBorder(0);
		// 带前导图(方法1)地址
		// String imagePath2 = "/home/hadoop/Pictures/line.png"; //图片为2px*11px
		// Image image21 = Image.getInstance(imagePath2);
		// 带前导图(方法2)用Java画
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
	 * 空行
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Paragraph blankRow() throws Exception {
		Paragraph blankRow = new Paragraph(20f, " ", myfont(20, Font.BOLD));
		return blankRow;
	}

	/**
	 * 空行
	 * 
	 * @param leading 行间距
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
	 * 工作经验主体方法
	 * 
	 * @param doc Document对象
	 * @throws Exception
	 */
	private static void add_workExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// 实现单元格跨页显示
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
		workExpTable.addCell(add_cell("贵州大数据发展集团", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("2019年10月-2020年10月");
		sbf.append(splitVline);
		sbf.append("项目经理");
		sbf.append(splitVline);
		sbf.append("10k~15k");
		sbf.append(splitVline);
		sbf.append("互联网产品经理");
		sbf.append(splitVline);
		sbf.append("计算机科学与技术");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs4));
		workExpTable.addCell(add_cell("工作描述：", tbcs2));
		StringBuffer sbd = new StringBuffer();
		sbd.append("在公司参与营销中心财务系统的设计与开发。\n" +
				"系统基于spring cloud的微服务平台，前端是使用vue框架，后台使用spring cloud与Mybatis，前后台使用json数据交换。还使用了mysql分布式数据库，Oracle数据库以及redis集群，\n"
				+

				"系统使用docker容器部署。使用git代码管理，maven构建系统，devops自动化部署");
		workExpTable.addCell(add_cell(sbd.toString(), tbcs4));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * 项目经验主体
	 * 
	 * @param doc Document对象
	 * @throws Exception
	 */
	private static void add_projectExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// 实现单元格跨页显示
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
		workExpTable.addCell(add_cell("基于人工智能的智慧校园平台", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("项目时间：2019年11月11日0点－2019年11月20日24点");
		sbf.append(splitVline);
		sbf.append("担任角色：项目经理");
		sbf.append(splitVline);
		sbf.append("所属单位：贵州大数据研究所");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell("项目描述：", tbcs2));
		StringBuffer sbd = new StringBuffer();
		sbd.append("在公司参与营销中心财务系统的设计与开发。\n" +
				"系统基于spring cloud的微服务平台，前端是使用vue框架，后台使用spring cloud与Mybatis，前后台使用json数据交换。还使用了mysql分布式数据库，Oracle数据库以及redis集群，\n"
				+

				"系统使用docker容器部署。使用git代码管理，maven构建系统，devops自动化部署");
		workExpTable.addCell(add_cell(sbd.toString(), tbcs4));
		workExpTable.addCell(add_cell("个人职责：", tbcs2));
		StringBuffer sbgr = new StringBuffer();
		sbgr.append("在公司参与营销中心财务系统的设计与开发。\n" +
				"系统基于spring cloud的微服务平台，前端是使用vue框架，后台使用spring cloud与Mybatis，前后台使用json数据交换。还使用了mysql分布式数据库，Oracle数据库以及redis集群，\n"
				+

				"系统使用docker容器部署。使用git代码管理，maven构建系统，devops自动化部署");
		workExpTable.addCell(add_cell(sbgr.toString(), tbcs4));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * 教育经历
	 * 
	 * @param doc Document对象
	 * @throws Exception
	 */
	private static void add_eduExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// 实现单元格跨页显示
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
		workExpTable.addCell(add_cell("贵州教育大学", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("专业：计算机科学与技术");
		sbf.append(splitVline);
		sbf.append("学历：本科");
		sbf.append(splitVline);
		sbf.append("时间：2015年9月-2019年7月");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * 培训经历
	 * 
	 * @param doc Document对象
	 * @throws Exception
	 */
	private static void add_trainExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// 实现单元格跨页显示
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
		workExpTable.addCell(add_cell("培训课程：hadoop高级工程师培训", tbcs1));
		StringBuffer sbf = new StringBuffer();
		sbf.append("时间：2015年9月-2019年7月");
		sbf.append(splitVline);
		sbf.append("培训机构：大数据培训中心");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * 专业技能
	 * 
	 * @param doc Document对象
	 * @throws Exception
	 */
	private static void add_majorSkillExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// 配置单元格跨页显示
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
		sbf.append("5年");
		sbf.append(splitVline);
		sbf.append("熟练");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * 语言能力
	 * 
	 * @param doc Document对象
	 * @throws Exception
	 */
	private static void add_langSkillExpTable(Document doc) throws Exception {
		PdfPTable workExpTable = new PdfPTable(1);
		// 配置单元格跨页显示
		workExpTable.setSplitLate(false);
		workExpTable.setSplitRows(true);

		int workExpTableWidth[] = { 100 };
		workExpTable.setWidths(workExpTableWidth);
		workExpTable.getDefaultCell().setBorder(0);
		TBCell tbcs3 = TBCell.NEW().bgcolor("#eee").lead(10).borderColor("#eee").colspan(2).rowspan(1);
		TBCell tbcs5 = TBCell.NEW().bgcolor("#eee").lead(1).borderColor("#eee").colspan(2).rowspan(1);
		StringBuffer sbf = new StringBuffer();
		sbf.append("英语");
		sbf.append(splitVline);
		sbf.append("听说能力：熟练");
		sbf.append(splitVline);
		sbf.append("读写能力：熟练");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		workExpTable.addCell(add_cell_speace(tbcs5));
		doc.add(workExpTable);
		doc.add(blankRow(5));
	}

	/**
	 * 资格证书
	 * 
	 * @param doc Document对象
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
		sbf.append("证书名称： HCIP华为认证ICT高级工程师");
		sbf.append(splitVline);
		sbf.append("获得时间：2019年10月");
		workExpTable.addCell(add_cell(sbf.toString(), tbcs3));
		// String certImgPath =
		// "http://www.ynbdqn.cn/DianNaoPeiXun/UploadFiles_7627/201011/2010111610590444.jpg";
		String certImgPath = "/home/hcert.jpeg";
		Image certImg = Image.getInstance(certImgPath);
		// 设置图片缩放(让超过文档宽度的图片自适应宽度)
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
			// 创建文档
			Document document = new Document(PageSize.A4, 5, 5, 36, 36);
			// 设置文档保存路径
			PdfWriter.getInstance(document, new FileOutputStream("demo.pdf"));
			document.open();
			Paragraph title = new Paragraph(18f, "个人简历", myfont(22, Font.BOLD));
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);
			document.add(blankRow(30));
			// 基本信息
			PdfPTable tbBaseInfo = new PdfPTable(2);
			PdfPCell cell11 = new PdfPCell(new Paragraph("林黛玉", myfont(18, Font.NORMAL)));
			cell11.setBorder(0);
			// cell11.setPaddingTop(5);
			cell11.setPaddingBottom(10);
			cell11.setPaddingLeft(15);
			// 头像
			String headImgPath = "/home/hcert.jpeg";
			Image headImg = Image.getInstance(headImgPath);
			// 设置每列宽度比例
			int width11[] = { 15, 85 };
			tbBaseInfo.setWidths(width11);
			tbBaseInfo.getDefaultCell().setBorder(0);
			PdfPCell cellHimg = new PdfPCell();
			cellHimg.setBorder(0);
			cellHimg.setImage(headImg);
			cellHimg.setColspan(1);// 合并单元格
			cellHimg.setRowspan(3);
			tbBaseInfo.addCell(cellHimg);
			tbBaseInfo.addCell(cell11);
			PdfPCell cell12 = new PdfPCell(
					new Paragraph("女" + splitVline + "18岁" + splitVline + " 大观园潇湘馆" + splitVline + "6年 工 作经验", myfont(
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
			// 加入空行
			document.add(blankRow(30));

			document.add(add_headtitle_1("求职意向"));
			// 加入空行
			document.add(blankRow(5));
			// 求职意向主体
			PdfPTable careerTable = new PdfPTable(4);
			// 配置单元格跨页显示
			careerTable.setSplitLate(false);
			careerTable.setSplitRows(true);

			int careerTableWidth[] = { 15, 35, 15, 35 };
			careerTable.setWidths(careerTableWidth);
			careerTable.addCell(add_cell("求职状态：", "#878787", 10));
			careerTable.addCell(add_cell("在职考虑换工作", 10));
			careerTable.addCell(add_cell("工作地点：", "#878787", 10));
			careerTable.addCell(add_cell("中国-义龙新区", 10));

			careerTable.addCell(add_cell("期望行业：", "#878787", 10));
			careerTable.addCell(add_cell("事业单位/计算机", 10));
			careerTable.addCell(add_cell("期望职业：", "#878787", 10));
			careerTable.addCell(add_cell("项目经理/运维开发/信息安全工程师/系统架构师", 10));

			careerTable.addCell(add_cell("期望薪资：", "#878787", 10));
			careerTable.addCell(add_cell("10k~15k", 10));
			careerTable.addCell(add_cell("工作性质：", "#878787", 10));
			careerTable.addCell(add_cell("全职", 10));
			document.add(careerTable);
			// 加入空行
			document.add(blankRow());

			// 工作经验
			document.add(add_headtitle_1("工作经验"));
			document.add(blankRow(5));
			// 工作经验 主体 Begin
			add_workExpTable(document);
			add_workExpTable(document);
			// 工作经验 主体 END

			document.add(blankRow());

			// 项目经验
			document.add(add_headtitle_1("项目经验"));
			document.add(blankRow(5));
			add_projectExpTable(document);

			document.add(blankRow());
			// 教育经历
			document.add(add_headtitle_1("教育经历"));
			document.add(blankRow(5));
			add_eduExpTable(document);

			document.add(blankRow());
			// 培训经历
			document.add(add_headtitle_1("培训经历"));
			document.add(blankRow(5));
			add_trainExpTable(document);

			document.add(blankRow());
			// 专业技能
			document.add(add_headtitle_1("专业技能"));
			document.add(blankRow(5));
			add_majorSkillExpTable(document);

			document.add(blankRow());
			// 语言能力
			document.add(add_headtitle_1("语言能力"));
			document.add(blankRow(5));
			add_langSkillExpTable(document);

			document.add(blankRow());
			// 资格证书
			document.add(add_headtitle_1("资格证书 "));
			document.add(blankRow(5));
			add_certExpTable(document);

			document.close();
			System.out.println("创建成功");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}