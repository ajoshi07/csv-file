package com.sape.feecalculator.repo;


import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sape.feecalculator.beans.Transaction;
import org.springframework.stereotype.Component;


import java.io.*;
import java.util.List;


@Component(value = "csvFileReadWriteImpl")
public class CSVFileReadWriteImpl implements FileReadWriter{

    @Override
    public List<Transaction> readData(String fileName, Class c) throws IOException {
        Reader csvFileReader=new FileReader(fileName);
        ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
        mappingStrategy.setType(c);
        CsvToBean csvToBean = new CsvToBeanBuilder(csvFileReader)
                .withType(c)
                .withMappingStrategy(mappingStrategy)
                .withSkipLines(1)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List list=csvToBean.parse();
        return list;
    }

    @Override
    public void writeData(String fileName,List transactionList,Class c) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Writer csvWriter = new FileWriter(fileName);
        StatefulBeanToCsvBuilder csvBuilder = new StatefulBeanToCsvBuilder<>(csvWriter);
        ColumnPositionMappingStrategy strategy = new WriteColumnMapping();
        strategy.setType(c);
        StatefulBeanToCsv transactionSummaryStatefulBeanToCsv = csvBuilder
                .withMappingStrategy(strategy)
                .build();

        transactionSummaryStatefulBeanToCsv.write(transactionList);
        csvWriter.close();

    }
}
