package com.springmvcproject2.demo.service;

import com.springmvcproject2.demo.model.Spittle;

import java.util.List;

public interface ISpittleRepository {
    List<Spittle> findSpittles(long max, int count);
}
