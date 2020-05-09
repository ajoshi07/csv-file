package com.sape.feecalculator.repo;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


import java.io.IOException;
import java.util.List;


public interface FileReadWriter {

     List readData(String fileName, Class c) throws IOException;
     void writeData(String fileName,List list,Class c) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;
}
