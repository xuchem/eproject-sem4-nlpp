/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.dao;

import java.util.List;

/**
 *
 * @author SVN - Team
 */
public interface IObjectDAO<T> {

    public List<T> findAll();

    public T findById(int id);

    public List<T> findByName(String name);

    public boolean saveObject(T t);

    public boolean removeObject(int id);

    public boolean updateObject(T t);

    public int count();
}
