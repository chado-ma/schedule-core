package com.schedule.ufu.usecases.auth;

import com.schedule.ufu.fixtures.models.UserModelFixture;
import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import com.schedulecore.ufu.domains.usecases.auth.AuthenticateImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticateImpltest {

    @Mock
    private AuthPort authPort;

    @InjectMocks
    private AuthenticateImpl authenticateImpl;

    @Test
    public void testExecuteValidToken() {
        UserModel mockUser = UserModelFixture.builder()
                .matricula("123")
                .nome("testuser")
                .build().getMock();
        when(authPort.authenticateUser(anyString())).thenReturn(mockUser);
        UserModel result = authenticateImpl.execute("valid-token");

        verify(authPort).authenticateUser("valid-token");
        Assertions.assertNotNull(result);
        Assertions.assertEquals("123", result.getMatricula());
        Assertions.assertEquals("testuser", result.getNome());
    }

    @Test
    public void testExecuteNullTokenThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            authenticateImpl.execute(null);
        });
    }

    @Test
    public void testExecuteEmptyTokenThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            authenticateImpl.execute("");
        });
    }

    @Test
    public void testExecuteInvalidTokenThrowsAuthenticationException() {
        when(authPort.authenticateUser(anyString())).thenReturn(null);

        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
            authenticateImpl.execute("invalid-token");
        });

        verify(authPort).authenticateUser("invalid-token");
    }
}
