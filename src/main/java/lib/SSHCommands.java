package lib;

import java.io.*;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SSHCommands {

	protected Logger log = Logger.getLogger(this.getClass().getName());

//	/**
//	 * Connect to remote machine and establish session.
//	 * written by POM team
//	 * @return if connect successfully, return true, else return false
//	 */
//	public Boolean connect(String sHostName, String sUserName, String sPassword) {
//		try {
//			session = jsch.getSession(sUserName, sHostName);
//			log.info("Session initialized with Host [" + sHostName + "] and associated with user [" + sUserName
//					+ "] and credential " + sPassword);
//			// session.setUserInfo(userInfo);
//			session.setPassword(sPassword);
//			config.put("StrictHostKeyChecking", "no");
//			session.setConfig(config);
//			log.info("SSHExec initialized successfully");
//			log.info("SSHExec trying to connect " + sUserName + "@" + sHostName);
//			session.connect();
//			log.info("SSH connection established");
//			return true;
//		} catch (Exception e) {
//			log.info("Connect fails with the following exception: " + e);
//			return false;
//		}
//	}
	private Session session;

	private Channel channel;

	private JSch jsch;

	private UserInfo userInfo;

	private Properties config;

	public int iExitStatus;

	public void SSHExecute() {
		jsch = new JSch();
		Session session = null;
		config = new Properties();
		iExitStatus = 0;
	}

	public Boolean connect(String sHostName, String sUserName, String sPassword) {
		try {
			session = jsch.getSession(sUserName, sHostName);
			log.info("Session initialized with Host [" + sHostName + "] and associated with user [" + sUserName
					+ "] and credential " + sPassword);
			// session.setUserInfo(userInfo);
			session.setPassword(sPassword);
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			log.info("SSHExec initialized successfully");
			log.info("SSHExec trying to connect " + sUserName + "@" + sHostName);
			session.connect();
			log.info("SSH connection established");
			return true;
		} catch (Exception e) {
			log.info("Connect fails with the following exception: " + e);
			return false;
		}
	}

	public Boolean disconnect() {
		try {
			log.info("Closing SSH connection.");
			session.disconnect();
			session = null;
			iExitStatus = 0;
			log.info("SSH connection closed successfully.");
			return true;
		} catch (Exception e) {
			log.info("Disconnect fails with the following exception: " + e.toString());
			return false;
		}
	}

	/**
	 * Connect to remote machine and send command only then exit.
	 *
	 * @return return console log of the server
	 */
	public String sshConnectExec(String command) throws JSchException, InterruptedException, IOException {
		String sRetVal = "";
		try {

			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			channel.setOutputStream(System.out);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			log.info("Connection channel established successfully");
			log.info("Start to run command: " + command);
			StringBuilder sb = new StringBuilder();
			byte[] tmp = new byte[1024];
			Date startDate = new Date();
			int timeout = 800;
			while (true) {
				while (in.available() > 0) {
					Date endDate = new Date();
					long difference = (endDate.getTime() - startDate.getTime()) / 1000;
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					String str = new String(tmp, 0, i);
					sb.append(str);
					System.out.print(str);
					log.info(str);
					if (difference >= timeout) {
						log.info("timed out. command took more than expected");
						break;
					}
				}
				if (channel.isClosed()) {
					log.info("Connection channel closed");
					// logger.putMsg(Logger.INFO,"Exit-status: " + channel.getExitStatus());
					log.info("Check if exec success or not ... ");
					iExitStatus = channel.getExitStatus();
					// r.sysout = sb.toString();
					if (iExitStatus == 0) {
						log.info("Execute successfully for command: " + command);
					} else {
						log.info("Execution failed while executing command: " + command);
						log.info("Error message: " + ((ChannelExec) channel).getErrStream());
					}
					break;
				}
				Date endDate = new Date();
				long difference = (endDate.getTime() - startDate.getTime()) / 1000;
				if (difference >= timeout) {
					log.info("timed out. command took more than expected");
					break;
				}
			}
			channel.disconnect();
			sRetVal = sb.toString();
		} catch (Exception e) {
		}
		return sRetVal;
	}
//	retain session solutions	
//	private Session getSession() throws Exception {
//	    try {
//	        ChannelExec testChannel = (ChannelExec) session.openChannel("exec");
//	        testChannel.setCommand("true");
//	        testChannel.connect();
//
//	            log.debug("Session successfully tested, use it again.");
//
//	        testChannel.exit();
//	    } catch (Throwable t) {
//	        log.info("Session terminated. Create a new one.");
//	        session = jsch.getSession(user, host, port);
//	        session.setConfig(config);
//	        session.connect();
//	    }
//	    return session;
//	}

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
		log.info("channel connected");
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

		log.info("-------------------------------------------------------------------------------------------------------");
		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		JSch jsch = new JSch();
		Session session = jsch.getSession(OSuser, host, 22);
		log.info("SSHExec trying to connect " + OSuser + "@" + host);
		session.setPassword(OSpass);
		session.setConfig(config);
		session.connect();
		Thread.sleep(2000);
		log.info("SSH connection established");
		Channel channel = session.openChannel("shell");
		OutputStream out = channel.getOutputStream();
		PrintStream ps = new PrintStream(out, true);
		// open channel
		channel.connect();
		log.info("channel connected \n");
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
//		log.debug("Entering into Method : " + Thread.currentThread().getStackTrace()[1].getMethodName());
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
				System.out.println(str);
//				PrintStream ps = 
//				log.info(str);			
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
}

