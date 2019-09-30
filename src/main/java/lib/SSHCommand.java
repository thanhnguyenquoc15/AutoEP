package lib;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHCommand extends ReadData{
    
 // Method is used to connect SSH
	public void Connect(String HostName, String UserName, String PassWord) {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(HostName, UserName);
			session.setPassword(PassWord);
			
		} catch (JSchException e) {
			
			e.printStackTrace();
		}
		
	}
}
