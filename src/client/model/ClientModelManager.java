package client.model;

import client.networking.Client;
import shared.transfer.Message;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ClientModelManager implements ClientModel
  {
    private PropertyChangeSupport support;
    private Client client;
    private User loggedInUser;

    public ClientModelManager(Client client)
  {
    this.client = client;
    client.startClient();
    support = new PropertyChangeSupport(this);
    client.addListener(UserAction.RECEIVE_MESSAGE.toString(), this::onNewMessage);
    client.addListener(UserAction.NEW_ACTIVE_USERS.toString(), this::getUserList);
    client.addListener(UserAction.REMOVE_USER.toString(), this::logoutUser);
  }

    private void logoutUser(PropertyChangeEvent evt)
    {
      support.firePropertyChange(evt);
    }

    private void getUserList(PropertyChangeEvent evt)
    {
      List<User> users = (List<User>) evt.getNewValue();
      for (int i = 0; i < users.size(); i++)
      {
        if(loggedInUser.getUsername().equals(users.get(i).getUsername()))
        {
          users.remove(i);
        }
      }
      support.firePropertyChange(evt.getPropertyName(), null, users);
    }

    private void onNewMessage(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);

  }

  @Override public void login(String username, String password)
  {
    System.out.println("login called");
    loggedInUser = new User(username, password);
    client.login(loggedInUser);
  }

    @Override public void getUsers()
    {
      client.getUserList();
    }

    @Override public void sendMessage(String messageContent)
    {
      Message messageToClient = new Message(messageContent, loggedInUser);
      client.sendMessageToServer(messageToClient);
    }

    @Override public void logoutUser()
    {
      client.logoutUser();
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
