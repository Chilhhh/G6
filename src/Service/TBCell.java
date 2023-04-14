package Service;
 
import com.itextpdf.text.Font;
 
public class TBCell {
	private int border=0;
	private String textColor="#000000";
	private float lead=0;
	private float fontSize=12;
	private String bgcolor=null;
	private int colspan=0;
	private int rowspan=0;
	private String borderColor;
	private int fontStyle=Font.NORMAL;
	private float indent=0;
	public float getIndent() {
		return indent;
	}
	public void setIndent(float indent) {
		this.indent = indent;
	}
	public int getFontStyle() {
		return fontStyle;
	}
	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public int getColspan() {
		return colspan;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public int getRowspan() {
		return rowspan;
	}
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	public int getBorder() {
		return border;
	}
	public void setBorder(int border) {
		this.border = border;
	}
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}
	public float getLead() {
		return lead;
	}
	public void setLead(float lead) {
		this.lead = lead;
	}
	public float getFontSize() {
		return fontSize;
	}
	public void setFontSize(float fontSize) {
		this.fontSize = fontSize;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	
	public static TBCell NEW() {
        return new TBCell();
    }
	
	public TBCell border(int border) {
		this.setBorder(border);
		return this;
	}
	public TBCell color(String color) {
		this.setTextColor(color);
		return this;
	}
	public TBCell lead(float lead) {
		this.setLead(lead);
		return this;
	}
	public TBCell fontSize(float fontSize) {
		this.setFontSize(fontSize);
		return this;
	}
	public TBCell bgcolor(String bgcolor) {
		this.setBgcolor(bgcolor);
		return this;
	}
	public TBCell colspan(int colspan) {
		this.setColspan(colspan);
		return this;
	}
	public TBCell rowspan(int rowspan) {
		this.setRowspan(rowspan);
		return this;
	}
	public TBCell borderColor(String borderColor) {
		this.setBorderColor(borderColor);
		return this;
	}
	public TBCell fontStyle(int fontStyle) {
		this.setFontStyle(fontStyle);
		return this;
	}
	public TBCell indent(float indent) {
		this.setIndent(indent);
		return this;
	}
	@Override
	public String toString() {
		return "TBCell [border=" + border + ", textColor=" + textColor + ", lead=" + lead + ", fontSize=" + fontSize
				+ ", bgcolor=" + bgcolor + ", colspan=" + colspan + ", rowspan=" + rowspan + ", borderColor="
				+ borderColor + ", fontStyle=" + fontStyle + "]";
	}
	
	
}