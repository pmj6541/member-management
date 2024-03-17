package com.practice.manage.membermanage.main.repository;

import com.practice.manage.membermanage.main.domain.Refund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RefundMapper {
    List<Refund> getRefundList(String uid, Integer reportId, Integer from, Integer size);
    Refund getRefund(String uid, Integer refundId);
    List<Refund> getAllRefundList(String uid, Integer from, Integer size);
    void postRefund(String uid, Refund refund);
    void putRefund(String uid, Integer refundId, Refund refund);
    void deleteRefund(String uid, Integer refundId);
}
