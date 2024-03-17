package com.practice.manage.membermanage.main.service.impl;

import com.practice.manage.membermanage.main.repository.RefundMapper;
import com.practice.manage.membermanage.main.repository.ReportMapper;
import com.practice.manage.membermanage.main.service.RefundService;
import com.practice.manage.membermanage.main.domain.Refund;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    final RefundMapper refundMapper;
    final ReportMapper reportMapper;
    @Override
    public List<Refund> getRefundList(String uid, Integer reportId, Integer from, Integer size) {
        if(reportId == -1){
            return refundMapper.getAllRefundList(uid, from, size);
        }else {
            return refundMapper.getRefundList(uid, reportId, from, size);
        }
    }

    @Override
    public Refund getRefund(String uid, Integer refundId) {
        return refundMapper.getRefund(uid, refundId);
    }

    @Override
    public void postRefund(String uid, Refund refund) {
        reportMapper.setStatusInProgress(refund.getReportId());
        refundMapper.postRefund(uid, refund);
    }

    @Override
    public void putRefund(String uid, Integer refundId, Refund refund) {
        refundMapper.putRefund(uid, refundId, refund);
    }

    @Override
    public void deleteRefund(String uid, Integer refundId) {
        refundMapper.deleteRefund(uid, refundId);
    }
}
