package server.networking;

import client.networking.RMIClient;
import server.model.ServerModel;
import shared.networking.ClientCallback;
import shared.transfer.Message;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIServerImpl implements shared.networking.RMIServer
{
  private ServerModel serverModelManager;
  private List<ClientCallback> clients;

  public RMIServerImpl(ServerModel serverModelManager) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    clients = new ArrayList<>();
    this.serverModelManager = serverModelManager;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("RMIServer", this);
  }

  @Override public void addUserToList(User requestArg)
  {
    serverModelManager.addUserToList(requestArg);
  }

  @Override public void getOnlineUsers()
  {
    serverModelManager.getOnlineUsers();
  }

  @Override public void broadcastMessage(Message message) throws RemoteException
  {
    for (ClientCallback client : clients)
    {
      System.out.println("message sent to user");
      client.sendMessage(message);
    }
  }

  @Override public void logoutUser(User user, ClientCallback clientCallback)
  {
    clients.remove(clientCallback);
    serverModelManager.logoutUser(user);
  }

  @Override public void registerClientForBroadcast(ClientCallback client)
      throws RemoteException
  {
    clients.add(client);
  }

  @Override public void registerClientForUserList(ClientCallback clientCallback)
      throws RemoteException
  {
    serverModelManager
        .addListener(UserAction.NEW_ACTIVE_USERS.toString(), evt -> {
          try
          {
            clientCallback.userList((List<User>) evt.getNewValue());
          }
          catch (RemoteException e)
          {
            e.printStackTrace();
          }
        });
  }

}
