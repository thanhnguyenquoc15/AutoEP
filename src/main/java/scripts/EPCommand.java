package scripts;

import static org.testng.Assert.fail;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import frame.EPCommonFunction;
import lib.ReadData;
import lib.SSHCommand;
import lib.SSHCommands;

//import static lib.ReadData.*;
public class EPCommand {
	
private static final boolean fail = false;
//    private ChannelSftp channelsftp = null;
//    private Session session = null;
//    private JSch jsch;
		
	//protected Logger log = Logger.getLogger(this.getClass().getName());
	private static final Logger logger = Logger.getLogger(EPCommand.class);
	ReadData readObj = new ReadData();
	EPCommonFunction epcoObj = new EPCommonFunction();
	SSHCommand sshObj = new SSHCommand();
	SSHCommands s = new SSHCommands();
	
	
@Test(enabled = fail)
   public void checkCommand() {
	
	
   sshObj.connect(readObj.EPServer, readObj.EPUser_SSH, readObj.EPPass_SSH);
	String cmd = "service vpms status";
	String cmd1 = "iaversion.php";
	//log.info(cmd1);
	sshObj.exec(cmd1);
	sshObj.disconnect();
	epcoObj.wait(5);
	}
//
//@Test
//@Parameters({ "PHost", "PUser", "PPass", "PLocation" })
//
//public void InstallPatch() throws InterruptedException,IOException,SQLException{
//	  
//	  String rfilename = readObj.INSTALLER_PATH + "";
//	  sshObj.connect(readObj.EPServer, readObj.EPUser_SSH, readObj.EPPass_SSH);
//	  epcoObj.DeleteFile(rfilename);
//	  Runtime runtime = Runtime.getRuntime();
//	  String cmd = "";
//	  String cmd1 = "bash setup.sh";
//	  //cmd = readObj.INSTALLER_PATH + "/setup.sh" + " " + readObj.INSTALLER_PATH + " " + PHost + " " + PUser + " " + PPass + " " + PLocation +  " " 
//	                                                  // + readObj.EPServer + " " +  readObj.EPUser_SSH + " " + readObj.EPPass_SSH;
//	  cmd = readObj.INSTALLER_PATH + " " + cmd1 + " "
//              + readObj.EPServer + " " +  readObj.EPUser_SSH + " " + readObj.EPPass_SSH;
//	  log.info(cmd);
//	  
//	  Process p1 = runtime.exec(cmd);
//	  int exitValue = p1.waitFor();
//	  System.out.println(exitValue);
//	  InputStream is = p1.getInputStream();
//	  int i = 0;
//	  while ((i = is.read()) != -1)
//		{
//			System.out.print((char) i);
//			log.debug((char) i);
//		}
//	  
//	 // epcoObj.restartServices();
//	  cmd = "service vpms resart";
//	  sshObj.connect(readObj.EPServer, readObj.EPUser_SSH, readObj.EPPass_SSH);
//	  cmd = "service appserver restart";
//	  log.info(sshObj.exec(cmd));
//	  log.info("---------------------------------------------------------------------------------------------------");
//	
//}

@Test(enabled = fail)
public void test1() throws Exception {
	String source = "/tmp/phu/phu.sh";
	String destination = "D:\\down\\";
	sshObj.connect();
	sshObj.download(source, destination);
	sshObj.disconnect1();
}
@Test
public void test2() throws Exception {
	String source = "D:\\up\\phu.txt";
	String destination = "/tmp/phu2";
	String cmd = "cd /tmp/";
	String cmd1 = "cd phu2";
	s.sshToHost(ReadData.EPServer, ReadData.EPUser_SSH, ReadData.EPPass_SSH, cmd, cmd1, "ls");
	
	//sshObj.connect();
	
	//sshObj.upload(source, destination);
}
}
