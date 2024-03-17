package com.practice.manage.membermanage.main.service;

import com.practice.manage.membermanage.main.domain.Refund;

import java.util.List;

public interface RefundService {
    List<Refund> getRefundList(String uid, Integer reportId, Integer from, Integer size);

    Refund getRefund(String uid, Integer refundId);

    void postRefund(String uid, Refund refund);

    void putRefund(String uid, Integer refundId, Refund refund);

    void deleteRefund(String uid, Integer refundId);


}
