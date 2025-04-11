package org.example.dailydriver.service;

import java.util.List;

public interface CrudService<C, U, D, I> {

    Boolean save(C entity);

    D update(C entity, I id);

    Boolean delete(I id);

    D findById(I id);

    List<D> findAll();

}
