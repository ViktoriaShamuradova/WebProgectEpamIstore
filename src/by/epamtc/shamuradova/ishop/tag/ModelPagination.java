package by.epamtc.shamuradova.ishop.tag;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.epamtc.shamuradova.ishop.bean.Model;

public class ModelPagination extends TagSupport {
	private static final long serialVersionUID = 2579972393677888552L;

	private List<Model> models;

	private int currentPage;

	private int totalModels;

	private int modelsPerPage;
	
	private String category;

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalModels(int totalModels) {
		this.totalModels = totalModels;
	}

	public void setModelsPerPage(int modelsPerPage) {
		this.modelsPerPage = modelsPerPage;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int doStartTag() throws JspException {
		int pageCount = (int) Math.ceil((double) totalModels / modelsPerPage);//сть же этот параметр с комманды, можно передать
		
		
	//	HttpSession session = pageContext.getSession();		
//		String localeName = (String) session.getAttribute("locale");
//		Locale locale;
//		switch (localeName){
//		case "ru_RU":
//			locale = new Locale("ru", "RU");
//			break;
//		case "en_US":
//			locale = new Locale("en", "US");
//			break;
//			default:
//				locale = Locale.getDefault();
//				break;
//		}
//		
//		// TODO bundle name
//		ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);

		
		JspWriter out = pageContext.getOut();
		try {
			out.write("<main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 pt-3 px-4\">");
			out.write("<div class=\"container\">");
			out.write("<div class=\"row\">");
			for (Model model : this.models) {
				out.write("<div class=\"card\" style=\"width: 18rem;\">");
				out.write("<div class=\"card-body\">");
				out.write("<h5 class=\"card-title\">" + model.getName() + "</h5>");
				out.write("<h6 class=\"card-price\">" + model.getPrice() + "</h6>");
				out.write("<p class=\"card-text\">" + model.getDescription() + "</p>");
				out.write("<p class=\"card-category\">" + model.getCategory() + "</p>");
				out.write("<p class=\"card-text\">" + model.getProducer() + "</p>");
				out.write("<p class=\"card-text\">" + model.getProducer() + "</p>");
				out.write("<p>");
				out.write("<a href=\"controller?command=add_to_cart&modelId=" + model.getId()+ "\" class=\"btn btn-primary\">Добавить в корзину</a>");
				out.write("</p>");
				out.write("</div>");
				out.write("</div>");
			}
			out.write("</div>");
			out.write("<center>");
			String categoryParam = category == null || category.isEmpty() ? "" : "&category=" + category;
			if (pageCount != 1) {
				for (int i = 1; i <= pageCount; i++) {
					String active = i == currentPage ? " active" : "";
					out.append("<a class=\"btn btn-primary btn-sm" + active + "\" href=\"controller?command=GET_MAIN_ALL_MODELS_OR_BY_CATEGORY_PAGE" + 
							categoryParam + "&pageNumber=" + i
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
