package com.sape.feecalculator.config;

import com.sape.feecalculator.calculator.FeeCalculation;
import com.sape.feecalculator.calculator.IntraDayTransactionCalculatorImpl;
import com.sape.feecalculator.calculator.NormalTransactionCalculatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

    @Bean(value ="intraDayFeeCalc")
    public FeeCalculation intraDayFeeCalc() {
        return new FeeCalculation(new IntraDayTransactionCalculatorImpl());
    }
    @Bean(value = "normalDayFeeCalc")
    public FeeCalculation normalDayFeeCalc() {
        return new FeeCalculation(new NormalTransactionCalculatorImpl());
    }
}


