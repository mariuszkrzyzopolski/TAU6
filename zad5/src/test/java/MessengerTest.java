import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class MessengerTest {

    private static final String CLIENT_EMAIL = "test@test.com";
    private static final String MSG_CONTENT = "Some new email";
    @Mock MailServer mailServer;
    @Spy Client client;
    @Mock TemplateEngine templateEngine;

    @InjectMocks Messenger mess;

    @Test
    public void shouldSendEmail() {
        Template temlateMock = mock(Template.class);
        when(client.getEmail()).thenReturn(CLIENT_EMAIL);
        when(templateEngine.prepareMessage(temlateMock, client)).thenReturn(MSG_CONTENT);

        mess.sendMessage(client, temlateMock);
        verify(mailServer).send(CLIENT_EMAIL, MSG_CONTENT);
    }

    @Test
    public void shouldSendEmptyEmail() {
        Template temlateMock = mock(Template.class);
        when(client.getEmail()).thenReturn(CLIENT_EMAIL);
        when(templateEngine.prepareMessage(temlateMock, client)).thenReturn("");

        mess.sendMessage(client, temlateMock);
        verify(mailServer).send(CLIENT_EMAIL, "");
    }
}
