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
public interface IPlace extends Remote {
    public int getUKMPlaceID() throws RemoteException;;
    public void setUKMPlaceID(int UKMPlaceID) throws RemoteException;;
    public String getPlaceName() throws RemoteException;;
    public void setPlaceName(String PlaceName) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;  
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
}
