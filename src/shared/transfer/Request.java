package shared.transfer;

import java.io.Serializable;

public class Request implements Serializable
{
  private String requestType;
  private Object requestArg;

  public Request(String requestType, Object requestArg)
  {
    this.requestType = requestType;
    this.requestArg = requestArg;
  }

  public String getRequestType()
  {
    return requestType;
  }

  public Object getRequestArg()
  {
    return requestArg;
  }
}
