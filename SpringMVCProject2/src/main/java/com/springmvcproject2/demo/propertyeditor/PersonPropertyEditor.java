package com.springmvcproject2.demo.propertyeditor;

import com.springmvcproject2.demo.model.Person;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class PersonPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            if(text.equals("孙悟空")){
                Person person= new Person("孙悟空","猴子",1000);
                setValue(person);
            }
        } else {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        Person person = (Person) getValue();
        if (person != null) {
            return person.getName();
        } else {
            return "";
        }
    }
}
