package com.schedule.ufu.usecases;

import com.schedulecore.ufu.domains.models.UserModel;
import com.schedulecore.ufu.domains.ports.DatabasePort;
import com.schedulecore.ufu.domains.usecases.auth.GetUsersImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetUsersImplTest {

    @Mock
    private DatabasePort databasePort;

    @InjectMocks
    private GetUsersImpl getUsersImpl;

    @Test
    public void testExecuteReturnsUsersList() {
        // Criando usuários de teste
        UserModel user1 = UserModel.builder().nome("user1").build();
        UserModel user2 = UserModel.builder().nome("user2").build();

        List<UserModel> mockUsers = List.of(user1, user2);

        when(databasePort.findAllUsers()).thenReturn(mockUsers);

        List<UserModel> result = getUsersImpl.execute();

        verify(databasePort, times(1)).findAllUsers();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("user1", result.get(0).getNome());
        Assertions.assertEquals("user2", result.get(1).getNome());
    }

    @Test
    public void testExecuteReturnsEmptyList() {
        when(databasePort.findAllUsers()).thenReturn(List.of());

        List<UserModel> result = getUsersImpl.execute();

        verify(databasePort, times(1)).findAllUsers();
        Assertions.assertTrue(result.isEmpty());
    }


}
