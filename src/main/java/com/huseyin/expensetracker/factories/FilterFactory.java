package com.huseyin.expensetracker.factories;

import com.huseyin.expensetracker.interfaces.ICriteria;
import com.huseyin.expensetracker.filters.Income;
import com.huseyin.expensetracker.filters.Outcome;

public class FilterFactory {

    //use getShape method to get object of type shape
    public ICriteria getFilter(String filterType){
        if(filterType == null){
            return null;
        }

        if(filterType.equalsIgnoreCase("income")){
            return new Income();

        } else if(filterType.equalsIgnoreCase("outcome")){
            return new Outcome();

        }

        return null;
    }
}