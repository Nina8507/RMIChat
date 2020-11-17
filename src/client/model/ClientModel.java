package client.model;

import shared.util.Subject;

public interface ClientModel extends Subject
{
  void login(String username, String password);
  void getUsers();
  void sendMessage(String messageContent);
  void logoutUser();
}
