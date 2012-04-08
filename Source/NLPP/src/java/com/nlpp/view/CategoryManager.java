/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.view;

import com.nlpp.bo.CategoryBO;
import com.nlpp.domain.Category;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author SVN - Team
 */
public class CategoryManager extends Category {

    /** Creates a new instance of CategoryManager */
    public CategoryManager() {
    }
    private CategoryBO managerCategory;
    
    private Category selectCategory;

    public Category getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(Category selectCategory) {
        this.selectCategory = selectCategory;
    }

    public CategoryBO getManagerCategory() {
        return managerCategory;
    }

    public void setManagerCategory(CategoryBO managerCategory) {
        this.managerCategory = managerCategory;
    }

    public List<Category> getAllCategory() {
        return managerCategory.findAll();
    }

    public String saveCategory() {
        Category cat = new Category();
        cat.setCatName(this.getCatName());
        System.out.println("this.getName():" + this.getCatName());
        boolean flag = managerCategory.saveCategory(cat);
        return "viewCategorys";
    }

    public String removeCategory() {
        Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = m.get("id").toString();
        int catId = Integer.parseInt(id);
        boolean flag = managerCategory.removeCategory(catId);
        return "viewCategorys";
    }

    public String updateCategory() {
        Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idx = m.get("id").toString();
        int id = Integer.parseInt(idx);
        String name = this.getCatName();
        System.out.println(id + "_" + name);
        Category cat = new Category();
        cat.setCatId(id);
        cat.setCatName(name);
        managerCategory.updateCategory(cat);
        return null;
    }
}
