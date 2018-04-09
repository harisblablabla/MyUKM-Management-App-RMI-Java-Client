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
public interface IUKM extends Remote{
    public int getUKMID() throws RemoteException;;
    public void setUKMID(int UKMID) throws RemoteException;;
    public String getUKMName() throws RemoteException;;
    public void setUKMName(String UKMName) throws RemoteException;;
    public String getDescription() throws RemoteException;;
    public void setDescription(String Description) throws RemoteException;; 
    public int getCoachID() throws RemoteException;;
    public void setCoachID(int CoachID) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;  
    public ArrayList display() throws RemoteException;;
    public ArrayList displayinschedule() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
}
