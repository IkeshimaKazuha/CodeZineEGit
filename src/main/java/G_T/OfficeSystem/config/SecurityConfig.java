/*package G_T.OfficeSystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	//デフォルトでlogin画面
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
		.authoritiesByUsernameQuery(
			"select id as username from user_master " +
			" where id = ?")
		.usersByUsernameQuery(
			"select id as username, name as password from user_master " +
			" where id = ?");
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");



    }



    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hoge").password("HOGE").roles("USER");
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("/sql/create_database.sql")
                .build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("SELECT LOGIN_ID, PASSWORD, ENABLED FROM USERS WHERE LOGIN_ID=?")
        .authoritiesByUsernameQuery("SELECT LOGIN_ID, ROLE FROM AUTHORITIES WHERE LOGIN_ID=?");
    }
    //オリジナルログイン画面
	@Override
	protected void configure(HttpSecurity web)throws Exception{


        web.authorizeRequests()
        .antMatchers("/Login").permitAll()
        .antMatchers("/my-login").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/my-login");

		web.formLogin().loginPage("/LoginTest").defaultSuccessUrl("/Find").failureUrl("/LoginTest").permitAll();
		web.authorizeRequests().antMatchers("/css/**", "/images/**", "/js/**").permitAll().anyRequest().authenticated();
		//web.formLogin().usernameParameter("user").passwordParameter("password");

	}


}*/