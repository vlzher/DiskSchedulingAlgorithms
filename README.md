# Disk Scheduling Algorithms

This Java program demonstrates various disk scheduling algorithms, including FCFS (First-Come, First-Served), SSTF (Shortest Seek Time First), SCAN, C-SCAN (Circular SCAN), EDF (Earliest Deadline First), and FD-SCAN (Fixed Deadline SCAN).

## Disk Class

The `Disk` class represents a disk with a certain size. It contains a list of processes and provides methods for different disk scheduling algorithms.

### Constructor

- `Disk(int size)`: Creates a new disk with the specified size.

### Methods

- `FCFS(boolean print, int positionHead, boolean processesToUpload)`: Implements the FCFS disk scheduling algorithm. It takes three parameters: `print` indicates whether to print the details of the executed processes, `positionHead` is the initial position of the disk head, and `processesToUpload` specifies whether to upload the processes from the `processes1` list. It returns the total head movement (pass) required to execute all the processes.

- `SSTF(boolean print, int positionHead)`: Implements the SSTF disk scheduling algorithm. It takes two parameters: `print` indicates whether to print the details of the executed processes, and `positionHead` is the initial position of the disk head.

- `Scan(boolean print, int positionHead)`: Implements the SCAN disk scheduling algorithm. It takes two parameters: `print` indicates whether to print the details of the executed processes, and `positionHead` is the initial position of the disk head.

- `CScan(boolean print, int positionHead)`: Implements the C-SCAN disk scheduling algorithm. It takes two parameters: `print` indicates whether to print the details of the executed processes, and `positionHead` is the initial position of the disk head.

- `EDF(boolean print, int positionHead)`: Implements the EDF disk scheduling algorithm. It takes two parameters: `print` indicates whether to print the details of the executed processes, and `positionHead` is the initial position of the disk head.

- `FDScan(boolean print, int positionHead)`: Implements the FD-SCAN disk scheduling algorithm. It takes two parameters: `print` indicates whether to print the details of the executed processes, and `positionHead` is the initial position of the disk head.
