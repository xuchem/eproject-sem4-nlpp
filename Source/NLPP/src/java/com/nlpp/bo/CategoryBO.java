/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.bo;

import com.nlpp.dao.CategoryDAO;
import com.nlpp.domain.Category;
import java.util.List;

/**
 *
 * @author SVN - Team
 */
public class CategoryBO {

    private CategoryDAO categoryDAO;

    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    public Category findById(int catId) {
        return categoryDAO.findById(catId);
    }

    public List<Category> findByName(String catName) {
        return categoryDAO.findByName(catName);
    }

    public boolean saveCategory(Category cat) {
        return categoryDAO.saveObject(cat);
    }

    public boolean updateCategory(Category cat) {
        return categoryDAO.updateObject(cat);
    }

    public boolean removeCategory(int catId) {
        return categoryDAO.removeObject(catId);
    }
}
