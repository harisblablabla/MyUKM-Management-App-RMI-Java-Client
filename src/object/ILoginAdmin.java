/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Ryandika
 */
public interface ILoginAdmin extends Remote {
    public String getUsername() throws RemoteException;;
    public void setUsername(String Username) throws RemoteException;;
    public String getPassword() throws RemoteException;;
    public void setPassword(String password) throws RemoteException;;
    public int doLogin() throws RemoteException;;
    public String[] caridataAdmin() throws RemoteException;;
}
