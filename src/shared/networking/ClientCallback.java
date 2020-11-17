package shared.networking;

import shared.transfer.Message;
import shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientCallback extends Remote
{
  void userList(List<User> users) throws RemoteException;
  void sendMessage(Message message) throws RemoteException;
}
