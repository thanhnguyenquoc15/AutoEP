package scripts;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.jcraft.jsch.JSchException;

import frame.EPCommonFunction;
import lib.ReadData;
import lib.SSHCommands;

//import static lib.ReadData.*;
public class EPCommand {
	
    private static final boolean fail = false;
//    private ChannelSftp channelsftp = null;
//    private Session session = null;
//    private JSch jsch;
		
	protected Logger log = Logger.getLogger(this.getClass().getName());
	//protected static final Logger logger = Logger.getLogger(EPCommand.class);
	ReadData readObj = new ReadData();
	EPCommonFunction epcoObj = new EPCommonFunction();
	SSHCommands sshObjs = new SSHCommands();
	
	
@Test(enabled = fail)
   public void checkCommand() {
	
	
	sshObjs.connectToHost(readObj.EPServer, readObj.EPUser_SSH, readObj.EPPass_SSH);
	String cmd = "service vpms status";
	String cmd1 = "iaversion.php";
	//log.info(cmd1);
	sshObjs.exec(cmd1);
	sshObjs.disconnect();
	epcoObj.wait(5);
	}


@Test(enabled = fail)
public void test1() throws Exception {
	String source = "/tmp/phu/phu.sh";
	String destination = "D:\\down\\";
	sshObjs.connect();
	sshObjs.download(source, destination);
	sshObjs.disconnect();
	
}
@Test(enabled = fail)
public void test2() throws Exception {
	String source = "D:\\up\\phu.sh";
	String destination = "/tmp/phu2";
	//String cmd = "cd /tmp/";
	//String cmd1 = "cd phu2";
	//s.sshToHost(ReadData.EPServer, ReadData.EPUser_SSH, ReadData.EPPass_SSH, cmd, cmd1, "ls");
	
	sshObjs.connect();
	
	sshObjs.upload(source, destination);
	sshObjs.disconnect();
}

@Test(enabled = fail)
public void test3() throws Exception {
	String EPM = "7.2.3.0.0464";
	String com = "iaversion.php";
	String path = "D:\\DATA\\auto\\eclipse\\workspace\\AutoEP\\logEP\\Defaultsuite.log";
	sshObjs.connect();
	sshObjs.exec(com);
		
	try {
		
		String content = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		System.out.println(content);
		log.info(content);
		log.info("-------------------------------------------------------------------------");
		assertTrue(content.contains(EPM), "verify TC Passed");
		//log.assertLog(content.contains(EPM), "TC passed");
		log.info("TC Passed");
//		if(content.contains(EPM)) {
//			
//		
//		}
		//assertEquals(content, EPM);
	}
	
	catch (IOException e){
		
		e.printStackTrace();
	}
	
	
}

@Test(enabled = true)
public void test4() throws Exception {
	String S_EPM = "D:\\Patch\\EPM\\EPM_7.2.3.0.0464.tar.gz";
	String S_MPP = "D:\\Patch\\MPP\\7.2.3.0.0464.tar.gz" ;
	String D_EPM = "/tmp/Patch/EPM";
	String D_MPP = "/tmp/Patch/MPP";
	String command = "cd /tmp";
	String commandE = "tar -xzvf EPM_7.2.3.0.0464.tar.gz";
	String command2E = "bash setup.sh";
	String commandM = "tar -xzvf 7.2.3.0.0464.tar.gz";
	//String command2M = "bash setup.sh";
	//String command2 = "cd /MPP";
	String mkd = "mkdir Patch";
	String mkd1 = "mkdir Patch/EPM";
	String mkd2 = "mkdir Patch/MPP";
	String mkd3 = "cd /tmp/Patch/EPM";
	String mkd4 = "cd /tmp/Patch/MPP";
	String EPM = "7.2.3.0.0464";
	String path = "D:\\DATA\\auto\\eclipse\\workspace\\AutoEP\\logEP\\Defaultsuite.log";
	//sshObj.connect();
	sshObjs.sshToHost(readObj.EPServer, readObj.EPUser_SSH, readObj.EPPass_SSH, command, mkd, mkd1, mkd2);
	log.info("Generated directory" + mkd + mkd1 + mkd2);
	//sshObjs.sshShell(command1);
	sshObjs.connect();
	sshObjs.upload(S_EPM, D_EPM);
	sshObjs.upload(S_MPP, D_MPP);
	sshObjs.sshShell(mkd3, commandE, command2E);
	sshObjs.sshShell(mkd4, commandM, command2E);
	sshObjs.connect();
	sshObjs.exec("iaversion.php");
try {
		
		String content = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
		System.out.println(content);
		log.info(content);
		log.info("-------------------------------------------------------------------------");
		assertTrue(content.contains(EPM), "verify TC Passed");
		//log.assertLog(content.contains(EPM), "TC passed");
		log.info("TC Passed");
//		if(content.contains(EPM)) {
//			
//		
//		}
		//assertEquals(content, EPM);
	}
	
	catch (IOException e){
		
		e.printStackTrace();
	}
	
	
}


}