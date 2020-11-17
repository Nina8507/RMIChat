package client.views.login;

import client.model.ClientModel;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginVM
{
  private ClientModel clientModel;
  private StringProperty username, password;

  public LoginVM(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    username = new SimpleStringProperty();
    password = new SimpleStringProperty();
  }
  public Property<String> usernameProperty()
  {
    return username;
  }

  public Property<String> passwordTextProperty()
  {
    return password;
  }

  public void login()
  {
    clientModel.login(username.get(), password.get());
  }
}
