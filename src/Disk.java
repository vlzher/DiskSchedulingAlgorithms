import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Disk {
    private ArrayList<Process> processes;
    private ArrayList<Process> processes1;
    private ArrayList<Process> processesInQueue;
    private int size;
    private int positionHead;

    public Disk(int size) {
        this.processes = new ArrayList<Process>();
        this.processesInQueue = new ArrayList<Process>();
        this.size = size;
        positionHead = 0;
        processes1 = new ArrayList<Process>();

    }

    public Disk(ArrayList<Process> processes, int size) {
        this.processes = processes;
        this.processesInQueue = new ArrayList<Process>();
        this.size = size;
        positionHead = 0;
        processes1 = new ArrayList<Process>();
    }

    public int FCFS(boolean print, int positionHead,boolean processesToUpload) {
        this.positionHead = positionHead;
        ArrayList<Process> processesFCFS = new ArrayList<Process>();
        if(!processesToUpload){
            for (Process process : processes) {
                processesFCFS.add(process.clone());
            }
        }
        else{
            for (Process process : processes1) {
                processesFCFS.add(process.clone());
            }
        }
        int pass = 0;
        while (processesFCFS.size() != 0) {
            for (Process process : processesFCFS) {
                if (process.getAppearTime() >= pass) {
                    processesInQueue.add(process);
                }
            }
            processesInQueue.sort((Comparator.comparingInt(Process::getAppearTime)));
            while (processesInQueue.size() != 0) {
                pass += Math.abs(processesInQueue.get(0).getPosition() - positionHead);
                positionHead = processesInQueue.get(0).getPosition();
                if (print) {
                    System.out.println(processesInQueue.get(0).toString());
                }
                processesFCFS.remove(processesInQueue.get(0));
                processesInQueue.remove(0);

            }


        }
        if(!processesToUpload){
            System.out.println("Pass : " + pass);
        }

        return pass;
    }

    public void SSTF(boolean print, int positionHead) {
        this.positionHead = positionHead;
        int pass = 0;
        ArrayList<Process> processesSSTF = new ArrayList<Process>();
        for (Process process : processes) {
            processesSSTF.add(process.clone());
        }
        int time = 0;
        while (processesSSTF.size() != 0) {
            Process process = null;
            processesInQueue.sort((Comparator.comparingInt(Process::getAppearTime)));
            for (Process process1 : processesSSTF) {
                if (process1.getAppearTime() <= time) {
                    if (!processesInQueue.contains(process1)) {
                        processesInQueue.add(process1);
                    }
                    process = process1;
                }
            }
            for (int i = 0; i < processesInQueue.size(); i++) {
                if (process != null && Math.abs(processesInQueue.get(i).getPosition() - positionHead) < Math.abs(process.getPosition() - positionHead)) {
                    process = processesInQueue.get(i);
                }


            }
            if (processesInQueue.size() == 0) {
                time++;
            }
            if (process != null) {
                time += Math.abs(process.getPosition() - positionHead);
                pass += Math.abs(process.getPosition() - positionHead);
                positionHead = process.getPosition();

                if (print) {
                    System.out.println(process);
                }
                processesInQueue.remove(process);
                processesSSTF.remove(process);
            }

        }
        System.out.println("Pass : " + pass);

    }
    public void Scan(boolean print, int positionHead){
        this.positionHead = positionHead;
        int pass = 0;
        ArrayList<Process> processesScan = new ArrayList<Process>();
        for (Process process : processes) {
            processesScan.add(process.clone());
        }
        int time = 0;
        ArrayList<Process> queue = new ArrayList<Process>();
        while(processesScan.size()!= 0){
            for(Process process : processesScan){
                if(time+Math.abs(positionHead-process.getPosition())>= process.getAppearTime()){
                    queue.add(process);
                }
            }
            if(queue.size()==0){
                time++;
            }
            if(queue.size()-processesScan.size()!=0 && queue.size() !=0){
                if(positionHead == 0 || positionHead == this.size){
                    pass+=this.size;
                    time+=this.size;
                    if(positionHead == this.size){
                        positionHead = 0;
                    }
                    else {
                        positionHead = this.size;
                    }
                }
                else{
                    queue.sort((Comparator.comparingInt(Process::getPosition)));
                    int left  = 0;
                    int right = 0;
                    for(Process process : queue){
                        if(process.getPosition()>=this.positionHead){
                            right++;
                        }
                        else{
                            left++;
                        }
                    }
                    if(left >0 && right >0){
                        pass+=this.size*2-Math.abs(positionHead-this.size);
                        time+=this.size*2-Math.abs(positionHead-this.size);
                        positionHead = 0;
                    }
                    else if(left >0){
                        pass+=Math.abs(-positionHead);
                        time+=Math.abs(-positionHead);
                        positionHead = 0;
                    }
                    else{
                        pass+=Math.abs(this.size-positionHead);
                        time+=Math.abs(this.size-positionHead);
                        positionHead = this.size;
                    }

                }
                for(Process process : queue){
                    if(print){
                        System.out.println(process.toString1());
                    }
                    processesScan.remove(process);
                }
                queue.clear();


            }
            else{
                if(queue.size() !=0){
                    if(positionHead == this.size){
                        queue.sort((Comparator.comparingInt(Process::getPosition)));
                        pass+=Math.abs(this.size-queue.get(0).getPosition());
                        time+=Math.abs(this.size-queue.get(0).getPosition());

                    }
                    else if(positionHead == 0){
                        queue.sort((Comparator.comparingInt(Process::getPosition)));
                        Collections.reverse(queue);
                        time+=Math.abs(-queue.get(0).getPosition());
                        pass+=Math.abs(-queue.get(0).getPosition());
                    }
                    else{
                        queue.sort((Comparator.comparingInt(Process::getPosition)));
                        int left  = 0;
                        int right = 0;
                        for(Process process : queue){
                            if(process.getPosition()>=this.positionHead){
                                right++;
                            }
                            else{
                                left++;
                            }
                        }
                        if(left >0 && right >0){
                            queue.sort((Comparator.comparingInt(Process::getPosition)));
                            Collections.reverse(queue);
                            pass+=positionHead+queue.get(0).getPosition();
                            time+=positionHead+queue.get(0).getPosition();
                            positionHead = queue.get(0).getPosition();
                        }
                        else if(left >0){
                            pass+=Math.abs(-positionHead);
                            time+=Math.abs(-positionHead);
                            positionHead = 0;
                        }
                        else{
                            pass+=Math.abs(this.size-positionHead);
                            time+=Math.abs(this.size-positionHead);
                            positionHead = this.size;
                        }
                    }
                    for(Process process : queue){
                        if(print){
                            System.out.println(process.toString1());
                        }

                        processesScan.remove(process);
                    }
                    queue.clear();
                }
            }

        }
        System.out.println("Pass :"+pass);
    }
    public void CScan(boolean print, int positionHead){
        this.positionHead = positionHead;
        int pass = 0;
        ArrayList<Process> processesCScan = new ArrayList<Process>();
        for (Process process : processes) {
            processesCScan.add(process.clone());
        }
        int time = 0;
        ArrayList<Process> queue = new ArrayList<Process>();
        while(processesCScan.size()!=0){
            for(Process process : processesCScan){
                if(time+Math.abs(positionHead-process.getPosition())>= process.getAppearTime()){
                    queue.add(process);
                }
            }
            if(queue.size()==0){
                time++;
            }
            if(queue.size() !=0){
                if(queue.size()-processesCScan.size()!=0 ) {
                    time+=this.size-positionHead;
                    pass+=this.size-positionHead;
                    positionHead = 0;
                }
                else{
                    queue.sort((Comparator.comparingInt(Process::getPosition)));
                    Collections.reverse(queue);
                    time+=queue.get(0).getPosition();
                    pass+=queue.get(0).getPosition();
                    positionHead = queue.get(0).getPosition();

                }
                for(Process process : queue){
                    if(print){
                        System.out.println(process.toString1());
                    }
                    processesCScan.remove(process);
                }
                queue.clear();

            }

        }
        System.out.println("Pass: "+pass);
    }
    public void EDF(boolean print, int positionHead){
            this.positionHead = positionHead;
            int pass = 0;
            int numberOfSuccessful = 0;
            int numberOfRealTime = 0;
            int numberOfRealTimeToOperate = 0;
            ArrayList<Process> processesEDF = new ArrayList<Process>();
            for (Process process : processes) {
                processesEDF.add(process.clone());
            }
            int time = 0;
            ArrayList<Process> queue = new ArrayList<Process>();
        while(processesEDF.size()!=0){
            for(Process process : processesEDF){
                if(time>= process.getAppearTime() &&!queue.contains(process)){
                    queue.add(process);
                    if(process.isRealTime()){
                        numberOfRealTime++;
                        numberOfRealTimeToOperate++;
                    }
                }
            }
            if(queue.size()==0){
                time++;
            }
            else{
                queue.sort(Comparator.comparing(Process::isRealTime));
                Collections.reverse(queue);
                Process actualProcess = queue.get(0);
                if(actualProcess.isRealTime()){
                    for (Process process : queue){
                        if(process.isRealTime()){
                            if(process.get_deadLineTime()<actualProcess.get_deadLineTime()){
                                actualProcess = process;
                            }
                        }
                    }
                    int deadline = actualProcess.get_deadLineTime();
                    if(deadline<Math.abs(positionHead-actualProcess.getPosition())){
                        pass+=deadline;
                        time+=deadline;
                        for(Process process: processesEDF){
                            if(process.isRealTime()){
                                process.set_deadLineTime(process.get_deadLineTime()-deadline);
                            }
                        }
                        if(actualProcess.getPosition()>positionHead){
                            positionHead+=deadline;
                        }
                        else{
                            positionHead-=deadline;
                        }
                        numberOfRealTimeToOperate--;
                        queue.remove(actualProcess);
                        processesEDF.remove(actualProcess);
                        actualProcess.setIfExecuted(false);
                        if(print){
                            System.out.println(actualProcess.toString2());
                        }
                        actualProcess = null;


                    }
                    else{
                        pass+=Math.abs(positionHead-actualProcess.getPosition());
                        time+=Math.abs(positionHead-actualProcess.getPosition());
                        for(Process process: processesEDF){
                            if(process.isRealTime()){
                                process.set_deadLineTime(process.get_deadLineTime()-Math.abs(positionHead-actualProcess.getPosition()));
                            }
                        }
                        if(actualProcess.getPosition()>positionHead){
                            positionHead+=Math.abs(positionHead-actualProcess.getPosition());
                        }
                        else{
                            positionHead-=Math.abs(positionHead-actualProcess.getPosition());
                        }
                        queue.remove(actualProcess);
                        processesEDF.remove(actualProcess);
                        actualProcess.setIfExecuted(true);
                        numberOfSuccessful++;
                        numberOfRealTimeToOperate--;
                        actualProcess.setReleaseTime(time);
                        if(print){
                            System.out.println(actualProcess.toString2());
                        }
                        actualProcess = null;

                    }
                }
                else{
                    processes1.addAll(queue);
                    pass+=FCFS(print,positionHead,true);
                    processes1.clear();
                    for (Process process : queue){
                        processesEDF.remove(process);
                    }
                    queue.clear();
                }
            }

        }
        System.out.println("Number of Realtime proccesses "+numberOfRealTime);
        System.out.println("Number of Successful executed realtime processes "+ numberOfSuccessful);
        System.out.println("Pass "+pass);
    }
    public void FDScan(boolean print, int positionHead){
        this.positionHead = positionHead;
        int pass = 0;
        int numberOfSuccessful = 0;
        int numberOfRealTime = 0;
        int numberOfRealTimeToOperate = 0;
        ArrayList<Process> processesFDScan = new ArrayList<Process>();
        for (Process process : processes) {
            processesFDScan.add(process.clone());
        }
        int time = 0;
        ArrayList<Process> queue = new ArrayList<Process>();
        while(processesFDScan.size()!=0){
            for(Process process : processesFDScan){
                if(time>= process.getAppearTime() &&!queue.contains(process)){
                    queue.add(process);
                    if(process.isRealTime()){
                        numberOfRealTime++;
                        numberOfRealTimeToOperate++;
                    }
                }
            }
            if(queue.size()==0){
                time++;
            }
            else{
                queue.sort(Comparator.comparing(Process::isRealTime));
                Collections.reverse(queue);
                Process actualProcess = queue.get(0);
                if(actualProcess.isRealTime()){
                    for (Process process : queue){
                        if(process.isRealTime()){
                            if(process.get_deadLineTime()<actualProcess.get_deadLineTime()){
                                actualProcess = process;
                            }
                        }
                    }
                    int deadline = actualProcess.get_deadLineTime();
                    if(deadline<Math.abs(positionHead-actualProcess.getPosition())){
                        numberOfRealTimeToOperate--;
                        queue.remove(actualProcess);
                        processesFDScan.remove(actualProcess);
                        actualProcess.setIfExecuted(false);
                        if(print){
                            System.out.println(actualProcess.toString2());
                        }
                        actualProcess = null;


                    }
                    else{
                        pass+=Math.abs(positionHead-actualProcess.getPosition());
                        time+=Math.abs(positionHead-actualProcess.getPosition());
                        for(Process process: processesFDScan){
                            if(process.isRealTime()){
                                process.set_deadLineTime(process.get_deadLineTime()-Math.abs(positionHead-actualProcess.getPosition()));
                            }
                        }
                        int positionHeadOld =positionHead;
                        if(actualProcess.getPosition()>positionHead){
                            positionHead+=Math.abs(positionHead-actualProcess.getPosition());
                        }
                        else{
                            positionHead-=Math.abs(positionHead-actualProcess.getPosition());
                        }
                        ArrayList<Process> toRemove = new ArrayList<>();

                        for(Process process : queue){
                            if(((process.getPosition()>= positionHeadOld && process.getPosition()<= positionHead) || (process.getPosition()<= positionHeadOld && process.getPosition()>= positionHead)) && !process.equals(actualProcess)){
                                toRemove.add(process);
                                if(process.isRealTime()){
                                    numberOfSuccessful++;
                                    process.setIfExecuted(true);
                                    process.setReleaseTime(time);
                                }
                                if(print){
                                    System.out.println(process.toString2());
                                }


                            }
                        }
                        for(Process process: toRemove){
                            processesFDScan.remove(process);
                            queue.remove(process);
                        }
                        toRemove.clear();
                        queue.remove(actualProcess);
                        processesFDScan.remove(actualProcess);
                        actualProcess.setIfExecuted(true);
                        numberOfSuccessful++;
                        numberOfRealTimeToOperate--;
                        actualProcess.setReleaseTime(time);
                        if(print){
                            System.out.println(actualProcess.toString2());
                        }
                        actualProcess = null;

                    }
                }
                else{
                    processes1.addAll(queue);
                    pass+=FCFS(print,positionHead,true);
                    processes1.clear();
                    for (Process process : queue){
                        processesFDScan.remove(process);
                    }
                    queue.clear();
                }

            }


        }
        System.out.println("Number of Realtime proccesses "+numberOfRealTime);
        System.out.println("Number of Successful executed realtime processes "+ numberOfSuccessful);
        System.out.println("Pass "+pass);
    }
}


