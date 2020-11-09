package by.epamtc.shamuradova.ishop.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.epamtc.shamuradova.ishop.bean.entity.Model;

/**
 * Пользовательский тег, предназначенный для отображения моделей Поля класса:
 * List<Model> models - список моделей int totalModels - количество всех моделей
 * int currentPage - текущая страница, которую нужно отобразить int
 * modelsPerPage - количество моделей, которые нужно отобразить на странице
 * String category - какой категории нужно отобразить модели
 * 
 * 
 * @author Виктория Шамурадова 2020
 */

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
		int pageCount = countNumberOfPages(totalModels, modelsPerPage);

		JspWriter out = pageContext.getOut();
		try {
			out.write("<main role=\"main\" class=\"col-md-9 ml-sm-auto col-lg-10 pt-3 px-4\">");
			out.write("<div class=\"container\">");
			out.write("<div class=\"row\">");
			for (Model model : this.models) {
				out.write("<div class=\"card-group\">");
				out.write("<div class=\"card\" style=\"width: 18rem;\">");
				out.write("<img style=\"width: 287px; height: 250px;\" src=\"" + model.getImageLink() + "\">");
				out.write("<div class=\"card-body\">");
				out.write("<h5 class=\"card-title\">" + model.getName() + "</h5>");
				out.write("<h6 class=\"card-price\">" + model.getPrice() + "</h6>");
				out.write("<p class=\"card-text\">" + model.getDescription() + "</p>");

				out.write("<p class=\"card-text\"><small class=\"text-muted\">" + model.getProducer() + "</small></p>");

				out.write("</div>");
				out.write("<p>");
				out.write("<a href=\"controller?command=add_to_cart&modelId=" + model.getId()
						+ "\" class=\"btn btn-secondary \" style=\"width: 200px;\">Добавить в корзину</a>");
				out.write("</p>");
				out.write("</div>");
				out.write("</div>");
			}
			out.write("</div>");
			out.write("<center>");
			out.write("<div class=\"container mb-5 mt-5\">");
			String categoryParam = category == null || category.isEmpty() ? "" : "&category=" + category;
			if (pageCount != 1) {
				for (int i = 1; i <= pageCount; i++) {
					String active = i == currentPage ? " active" : "";
					out.append("<a class=\"btn btn-secondary btn-sm " + active
							+ "\" href=\"controller?command=ALL_MODELS_OR_BY_CATEGORY" + categoryParam + "&pageNumber="
							+ i + "\">" + i + " </a> ");
				}
			}
			out.write("</div>");
			out.write("</center>");
			out.write("</div>");
			out.write("</main>");
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

	private int countNumberOfPages(int totalModels, int modelsPerPage) {
		return (int) Math.ceil((double) totalModels / modelsPerPage);
	}
}
