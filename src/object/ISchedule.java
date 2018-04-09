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
public interface ISchedule extends Remote {
    public String getScheduleID() throws RemoteException;;
    public void setScheduleID(String ScheduleID) throws RemoteException;;
    public String getDay() throws RemoteException;;
    public void setDay(String Day) throws RemoteException;;
    public String getStartTime() throws RemoteException;;
    public void setStartTime(String StartTime) throws RemoteException;; 
    public String getEndTime() throws RemoteException;;
    public void setEndTime(String EndTime) throws RemoteException;;
    public int getUKMPlaceID() throws RemoteException;;
    public void setUKMPlaceID(int UKMPlaceID) throws RemoteException;; 
    public String getUKMName() throws RemoteException;;
    public void setUKMName(String UKMName) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;  
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord() throws RemoteException;;
    public ArrayList displayviewschedule() throws RemoteException;;
    public String getPlaceName() throws RemoteException;;
    public void setPlaceName(String PlaceName) throws RemoteException;;
    public String getCoachName() throws RemoteException;;
    public void setCoachName(String CoachName) throws RemoteException;;
    public String getDescription() throws RemoteException;;
    public void setDescription(String Description) throws RemoteException;;
}
