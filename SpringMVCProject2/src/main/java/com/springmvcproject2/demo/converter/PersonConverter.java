package com.springmvcproject2.demo.converter;

import com.springmvcproject2.demo.model.Person;
import org.springframework.core.convert.converter.Converter;

public class PersonConverter implements Converter<String, Person> {
    @Override
    public Person convert(String s) {
        if(s.equals("孙悟空")){
            return new Person("孙悟空","猴子",1000);
        }
        return null;
    }
}
