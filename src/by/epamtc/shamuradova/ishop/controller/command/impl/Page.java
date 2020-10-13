package by.epamtc.shamuradova.ishop.controller.command.impl;

import java.util.List;

public class Page<TYPE> {
	private List<TYPE> result;
	private int totalCount;
	private int currentPage;

	public Page() {
	}

	public List<TYPE> getResult() {
		return result;
	}

	public void setResult(List<TYPE> result) {
		this.result = result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
