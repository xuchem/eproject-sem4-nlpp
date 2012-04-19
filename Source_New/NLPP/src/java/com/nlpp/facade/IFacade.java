/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.facade;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Hung
 */
public interface IFacade<TSource, TKey extends Serializable> {

    public List<TSource> findAll();

    public TSource findByKey(TKey key);

    public boolean saveOrUpdate(TSource t);

    public boolean remove(TSource t);

    public boolean removeByKey(TKey key);

    public int count();
}
