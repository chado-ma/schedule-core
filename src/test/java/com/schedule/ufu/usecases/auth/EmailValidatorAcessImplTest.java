package com.schedule.ufu.usecases.auth;

import com.schedulecore.ufu.domains.ports.EmailSenderPort;
import com.schedulecore.ufu.domains.usecases.auth.EmailValidatorAcessImpl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailValidatorAcessImplTest {

    @Mock
    private EmailSenderPort emailSenderPort;

    @InjectMocks
    private EmailValidatorAcessImpl emailValidatorAcessImpl;

    @BeforeEach
    void setup() throws Exception {
        setPrivateField(emailValidatorAcessImpl, "subjectAuth", "[AUTH] ");
        setPrivateField(emailValidatorAcessImpl, "bodyAuth", "Seu código é: ");
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    public void testSendValidationEmailSuccess() {
        String email = "user@test.com";
        doNothing().when(emailSenderPort).sendEmail(anyString(), anyString(), anyString());
        emailValidatorAcessImpl.sendValidationEmail(email);

        verify(emailSenderPort, times(1)).sendEmail(
                eq(email),
                any(),
                any()
        );
    }

    @Test
    public void testSendValidationEmailAlreadyExists() {
        String email = "user@test.com";

        doNothing().when(emailSenderPort).sendEmail(anyString(), anyString(), anyString());
        emailValidatorAcessImpl.sendValidationEmail(email);

        emailValidatorAcessImpl.sendValidationEmail(email);

        verify(emailSenderPort, times(1)).sendEmail(any(), any(), any());
    }

    @Test
    public void testSendValidationEmailNullThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            emailValidatorAcessImpl.sendValidationEmail(null);
        });
    }

    @Test
    public void testSendValidationEmailEmptyThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            emailValidatorAcessImpl.sendValidationEmail("");
        });
    }

    @Test
    public void testExecuteValidCodeRemovesEntry()  {
        String email = "user@test.com";
        doNothing().when(emailSenderPort).sendEmail(anyString(), anyString(), anyString());
        // Adiciona manualmente o código ao mapa
        emailValidatorAcessImpl.sendValidationEmail(email);
        // Recupera o código gerado
        String codigoGerado = getCodigoDoMapa(email);
        Assertions.assertDoesNotThrow(() -> emailValidatorAcessImpl.execute(email, codigoGerado));
    }

    @Test
    public void testExecuteInvalidCodeThrowsException()  {
        String email = "user@test.com";
        doNothing().when(emailSenderPort).sendEmail(anyString(), anyString(), anyString());
        emailValidatorAcessImpl.sendValidationEmail(email);
        Assertions.assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
            emailValidatorAcessImpl.execute(email, "123456");
        });
    }

    private String getCodigoDoMapa(String email) {
        try {
            Field field = EmailValidatorAcessImpl.class.getDeclaredField("emailCodeMap");
            field.setAccessible(true);
            Map<String, String> map = (Map<String, String>) field.get(emailValidatorAcessImpl);
            return map.get(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

