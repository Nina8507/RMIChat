package server.model;

import shared.transfer.Message;
import shared.transfer.User;
import shared.util.Subject;

public interface ServerModel extends Subject
{
  void addUserToList(User requestArg);
  void getOnlineUsers();
  void broadcastMessage(Message message);
  void logoutUser(User requestArg);
}
