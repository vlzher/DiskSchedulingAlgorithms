import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Process process = new Process(98,0,false,0);
        Process process1 = new Process(183,0,false,0);
        Process process2 = new Process(37,0,false,0);
        Process process3 = new Process(122,0,false,0);
        Process process4 = new Process(14,0,false,0);
        Process process5 = new Process(124,0,false,0);
        Process process6 = new Process(65,0,false,0);
        Process process7 = new Process(67,0,false,0);


        ArrayList<Process> processesTest1 = new ArrayList<>();


        processesTest1.add(process);
        processesTest1.add(process1);
        processesTest1.add(process2);
        processesTest1.add(process3);
        processesTest1.add(process4);
        processesTest1.add(process5);
        processesTest1.add(process6);
        processesTest1.add(process7);


        Disk diskTest1 = new Disk(processesTest1, 180);

        System.out.println("FCFS");

        diskTest1.FCFS(true,53,false);
        System.out.println("SSTF");
        diskTest1.SSTF(true,53);

        System.out.println("Scan");
        diskTest1.Scan(true,53);

        System.out.println("CScan");
        diskTest1.CScan(true,53);

        ArrayList<Process> processes = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Process process0 = new Process(false);
            processes.add(process0);

        }

        Disk disk = new Disk(processes, 100);
        System.out.println("FCFS");
        disk.FCFS(true,0,false);
        System.out.println("SSTF");
        disk.SSTF(true,0);
        System.out.println("Scan");
        disk.Scan(true,0);
        System.out.println("CScan");
        disk.CScan(true,0);

        ArrayList<Process> processesRealTime = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Process process0 = new Process(false);
            Process process01 = new Process(true);
            processesRealTime.add(process0);
            processesRealTime.add(process01);

        }
        System.out.println("EDF");
        Disk diskRealTime = new Disk(processesRealTime,100);

        diskRealTime.EDF(true,0);
        System.out.println("FD-Scan");
        diskRealTime.FDScan(true,0);

        System.out.println("Test Algorytms for 10000 processes");
        ArrayList<Process> hugeTest = new ArrayList<>();
        for(int i = 0; i<5000; i++){
            Process process0 = new Process(false);
            Process process01 = new Process(true);
            hugeTest.add(process0);
            hugeTest.add(process01);

        }
        Disk hugeTestDisk = new Disk(hugeTest,100);
        System.out.println("FCFS");
        hugeTestDisk.FCFS(false,0,false);
        System.out.println("SSTF");
        hugeTestDisk.SSTF(false,0);
        System.out.println("Scan");
        hugeTestDisk.Scan(false,0);
        System.out.println("CScan");
        hugeTestDisk.CScan(false,0);
        System.out.println("EDF");
        hugeTestDisk.EDF(false,0);
        System.out.println("FD-Scan");
        hugeTestDisk.FDScan(false,0);

    }
}
