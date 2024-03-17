package com.practice.manage.membermanage.main.service.impl;

import com.practice.manage.membermanage.main.domain.Expense;
import com.practice.manage.membermanage.main.repository.ExpenseMapper;
import com.practice.manage.membermanage.main.repository.ReportMapper;
import com.practice.manage.membermanage.main.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseMapper expenseMapper;
    private final ReportMapper reportMapper;

    @Override
    public List<Expense> getExpenseList(String uid, Integer reportId, Integer from, Integer size) {
        if(reportId == -1){
            return expenseMapper.getAllExpenseList(uid, from, size);
        }else {
            return expenseMapper.getExpenseList(uid, reportId, from, size);
        }
    }

    @Override
    public Expense getExpense(String uid, Integer expenseId) {
        return expenseMapper.getExpense(uid, expenseId);
    }

    @Override
    public void postExpense(String uid, Expense expense) {
        reportMapper.setStatusInProgress(expense.getReportId());
        expenseMapper.postExpense(uid, expense);
    }

    @Override
    public void putExpense(String uid, Integer expenseId, Expense expense) {
        expenseMapper.putExpense(uid, expenseId, expense);
    }

    @Override
    public void deleteExpense(String uid, Integer expenseId) {
        expenseMapper.deleteExpense(uid, expenseId);
    }
}
