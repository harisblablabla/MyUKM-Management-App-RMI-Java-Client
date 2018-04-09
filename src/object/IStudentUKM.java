/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Haris
 */
public interface IStudentUKM extends Remote {
    public String getUname() throws RemoteException;;
    public void setUname(String Uname) throws RemoteException;;
    public String getUKMName() throws RemoteException;;
    public void setUKMName(String UKMName) throws RemoteException;;
    public int doInsertpending() throws RemoteException;;
    public int dobidubidu() throws RemoteException;;
    public int doInsert() throws RemoteException;;
    //public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;  
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
    public String[] cekakses() throws RemoteException;;
}
