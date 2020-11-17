package client.views.register;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterController implements ViewController
{
  @FXML private TextField enterUsernameTextField;
  @FXML private TextField enterPasswordTextField;
  @FXML private TextField reEnterPassTextField;
  @FXML private Button onCreateUserButton;

  private RegisterVM registerVm;
  private ViewHandler viewHandler;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.registerVm = viewModelFactory.getRegisterVM();
    this.viewHandler = viewHandler;
    enterPasswordTextField.textProperty().bindBidirectional(registerVm.enterUsernameProperty());
    enterPasswordTextField.textProperty().bindBidirectional(registerVm.enterPasswordProperty());
    reEnterPassTextField.textProperty().bindBidirectional(registerVm.reEnterPassProperty());
  }

  public void onCreateUserButton(ActionEvent actionEvent)
  {
    viewHandler.openChatView();
    registerVm.registerUser();
  }
}
