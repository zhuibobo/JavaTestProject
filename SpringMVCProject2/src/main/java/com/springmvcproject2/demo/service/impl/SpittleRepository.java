package com.springmvcproject2.demo.service.impl;

import com.springmvcproject2.demo.model.Spittle;
import com.springmvcproject2.demo.service.ISpittleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpittleRepository implements ISpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }
}
