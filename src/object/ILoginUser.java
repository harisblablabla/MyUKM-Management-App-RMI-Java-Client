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
 * @author Ryandika
 */
public interface ILoginUser extends Remote {
    public int getID() throws RemoteException;;
    public void setID(int ID) throws RemoteException;;
    public String getStudentName() throws RemoteException;;
    public void setStudentName(String StudentName) throws RemoteException;;
    public String getGender() throws RemoteException;;
    public void setGender(String Gender) throws RemoteException;; 
    public String getAddress() throws RemoteException;;
    public void setAddress(String Address) throws RemoteException;;
    public String getPhoneNumber() throws RemoteException;;
    public void setPhoneNumber(String PhoneNumber) throws RemoteException;;
    public String getFaculty() throws RemoteException;;
    public void setFaculty(String Faculty) throws RemoteException;;
    public String getMajor() throws RemoteException;;
    public void setMajor(String Major) throws RemoteException;;
    public String getBatch() throws RemoteException;;
    public void setBatch(String Batch) throws RemoteException;;
    public String getUname() throws RemoteException;;
    public void setUname(String Uname) throws RemoteException;;
    public String getPword() throws RemoteException;;
    public void setPword(String Pword) throws RemoteException;;
    public int doInsert() throws RemoteException;;
    public int doUpdate() throws RemoteException;;
    public int doDelete() throws RemoteException;;  
    public ArrayList display() throws RemoteException;;
    public ArrayList getRecord(String search) throws RemoteException;;
    public int doLogin() throws RemoteException;;
    public String[] caridataAdmin() throws RemoteException;;
    public String[] cekakses() throws RemoteException;;
    public String getUKMName() throws RemoteException;;
    public void setUKMName(String UKMName) throws RemoteException;;
     public ArrayList displayviewstudent() throws RemoteException;;
}
