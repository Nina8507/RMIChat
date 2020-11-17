package client.core;

import client.model.ClientModel;
import client.model.ClientModelManager;

public class ModelFactory
{
  private ClientModel clientModel;
  private ClientFactory clientFactory;

  public ModelFactory(ClientFactory clientFactory)
  {
    this.clientFactory = clientFactory;
  }

  public ClientModel getClientModel()
  {
    if (clientModel == null)
    {
      clientModel = new ClientModelManager(clientFactory.getClient());
    }
    return clientModel;
  }

}
