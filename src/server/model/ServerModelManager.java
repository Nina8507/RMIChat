package server.model;

import shared.transfer.Message;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ServerModelManager implements ServerModel
{
  private PropertyChangeSupport support;
  private List<User> users;

  public ServerModelManager()
  {
    support = new PropertyChangeSupport(this);
    users = new ArrayList<>();
    users.add(new User("first", "sdfs"));
    users.add(new User("second", "sdfs"));
  }

  @Override public void addUserToList(User requestArg)
  {
    users.add(requestArg);
    support.firePropertyChange(UserAction.NEW_ACTIVE_USERS.name(), null, users);
  }

  @Override public void getOnlineUsers()
  {
    support.firePropertyChange(UserAction.NEW_ACTIVE_USERS.toString(), null, new ArrayList<>(users));
  }

  @Override public void broadcastMessage(Message message)
  {
    support.firePropertyChange(UserAction.SEND_MESSAGE.toString(), null, message);
  }

  @Override public void logoutUser(User requestArg)
  {
    users.remove(requestArg);
    support.firePropertyChange(UserAction.NEW_ACTIVE_USERS.toString(), null, users);
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
