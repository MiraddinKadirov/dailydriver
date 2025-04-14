package org.example.dailydriver.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CrudService<C, U, D, I> {

    D save(C entity);

    D update(U entity, I id);

    Boolean delete(I id);

    D findById(I id);

    List<D> findAll();

    default List<D> findAll(I id) {
        return null;
    }

    default D save(C entity, List<MultipartFile> files){
        return null;
    };

}
