package com.sape.feecalculator;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.sape.feecalculator.config.FileConfiguration;
import com.sape.feecalculator.repo.FileReadWriter;
import com.sape.feecalculator.beans.Transaction;
import com.sape.feecalculator.beans.TransactionDTO;
import com.sape.feecalculator.beans.TransactionSummary;
import com.sape.feecalculator.converter.BeanTransformation;
import com.sape.feecalculator.utility.TransactionUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@SpringBootApplication
public class SapeFeeCalcApplication {

	@Autowired
	@Qualifier(value = "csvFileReadWriteImpl")
	private FileReadWriter csvFileReadWrite;

	@Autowired
	private TransactionUtility utility;

	@Autowired
	private static FileConfiguration fileConfiguration;


	static private SapeFeeCalcApplication SapeFeeCalcApplication;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SapeFeeCalcApplication.class,args);
		SapeFeeCalcApplication.performCalculation(fileConfiguration.getReadFilePath(),fileConfiguration.getWriteFilePath());
	}

	public void performCalculation(String readPath,String writePath) throws IOException {
		try {
			List<Transaction> transactions = csvFileReadWrite.readData(readPath, Transaction.class);
			if(!CollectionUtils.isEmpty(transactions)) {
				List<TransactionSummary> transactionSummaries = utility.getSummary(transactions);
				List<TransactionSummary> groupedtransactionSummaries = utility.groupDataOnField(transactionSummaries);
				List<TransactionSummary> sortedtransactionSummaries = utility.sortDataOnField(groupedtransactionSummaries);
				List<TransactionDTO> transactionSummaries1 = BeanTransformation.getSummaryDTO(sortedtransactionSummaries);
				csvFileReadWrite.writeData(writePath, transactionSummaries1, TransactionDTO.class);
			}
		}  catch (CsvRequiredFieldEmptyException e) {
			e.printStackTrace();
		} catch (CsvDataTypeMismatchException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Autowired
	private void setSapeFeeCalcApplication(SapeFeeCalcApplication sapeFeeCalcApplication)
	{
		this.SapeFeeCalcApplication=sapeFeeCalcApplication;
	}
	@Autowired
	private void setFileConfiguration(FileConfiguration fileConfiguration)
	{
		this.fileConfiguration=fileConfiguration;
	}
	}



