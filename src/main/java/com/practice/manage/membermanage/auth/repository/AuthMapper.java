package com.practice.manage.membermanage.auth.repository;

import com.practice.manage.membermanage.auth.service.domain.Keyset;
import com.practice.manage.membermanage.auth.service.domain.Status;
import com.practice.manage.membermanage.auth.service.domain.User;
import com.practice.manage.membermanage.auth.service.domain.UserRefresh;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    Keyset getKeysetByProject(String project);

    User getUserByUid(String uid);

    void setUserCodeByUid(String uid, String code);

    void addUser(String uid, String displayName, String phoneNumber, Status status);

    void setRefreshToken(UserRefresh userRefresh);

    String getUidByRefreshTokenAndTime(String refreshToken, Long now);
}
