package by.epamtc.shamuradova.ishop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Pagination extends TagSupport{

	private static final long serialVersionUID = -7373162363666290106L;

	private int currentPage;
	private int totalEntity;
	private int perPage;
	
	
	public Pagination() {	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalEntity(int totalEntity) {
		this.totalEntity = totalEntity;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	@Override
	public int doStartTag() throws JspException {
		int pageCount = (int) Math.ceil((double) totalEntity / perPage);//сть же этот параметр с комманды, можно передать
	
		JspWriter out = pageContext.getOut();
		try {
			out.write("<main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 pt-3 px-4\">");
			out.write("<div class=\"container\">");
			out.write("<center>");
			
			if (pageCount != 1) {
				for (int i = 1; i <= pageCount; i++) {
					String active = i == currentPage ? " active" : "";
					out.append("<a class=\"btn btn-primary btn-sm" + active + "\" href=\"controller?command=BLACK_LIST"
						+ "&pageNumber=" + i
							+ "\">" + i + " </a> ");
				}
			}
			out.write("</center>");
			out.write("</div>");
			out.write("</main>");
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

}
