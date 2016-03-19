package mvc3.account;

import mvc3.config.WebSecurityConfigurationAware;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class UserAuthenticationIntegrationTest extends WebSecurityConfigurationAware {

    private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

    @Test
    public void requiresAuthentication() throws Exception {
        mockMvc.perform(get("/account/current"))
                .andExpect(redirectedUrl("http://localhost/signin"));
    }

    @Test
    public void userAuthenticates() throws Exception {
        final String username = AccountService.TEST_USERNAME;
        ResultMatcher matcher = mvcResult -> {
            HttpSession session = mvcResult.getRequest().getSession();
            SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
            Assert.assertEquals(securityContext.getAuthentication().getName(), username);
        };
        mockMvc.perform(post("/authenticate").param("username", username).param("password", "demo"))
                .andExpect(redirectedUrl("/"))
                .andExpect(matcher);
    }

    @Test
    public void userAuthenticationFails() throws Exception {
        final String username = AccountService.TEST_USERNAME;
        mockMvc.perform(post("/authenticate").param("username", username).param("password", "invalid"))
                .andExpect(redirectedUrl("/signin?error=1"))
                .andExpect(mvcResult -> {
                    HttpSession session = mvcResult.getRequest().getSession();
                    SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
                    Assert.assertNull(securityContext);
                });
    }
}
