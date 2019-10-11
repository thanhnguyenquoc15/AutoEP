package scripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import frame.EPCommonFunction;
import lib.ReadData;
import lib.SSHCommands;

//import static lib.ReadData.*;
public class EPCommand {
    
	protected Logger log = Logger.getLogger(this.getClass().getName());
	ReadData readObj = new ReadData();
	EPCommonFunction epcoObj = new EPCommonFunction();
	
@Test
   public void checkCommand() {
	
	SSHCommands sshObj = new SSHCommands();
	//sshObj.connect(readObj.EPServer, readObj.EPUser_SSH, readObj.EPPass_SSH);
	String cmd = "service vpms status";
	String cmd1 = "iaversion.php";
	//log.info(cmd1);
	//sshObj.exec(cmd1);
	//sshObj.disconnect();
	epcoObj.wait(5);
}
}
