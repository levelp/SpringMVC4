package mvc3.signup;

import mvc3.config.WebAppConfigurationAware;
import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignupControllerTest extends WebAppConfigurationAware {
    @Test
    public void displaysSignupForm() throws Exception {
        // Форма регистрации в системе
        mockMvc.perform(get("/signup"))
                // Ожидаем увидеть форму регистраии
                .andExpect(model().attributeExists("signupForm"))
                // С заданным view
                .andExpect(view().name("signup/signup"))
                // И на странице будет такой текст
                .andExpect(content().string(
                        allOf(
                                containsString("<title>Signup</title>"),
                                containsString("<legend>Please Sign Up</legend>")
                        ))
                );
    }
}