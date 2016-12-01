package dk.fitfit.liftlog.config;

import dk.fitfit.liftlog.security.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final AuthenticationTokenFilter tokenFilter;

	@Autowired
	public SecurityConfiguration(AuthenticationTokenFilter tokenFilter) {
		this.tokenFilter = tokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class)
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/login**").permitAll()
				.anyRequest().authenticated()
				.and()
				.logout().logoutSuccessUrl("/").permitAll()
				.and()
				.csrf().csrfTokenRepository(new CookieCsrfTokenRepository())
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));
	}
}
