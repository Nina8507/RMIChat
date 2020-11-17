package shared.networking;

import client.networking.RMIClient;
import shared.transfer.Message;
import shared.transfer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  void addUserToList(User requestArg) throws RemoteException;
  void getOnlineUsers() throws  RemoteException;
  void broadcastMessage(Message message) throws RemoteException;
  void logoutUser(User user, ClientCallback clientCallback) throws RemoteException;
  void registerClientForBroadcast(ClientCallback client) throws RemoteException;
  void registerClientForUserList(ClientCallback clientCallback) throws RemoteException;
  //void unregisterClientFromBroadcast(ClientCallback clientCallback) throws RemoteException;
  //void unregisterClientFromUserList(ClientCallback clientCallback) throws RemoteException;
}
