package com.practice.manage.membermanage.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
public class MemberManageProperties {
	@Value("${member.api.key}")
	private String apiKey;
	
	@Value("${member.api.url}")
	private String apiUrl;

}
