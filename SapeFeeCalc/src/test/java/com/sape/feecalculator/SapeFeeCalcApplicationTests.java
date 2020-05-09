package com.sape.feecalculator;


import com.sape.feecalculator.repo.FileReadWriter;
import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionDTO;
import com.sape.feecalculator.beans.TransactionSummary;
import com.sape.feecalculator.utility.TransactionUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
public class SapeFeeCalcApplicationTests {

	@InjectMocks
	SapeFeeCalcApplication sapeFeeCalcApplication = new SapeFeeCalcApplication();

	@Mock
	FileReadWriter csvFileReadWrite;

	@Mock
	TransactionUtility utility;


	@Test
	public void csvTest() throws IOException, ParseException {
		List<Transaction> transactions=Mockito.mock(ArrayList.class);
		List<TransactionDTO> transactionSummaries1=Mockito.mock(ArrayList.class);
		List<TransactionSummary> sortedtransactionSummaries=Mockito.mock(ArrayList.class);
		List<TransactionSummary> groupedtransactionSummaries=Mockito.mock(ArrayList.class);
		List<TransactionSummary> transactionSummaries=Mockito.mock(ArrayList.class);
		String readPath="C:/mypersonal/Testsapient/Sample_Input.csv";
		String writepath="C:/mypersonal/Testsapient/Output_Input.csv";
		when(csvFileReadWrite.readData(readPath,Transaction.class)).thenReturn(transactions);
		when(utility.getSummary(transactions)).thenReturn(transactionSummaries);
		when(utility.groupDataOnField(transactionSummaries)).thenReturn(groupedtransactionSummaries);
		when(utility.sortDataOnField(groupedtransactionSummaries)).thenReturn(sortedtransactionSummaries);
		sapeFeeCalcApplication.performCalculation(readPath,writepath);

	}

	@Test(expected = FileNotFoundException.class)
	public void inputFileNotAvailTest() throws IOException, ParseException {
		List<Transaction> transactions=Mockito.mock(ArrayList.class);
        String readPath="";
        String writepath="C:/mypersonal/Testsapient/Output_Input.csv";
		when(csvFileReadWrite.readData(readPath,Transaction.class)).thenThrow(new FileNotFoundException());
		sapeFeeCalcApplication.performCalculation(readPath,writepath);

	}
    @Test
    public void utilityTest() throws IOException, ParseException {
        List<Transaction> transactions=Mockito.mock(ArrayList.class);
        List<TransactionDTO> transactionSummaries1=Mockito.mock(ArrayList.class);
        List<TransactionSummary> sortedtransactionSummaries=Mockito.mock(ArrayList.class);
        List<TransactionSummary> groupedtransactionSummaries=Mockito.mock(ArrayList.class);
        List<TransactionSummary> transactionSummaries=Mockito.mock(ArrayList.class);
        String readPath="C:/mypersonal/Testsapient/Sample_Input.csv";
        String writepath="C:/mypersonal/Testsapient/Output_Input.csv";
        when(csvFileReadWrite.readData(readPath,Transaction.class)).thenReturn(transactions);
        when(utility.getSummary(transactions)).thenReturn(transactionSummaries);
        sapeFeeCalcApplication.performCalculation(readPath,writepath);

    }

    }



