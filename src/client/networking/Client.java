package client.networking;

import shared.transfer.Message;
import shared.transfer.User;
import shared.util.Subject;

public interface Client extends Subject
{
  void startClient();
  void login(User user);
  void sendMessageToServer(Message message);
  void getUserList();
  void logoutUser();

}
