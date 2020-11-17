package client.views.register;

import client.model.ClientModel;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegisterVM
{
  private ClientModel clientModel;
  private StringProperty enterUsername, enterPassword, reEnterPass;

  public RegisterVM(ClientModel clientModel)
  {
    this.clientModel = clientModel;
    enterPassword = new SimpleStringProperty();
    enterUsername = new SimpleStringProperty();
    reEnterPass = new SimpleStringProperty();
  }

  public Property<String> enterUsernameProperty()
  {
    return enterUsername;
  }

  public Property<String> enterPasswordProperty()
  {
    return enterPassword;
  }

  public Property<String> reEnterPassProperty()
  {
    return reEnterPass; // an if() maybe?? or switch??
  }

  public void registerUser()
  {

  }
}
