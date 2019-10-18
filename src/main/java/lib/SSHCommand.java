package lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;


import org.testng.annotations.Test;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

/**
 * This generic class is used to create SSH session with unix server and executes command.
 *
 * @version 0.1  -  22/01/14   : AAPC Automation Team
 *
 * <font size="-2"> <br><br>
 * Copyright &copy; 2013 Avaya Inc., All Rights Reserved.
 * </br> </br> </font>
 *
 */
public class SSHCommand extends ReadData
{
	private Session session;

	private Channel channel;
	
	private ChannelSftp channelSftp;

	private JSch jsch;

	private UserInfo userInfo;

	private Properties config;

	public int iExitStatus;

	private String basePath;
	
	protected ThreadLocal<Session> threadSession;

	


	public SSHCommand()
	{
		jsch = new JSch();
		//varible let using
		JSch jsch = null;
		//ChannelSftp channelSftp = null;
		
		Session session = null;
		config = new Properties();
		iExitStatus = 0;
		
	}


	/**
	 * Connect to remote machine and establish session.
	 *
	 * @return if connect successfully, return true, else return false
	 */
	public Boolean  connect(String sHostName, String sUserName, String sPassword)
	{
		
		try
		{
			session = jsch.getSession(sUserName, sHostName);
			log.info("Session initialized with Host [" + sHostName + "] and associated with user ["
			         + sUserName + "] and credential " + sPassword);
			//session.setUserInfo(userInfo);
			session.setPassword(sPassword);
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			log.info("SSHExec initialized successfully");
			log.info("SSHExec trying to connect " + sUserName + "@" + sHostName);
			session.connect();
			log.info("SSH connection established");
			//modifier
			//channelSftp = (ChannelSftp) session.openChannel("sftp");
			//channelSftp.connect();
			
		
			return true;
		} catch (Exception e)
		{
			log.info("Connect fails with the following exception: " + e);
			return false;
		}
	}


	/**
	 * Disconnect to remote machine and destroy session.
	 *
	 * @return if disconnect successfully, return true, else return false
	 */
	public Boolean disconnect()
	{
		try
		{
			log.info("Closing SSH connection.");
			session.disconnect();
			session = null;
			iExitStatus = 0;
			log.info("SSH connection closed successfully.");
			return true;
		} catch (Exception e)
		{
			log.info("Disconnect fails with the following exception: " + e.toString());
			return false;
		}
	}


	/**
	 * Execute task on remote machine
	 *
	 * @param sCommand
	 *            - Task object that extends from CustomCode
	 * @throws Exception
	 */
	public synchronized String exec(String sCommand)
	{
		String sRetVal = "";
		try
		{
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(sCommand);
			channel.setInputStream(null);
			channel.setOutputStream(System.out);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			log.info("Connection channel established successfully");
			log.info("Start to run command: " + sCommand);
			StringBuilder sb = new StringBuilder();
			byte[] tmp = new byte[1024];
			Date startDate = new Date();
			int timeout = 800;
			while (true)
			{
				while (in.available() > 0)
				{
					Date endDate = new Date();
					long difference = (endDate.getTime() - startDate.getTime()) / 1000;
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					String str = new String(tmp, 0, i);
					sb.append(str);
					System.out.print(str);
					log.info(str);
					if (difference >= timeout)
					{
						log.info("timed out. command took more than expected");
						break;
					}
				}
				if (channel.isClosed())
				{
					log.info("Connection channel closed");
					//logger.putMsg(Logger.INFO,"Exit-status: " + channel.getExitStatus());
					log.info("Check if exec success or not ... ");
					iExitStatus = channel.getExitStatus();
					//r.sysout = sb.toString();
					if (iExitStatus == 0)
					{
						log.info("Execute successfully for command: " + sCommand);
						
					} else
					{
						log.info("Execution failed while executing command: " + sCommand);
						
						log.info("Error message: " + ((ChannelExec) channel).getErrStream());
					}
					break;
				}
				Date endDate = new Date();
				long difference = (endDate.getTime() - startDate.getTime()) / 1000;
				if (difference >= timeout)
				{
					log.info("timed out. command took more than expected");
					break;
				}
			}
			channel.disconnect();
			sRetVal = sb.toString();
		} catch (Exception e)
		{
		}
		return sRetVal;
	}


	public synchronized String execcmd(String sCommand) throws IOException,
	                                                    JSchException
	{
		String sRetVal = "";
		try
		{
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(sCommand);
			channel.setInputStream(null);
			channel.setOutputStream(System.out);
			((ChannelExec) channel).setErrStream(System.err);
			//InputStream in = channel.getInputStream();
			InputStream in = channel.getExtInputStream();
			channel.connect();
			log.info("Connection channel established successfully");
			log.info("Start to run command: " + sCommand);
			StringBuilder sb = new StringBuilder();
			byte[] tmp = new byte[1024];
			while (true)
			{
				while (in.available() > 0)
				{
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					String str = new String(tmp, 0, i);
					sb.append(str);
					System.out.print("result : " + str);
					log.info("result : " + str);
				}
				if (channel.isClosed())
				{
					System.out.print("Connection channel closed");
					log.info("Connection channel closed");
					break;
				}
			}
			channel.disconnect();
			sRetVal = sb.toString();
		} catch (Exception e)
		{
			log.info("in exception");
			System.out.print(e);
		}
		return sRetVal;
	}


	public synchronized String execWindows(String sCommand)
	{
		String sRetVal = "";
		try
		{
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(sCommand);
			channel.setInputStream(null);
			channel.setOutputStream(System.out);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			log.info("Connection channel established successfully");
			log.info("Start to run command: " + sCommand);
			StringBuilder sb = new StringBuilder();
			byte[] tmp = new byte[1024];
			Date startDate = new Date();
			int timeout = 60;
			while (true)
			{
				while (in.available() > 0)
				{
					Date endDate = new Date();
					long difference = (endDate.getTime() - startDate.getTime()) / 1000;
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					String str = new String(tmp, 0, i);
					sb.append(str);
					System.out.print(str);
					log.info(str);
					if (difference >= timeout)
					{
						log.info("timed out. command took more than expected");
						break;
					}
				}
				if (channel.isClosed())
				{
					log.info("Connection channel closed");
					//logger.putMsg(Logger.INFO,"Exit-status: " + channel.getExitStatus());
					log.info("Check if exec success or not ... ");
					iExitStatus = channel.getExitStatus();
					//r.sysout = sb.toString();
					if (iExitStatus == 0)
					{
						log.info("Execute successfully for command: " + sCommand);
	
					} else
					{
						log.info("Execution failed while executing command: " + sCommand);

						log.info("Error message: " + ((ChannelExec) channel).getErrStream());
					}
					break;
				}
				Date endDate = new Date();
				long difference = (endDate.getTime() - startDate.getTime()) / 1000;
				if (difference >= timeout)
				{
					log.info("timed out. command took more than expected");
					break;
				}
			}
			channel.disconnect();
			sRetVal = sb.toString();
		} catch (Exception e)
		{
		}
		return sRetVal;

	}
		

	/**
	 * sshConnectShell connect to remote machine and send multiple commands then
	 * exit.
	 * 
	 * @return console log, result of the sent commands
	 * @author nqthanh1
	 * @param single or multiple commands to send to Linux server
	 * @LastModifiedDate 11-Octorber-2019
	 * @LastModifiedBy nqthanh1
	 */

	public void sshShell(String... commands) throws Exception {

		log.info(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		Session session = jsch.getSession(ReadData.EPUser_SSH, ReadData.EPServer, 22);
		log.info("SSHExec trying to connect " + ReadData.EPUser_SSH + "@" + ReadData.EPServer);
		session.setPassword(ReadData.EPPass_SSH);
		session.setConfig(config);
		session.connect();
		Thread.sleep(2000);
		log.info("SSH connection established");
		Channel channel = session.openChannel("shell");
		OutputStream out = channel.getOutputStream();
		PrintStream ps = new PrintStream(out, true);
		// open channel
		channel.connect();
		InputStream input = channel.getInputStream();
		for (String command : commands) {
			ps.print(command + "\n");
			Thread.sleep(500);
			printResult(input, channel);
		}
		ps.close();

		channel.disconnect();
		log.info("Connection channel closed");
		session.disconnect();
		log.info("Connection session closed");
	}

	/**
	 * sshConnectShell connect to remote machine and send multiple commands then
	 * exit.
	 * 
	 * @return console log, result of the sent commands
	 * @author nqthanh1
	 * @param host     host's IP of Linux server to connect
	 * @param OSuser   OS user to connect to Linux server
	 * @param OSpass   password to connect to Linux server
	 * @param commands single or multiple commands to send to Linux server
	 * @LastModifiedDate 11-Octorber-2019
	 * @LastModifiedBy nqthanh1
	 */
	public void sshToHost(String host, String OSuser, String OSpass, String... commands) throws Exception {

		log.info(
				"---------------------------------------------------------------------------------------------------------------------------------------");
		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		
		//connect to remote ssh
		JSch jsch = new JSch();
		Session session = jsch.getSession(OSuser, host, 22);
		log.info("SSHExec trying to connect " + OSuser + "@" + host);
		session.setPassword(OSpass);
		session.setConfig(config);
		session.connect();
		Thread.sleep(2000);
		log.info("SSH connection established");
		// execute command
		Channel channel = session.openChannel("shell");
		OutputStream out = channel.getOutputStream();
		PrintStream ps = new PrintStream(out, true);
		//get object

		
		
		// open channel
		channel.connect();
		InputStream input = channel.getInputStream();
		for (String command : commands) {
			ps.print(command + "\n");
			Thread.sleep(500);
			printResult(input, channel);
		}
		ps.close();

		channel.disconnect();
		log.info("Connection channel closed");
		session.disconnect();
		log.info("Connection session closed");
	}

	/**
	 * DESC: put in InputStream of the Channel to print out the console log of the
	 * SSH session
	 * 
	 * @param input
	 * @param channel
	 */
	private void printResult(InputStream input, Channel channel) throws Exception {
		Date startDate = new Date();
		//time out if the command execute longer than this 
		int timeout = 200;
		//timeout if there is no response within this time
		int timeoutForEachResponse = 15;
		int waitforresponseTimes = 0;
		StringBuilder sb = new StringBuilder();
		int SIZE = 1024;
		byte[] tmp = new byte[SIZE];
		while (input.available() != 0) {
//		while (true) {
//			log.info(input.available());
			while (input.available() >= 0) {
				Date endDate = new Date();
				long difference = (endDate.getTime() - startDate.getTime()) / 1000;
				int i = input.read(tmp, 0, SIZE);
				if (i <= 0)
					break;
				String str = (new String(tmp, 0, i));
				sb.append(str);
				log.info(str);			
				//check if command executed or not
				String lastMess = str.substring(str.length() - 6);
				if (difference >= timeout || lastMess.contains("]# ")) {
					break;
				}
				waitforresponseTimes = waitforresponseTimes + 1;
				Thread.sleep(1000);
				if (waitforresponseTimes >= timeoutForEachResponse) {
					log.info("timed out. command took more time than expected");
					break;
				}
			}		
			Date endDate = new Date();
			long difference = (endDate.getTime() - startDate.getTime()) / 1000;
			if (difference >= timeout) {
				log.info("timed out. command took more time than expected");
				break;
			}
		}
	}
	//method to connect channel sftp
	public ChannelSftp connectSftp() throws JSchException {
		
		java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
 
        //JSch ssh = new JSch();
        Session session = jsch.getSession(ReadData.EPUser_SSH, ReadData.EPServer, 22);
        session.setConfig(config);
        session.setPassword(ReadData.EPPass_SSH);
        session.connect();
 
        ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
        sftp.connect();
		return sftp;
		   
			
	}
	/**
	 * Upload source to destination.
	 * @return 
	 * @author pmphu
	 * @param 
	 * @param (EPUser_SSH user, EPPass_SSH pass) to connect to Linux server
	 * @param Connect to Linux server
	 * @LastModifiedDate 11-Octorber-2019
	 * @LastModifiedBy pmphu
	 */	
	
	public static void fileFetch(String host, String user, String keyLocation, String sourceDir, String destDir) {
	    JSch jsch = new JSch();
	    Session session = null;
	    try {
	        // set up session
	        session = jsch.getSession(user,host);
	        // use private key instead of username/password
	        session.setConfig(
	                "PreferredAuthentications",
	                "publickey,gssapi-with-mic,keyboard-interactive,password");
	        jsch.addIdentity(keyLocation);
	        
	        java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
	        session.connect();

	        // copy remote log file to localhost.
	        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
	        channelSftp.connect();
	        channelSftp.get(sourceDir, destDir);
	        channelSftp.exit();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.disconnect();
	    }
	} 
	
	
	
	//Method to upload/download file ssh
	private String privateKeyPath;
	
	public void connect() throws JSchException {
		JSch jsch = new JSch();
		 
        // Uncomment the line below if the FTP server requires certificate
        //jsch.addIdentity("private-key-path");

        //session = jsch.getSession("server");
        
        //Comment the line above and Uncomment the two lines below if the FTP server requires password
        session = jsch.getSession(ReadData.EPUser_SSH, ReadData.EPServer, 22);
        session.setPassword(ReadData.EPPass_SSH);

        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }
	
	
    public void upload(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(source, destination);
        sftpChannel.exit();
    }

    public void download(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(source, destination);
        sftpChannel.exit();
    }

    public void disconnect1() {
        if (session != null) {
            session.disconnect();
        }
    }
				
		
	
	
}
