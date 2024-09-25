package vn.hcmute.elearning.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import vn.hcmute.elearning.enums.Role;

@KeycloakConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@ComponentScan(basePackageClasses = KeycloakSpringBootConfigResolver.class)
public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.configure(http);

        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(
                "/",
                "/*",
                "/webjars/**",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/api/auth/v1/**",
                "/api/auth/**",
                "/api/address/**",
                "/api/ekyc/portal/v1/ocr",
                "/api/file/v1/**",
                "/api/*/public/**",
                "/ws/**"
            )
            .permitAll()
            .antMatchers("/api/*/cms/**").hasAnyRole(Role.ADMIN.name(), Role.ROOT_ADMIN.name())
            .antMatchers("/api/*/teacher/**").hasRole(Role.TEACHER.name())
            .antMatchers("/api/*/portal/**").hasRole(Role.STUDENT.name())
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(new SimpleCORSFilter(), WebAsyncManagerIntegrationFilter.class)
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
            .and()
            .logout()
            .addLogoutHandler(keycloakLogoutHandler())
            .logoutUrl("/user/logout").permitAll()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID");
    }

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        SimpleAuthorityMapper simpleAuthorityMapper = new SimpleAuthorityMapper();
//        simpleAuthorityMapper.setPrefix("");
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(simpleAuthorityMapper);
        auth.authenticationProvider(keycloakAuthenticationProvider);

    }

    /**
     * Defines the session authentication strategy
     *
     * @return null
     */
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    protected AuthenticationEntryPoint authenticationEntryPoint() throws Exception {
        return new RestAuthenticationEntryPoint(adapterDeploymentContext());
    }
}
