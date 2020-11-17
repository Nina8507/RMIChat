package shared.transfer;

import java.io.Serializable;

public class Message implements Serializable
{
  private String message;
  private User user;

  public Message(String message, User user)
  {
    this.message = message;
    this.user = user;
  }

  public String getMessage()
  {
    return message;
  }

  public User getUser()
  {
    return user;
  }
}
