package com.example.demo.editor;

import com.example.demo.model.Isbn;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by Wu.Kang on 2017/6/16.
 */
public class IsbnEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasText(text)) {
            setValue(new Isbn(text));
        }else {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        Isbn isbn = (Isbn)getValue();
        if(isbn != null) {
            return isbn.getIsbn();
        }else {
            return "";
        }
    }
}
