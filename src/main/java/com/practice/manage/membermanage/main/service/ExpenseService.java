package com.practice.manage.membermanage.main.service;

import com.practice.manage.membermanage.main.domain.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getExpenseList(String uid, Integer reportId, Integer from, Integer size);

    Expense getExpense(String uid, Integer expenseId);

    void postExpense(String uid, Expense expense);

    void putExpense(String uid, Integer ExpenseId, Expense expense);

    void deleteExpense(String uid, Integer expenseId);


}
