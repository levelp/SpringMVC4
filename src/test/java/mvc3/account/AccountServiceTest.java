package mvc3.account;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @InjectMocks
    private AccountService accountService = new AccountService();
    @Mock
    private AccountRepository accountRepositoryMock;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void shouldInitializeWithTwoDemoUsers() {
        // act
        accountService.initialize();
        // assert
        verify(accountRepositoryMock, times(2)).save(any(Account.class));
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        // arrange
        thrown.expect(UsernameNotFoundException.class);
        thrown.expectMessage("user not found");

        when(accountRepositoryMock.findOneByEmail("user@example.com")).thenReturn(null);
        // act
        accountService.loadUserByUsername("user@example.com");
    }

    @Test
    public void shouldReturnUserDetails() {
        // arrange
        Account demoUser = new Account("user@example.com", "demo", "ROLE_USER");
        when(accountRepositoryMock.findOneByEmail("user@example.com")).thenReturn(demoUser);

        // act
        UserDetails userDetails = accountService.loadUserByUsername("user@example.com");

        // assert
        assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
        assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
        assertThat(hasAuthority(userDetails, demoUser.getRole()));
    }

    private boolean hasAuthority(UserDetails userDetails, String role) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) {
                return true;
            }
        }
        return false;
    }
}
