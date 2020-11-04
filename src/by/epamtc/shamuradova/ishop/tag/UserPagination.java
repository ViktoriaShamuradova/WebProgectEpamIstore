package by.epamtc.shamuradova.ishop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class UserPagination extends TagSupport {


	private static final long serialVersionUID = 1881091514780686805L;
	
	private int currentPage;
	private int totalEntity;
	private int perPage;
	private String currentCommand;

	private int roleId;

	public UserPagination() {

	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCurrentCommand(String currentCommand) {
		this.currentCommand = currentCommand;
	}

	public void setTotalEntity(int totalEntity) {
		this.totalEntity = totalEntity;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	@Override
	public int doStartTag() throws JspException {
		int pageCount = (int) Math.ceil((double) totalEntity / perPage);// сть же этот параметр с комманды, можно
																		// передать

		JspWriter out = pageContext.getOut();
		try {
			out.write("<main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 pt-3 px-4\">");
			out.write("<div class=\"container\">");
			out.write("<center>");

			if (pageCount != 1) {
				for (int i = 1; i <= pageCount; i++) {
					String active = i == currentPage ? " active" : "";
					out.append("<a class=\"btn btn-secondary btn-sm" + active + "\" href=\"" + currentCommand + "&roleId="
							+ roleId + "&pageNumber=" + i + "\">" + i + " </a> ");
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
