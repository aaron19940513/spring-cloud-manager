package com.springcloud.book.consumer.service;

import java.util.concurrent.Future;

import com.springcloud.book.consumer.model.Animal;

public interface ICollapsingService {
    public Future<Animal> collapsing(Integer id);

    public Animal collapsingSyn(Integer id);

    public Future<Animal> collapsingGlobal(Integer id);

}
