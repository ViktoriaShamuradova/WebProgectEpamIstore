package by.epamtc.shamuradova.ishop.service;

import java.util.List;

import by.epamtc.shamuradova.ishop.bean.Model;

public interface ModelService {

	public List<Model> listAllModels(int page, int imit);
}
