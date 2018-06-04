import java.util.ArrayList;

public class GetSystemProccess {
public ArrayList<ProcessHandle> process;
GetSystemProccess(){
	this.process = new ArrayList<ProcessHandle>();
}
public ArrayList<ProcessHandle> getProcess(){
	ProcessHandle.allProcesses()
    .forEach(pro -> process.add(pro));
	return process;
	
}
}
