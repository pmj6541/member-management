package com.practice.manage.membermanage.main.repository;

import com.practice.manage.membermanage.main.domain.Expense;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpenseMapper {
    List<Expense> getExpenseList(String uid, Integer reportId, Integer from, Integer size);

    Expense getExpense(String uid, Integer expenseId);

    List<Expense> getAllExpenseList(String uid, Integer from, Integer size);

    void postExpense(String uid, Expense expense);

    void putExpense(String uid, Integer expenseId, Expense expense);

    void deleteExpense(String uid, Integer expenseId);
}
