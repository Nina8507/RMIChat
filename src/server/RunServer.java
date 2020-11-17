package server;

import server.model.ServerModelManager;
import server.networking.RMIServerImpl;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class RunServer
{
  public static void main(String[] args)
      throws RemoteException, AlreadyBoundException
  {
    RMIServerImpl rmiServer = new RMIServerImpl(new ServerModelManager());
    rmiServer.startServer();
  }
}
