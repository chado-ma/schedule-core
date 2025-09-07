package com.schedule.ufu.usecases.auth;

import com.schedule.ufu.fixtures.models.UserModelFixture;
import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.AuthPort;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.auth.GenerateAuthImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GenerateAuthImpltest {

    @Mock
    private DatabasePort databasePort;

    @Mock
    private AuthPort authPort;

    @InjectMocks
    private GenerateAuthImpl generateAuthImpl;

    @Test
    public void testExecuteValidUserGeneratesToken() {
        UserModel mockUser = UserModelFixture.builder()
                .matricula("123")
                .nome("testuser")
                .email("test@test.com")
                .build().getMock();
        when(databasePort.findUserByEmail("test@test.com"))
                .thenReturn(Optional.of(mockUser));
        when(authPort.generateAuthToken(mockUser))
                .thenReturn("token-123");

        String token = generateAuthImpl.execute(mockUser);

        verify(databasePort, times(1)).saveUserOrUpdateUser(mockUser);
        verify(databasePort, times(1)).findUserByEmail("test@test.com");
        verify(authPort, times(1)).generateAuthToken(mockUser);

        Assertions.assertEquals("token-123", token);
    }

    @Test
    public void testExecuteNullUserThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            generateAuthImpl.execute(null);
        });
    }

    @Test
    public void testExecuteUserWithNullEmailThrowsException() {
        UserModel mockUser = UserModelFixture.builder()
                .matricula("123")
                .nome("testuser")
                .email(null)
                .build().getMock();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            generateAuthImpl.execute(mockUser);
        });
    }

    @Test
    public void testExecuteUserWithEmptyEmailThrowsException() {
        UserModel mockUser = UserModelFixture.builder()
                .matricula("123")
                .nome("testuser")
                .email("")
                .build().getMock();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            generateAuthImpl.execute(mockUser);
        });
    }

    @Test
    public void testExecuteUserNotFoundThrowsException() {
        UserModel mockUser = UserModelFixture.builder()
                .matricula("123")
                .nome("testuser")
                .email("test@test.com")
                .build().getMock();

        when(databasePort.findUserByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            generateAuthImpl.execute(mockUser);
        });

        verify(databasePort).saveUserOrUpdateUser(mockUser);
        verify(databasePort).findUserByEmail("test@test.com");
        verify(authPort, never()).generateAuthToken(any());
    }
}
