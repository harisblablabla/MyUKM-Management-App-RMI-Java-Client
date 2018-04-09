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
public interface ICoach2 extends Remote{
    public int getCoachID() throws RemoteException;;
    public void setCoachID(int CoachID) throws RemoteException;;
    public String getCoachName() throws RemoteException;;
    public void setCoachName(String CoachName) throws RemoteException;;
    public String getCoachGender() throws RemoteException;;
    public void setCoachGender(String CoachGender) throws RemoteException;; 
    public String getCoachAddress() throws RemoteException;;
    public void setCoachAddress(String CoachAddress) throws RemoteException;;
    public String getCoachPhoneNumber() throws RemoteException;;
    public void setCoachPhoneNumber(String CoachPhoneNumber) throws RemoteException;; 
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;  
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
}
