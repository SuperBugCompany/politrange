package com.example.nortti.politrange.intefaces;

import java.util.ArrayList;

public interface ICatalog<T> {

    ArrayList <T> getCatalogList();
    void populateData();

}