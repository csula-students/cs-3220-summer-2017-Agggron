package tags;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class AdminHeader extends SimpleTagSupport {
	String currentTitle;

	public AdminHeader() {
		this.currentTitle = null;
	}

	public String getTitle() {
		return this.currentTitle;
	}
	public void setTitle(String title) {
		this.currentTitle = title;
	}
   public void doTag() throws JspException, IOException {
      JspWriter out = getJspContext().getOut();
      out.println("<header>" +
            			"<img src='/images/yellow-moon.jpg' width=150px height=150px class='header_image'>" +
            			"The Yellow Moon Inn" +
            		"</header>");
   }
}