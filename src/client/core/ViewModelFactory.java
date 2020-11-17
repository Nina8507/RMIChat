package client.core;

import client.views.chat.ChatVM;
import client.views.login.LoginVM;
import client.views.register.RegisterVM;

public class ViewModelFactory
{
  private LoginVM loginVM;
  private RegisterVM registerVM;
  private ChatVM chatVM;
  private ModelFactory modelFactory;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
  }

  public LoginVM getLoginVM()
  {
    if(loginVM == null)
    {
      loginVM = new LoginVM(modelFactory.getClientModel());
    }
    return loginVM;
  }

  public RegisterVM getRegisterVM()
  {
    if(registerVM == null)
    {
      registerVM = new RegisterVM(modelFactory.getClientModel());
    }
    return registerVM;
  }
  public ChatVM getChatVM()
  {
    if(chatVM == null)
    {
      chatVM = new ChatVM(modelFactory.getClientModel());
    }
    return chatVM;
  }
}
