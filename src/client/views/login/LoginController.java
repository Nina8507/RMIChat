package client.views.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements ViewController
{
  @FXML private TextField usernameTextField;
  @FXML private TextField passwordTextField;
  @FXML private Button onLoginButton;
  @FXML private Button onRegisterButton;

  private ViewHandler viewHandler;
  private LoginVM loginVM;

  public void onLoginButton(ActionEvent actionEvent)
  {
    loginVM.login();
    viewHandler.openChatView();
  }

  public void onRegisterButton(ActionEvent actionEvent)
  {
  }

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.loginVM = viewModelFactory.getLoginVM();
    usernameTextField.textProperty()
        .bindBidirectional(loginVM.usernameProperty());
    passwordTextField.textProperty()
        .bindBidirectional(loginVM.passwordTextProperty());
  }
}
