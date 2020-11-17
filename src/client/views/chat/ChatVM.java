package client.views.chat;

import client.model.ClientModel;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transfer.User;
import shared.transfer.UserAction;
import shared.util.Subject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ChatVM implements Subject
{
  private StringProperty messageToSendTextProperty;
  private StringProperty chatTextAreaProperty;
  private ObservableList<User> users;
  private ClientModel clientModel;
  private PropertyChangeSupport support;


  public ChatVM(ClientModel clientModel)
  {
    support = new PropertyChangeSupport(this);
    this.clientModel = clientModel;
    messageToSendTextProperty = new SimpleStringProperty();
    chatTextAreaProperty = new SimpleStringProperty();
    clientModel.addListener(UserAction.NEW_ACTIVE_USERS.toString(), this::getOnlineUserList);
    clientModel.addListener(UserAction.RECEIVE_MESSAGE.toString(), this::onNewReceivedMessage);
    clientModel.addListener(UserAction.REMOVE_USER.toString(), this::logoutUser);
  }

  private void logoutUser(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  private void onNewReceivedMessage(PropertyChangeEvent evt)
  {
    support.firePropertyChange(evt);
  }

  private void getOnlineUserList(PropertyChangeEvent evt)
  {
    List<User> tempUsers = (List<User>) evt.getNewValue();
    users = FXCollections.observableArrayList(tempUsers);
    for (int i = 0; i < tempUsers.size(); i++)
    {
      System.out.println(tempUsers.get(i).getUsername());
    }
    support.firePropertyChange(UserAction.NEW_ACTIVE_USERS.toString(), null, users);
  }

  public Property<String> messageToSendTextProperty()
  {
    return messageToSendTextProperty;
  }

  public Property<String> chatTextAreaProperty()
  {
    return chatTextAreaProperty;
  }

  public void getUsers()
  {
    clientModel.getUsers();
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

  public void sendMessageToChatView()
  {
    clientModel.sendMessage(messageToSendTextProperty.get());
  }

  public void logoutUser()
  {
    clientModel.logoutUser();
  }
}
