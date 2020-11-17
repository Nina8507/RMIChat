package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transfer.Message;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIClient implements Client, ClientCallback
{

  private RMIServer server;
  private PropertyChangeSupport support;
  private User userLoggedIn;

  public RMIClient()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("RMIServer");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void login(User user)
  {
    try
    {
      server.registerClientForUserList(this);
      System.out.println("register user for list");
      server.registerClientForBroadcast(this);
      System.out.println("register user for message");
      userLoggedIn = user;
      server.addUserToList(user);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void sendMessageToServer(Message message)
  {
    try
    {
      server.broadcastMessage(message);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void getUserList()
  {
    try
    {
      server.getOnlineUsers();
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void logoutUser()
  {
    try
    {
      server.logoutUser(userLoggedIn, this);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void userList(List<User> users) throws RemoteException
  {
    support.firePropertyChange(UserAction.NEW_ACTIVE_USERS.toString(), null, users);
  }

  @Override public void sendMessage(Message message) throws RemoteException
  {
    support.firePropertyChange(UserAction.RECEIVE_MESSAGE.toString(), null, message);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addListener(String name,
      PropertyChangeListener listener)
  {
    if (name == null || "".equals(name))
      addListener(listener);
    else
    {
      support.addPropertyChangeListener(name, listener);
    }
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removeListener(String name,
      PropertyChangeListener listener)
  {
    if (name == null || "".equals(name))
      removeListener(listener);
    else
    {
      support.removePropertyChangeListener(name, listener);
    }
  }
}
