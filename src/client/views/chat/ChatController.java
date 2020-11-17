package client.views.chat;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.transfer.Message;
import shared.transfer.User;
import shared.transfer.UserAction;

import java.beans.PropertyChangeEvent;

public class ChatController implements ViewController
{
  @FXML private TableView<User> tableView;
  @FXML private TableColumn<User, String> activeUsersColumn;
  @FXML private TextField messageToSendTextFields;
  @FXML private TextArea chatTextArea;
  @FXML private Button onLogoutButton;

  private ViewHandler viewHandler;
  private ChatVM chatVM;

  public void onSendButton(ActionEvent actionEvent)
  {
    chatVM.sendMessageToChatView();
    messageToSendTextFields.clear();
  }
  public void onLogoutButton(ActionEvent actionEvent)
  {
    chatVM.logoutUser();
    viewHandler.openLoginView();
  }
  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.chatVM = viewModelFactory.getChatVM();
    messageToSendTextFields.textProperty().bindBidirectional(chatVM.messageToSendTextProperty());
    chatTextArea.textProperty().bindBidirectional(chatVM.chatTextAreaProperty());
    chatVM.addListener(UserAction.NEW_ACTIVE_USERS.toString(), this::onlineUsers);
    chatVM.getUsers();
    activeUsersColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
    chatVM.addListener(UserAction.RECEIVE_MESSAGE.toString(), this::onReceivedMessage);
    chatVM.addListener(UserAction.REMOVE_USER.toString(), this::onLogoutButton);
  }

  private void onLogoutButton(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      ObservableList<User> users = (ObservableList<User>) evt.getNewValue();
      tableView.getItems().remove(users.get((Integer) evt.getNewValue()));
    });
  }

  private void onReceivedMessage(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      Message message = (Message) evt.getNewValue();
      chatTextArea.appendText(message.getUser().getUsername() + ": " + message.getMessage() + "\n");
    });
  }

  private void onlineUsers(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      ObservableList<User> users = (ObservableList<User>) evt.getNewValue();
      tableView.getItems().clear();
      tableView.getItems().addAll(users);
    });
  }


}
