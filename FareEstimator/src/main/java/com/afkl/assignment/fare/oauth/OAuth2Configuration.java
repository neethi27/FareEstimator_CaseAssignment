package com.afkl.assignment.fare.Oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * oauth2 configuration class
 * @author x087721
 *
 */
@Configuration
@EnableWebSecurity
public class OAuth2Configuration extends WebSecurityConfigurerAdapter {

    @Value("${afkl.api.tokenUrl}")
    private String tokenUrl;

    @Value("${afkl.api.clientId}")
    private String clientId;

    @Value("${afkl.api.secret}")
    private String secret;

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
    }

    /**
     * application.yml
     * 
     * @return
     */
    @Bean
    public OAuth2RestTemplate restTemplate() {
        final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(tokenUrl);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(secret);
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(Arrays.asList("read", "write"));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        return new OAuth2RestTemplate(resourceDetails, clientContext);
    }

}
	
