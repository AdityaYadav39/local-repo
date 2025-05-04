import java.util.*;

public class LeaderElection {
    static final int NUM_PROCESSES = 6;
    static List<Process> processes = new ArrayList<>();
    static int leaderId = -1;

    public static void main(String[] args) throws InterruptedException {
        // Create and start processes
        for (int i = 0; i < NUM_PROCESSES; i++) {
            Process p = new Process(i + 1); // Process ID from 1 to 5
            processes.add(p);
        }

        System.out.println("\nRunning Bully Algorithm:");
        runBullyAlgorithm();

        System.out.println("\nRunning Ring Algorithm:");
        runRingAlgorithm();
    }

    // Process class (simplified, no threading)
    static class Process {
        int id;
        Process(int id) {
            this.id = id;
        }
    }

    // Bully Algorithm
    static void runBullyAlgorithm() {
        int initiator = processes.get(0).id; // Lowest ID starts the election
        System.out.println("Process " + initiator + " is initiating Bully Election...");
        int maxId = -1;
        for (Process p : processes) {
            if (p.id > initiator && p.id > maxId) {
                maxId = p.id;
            }
        }
        leaderId = maxId;
        System.out.println("Bully Algorithm elected Process " + leaderId + " as the leader.");
    }

    // Ring Algorithm
    static void runRingAlgorithm() {
        System.out.println("Process " + processes.get(0).id + " is initiating Ring Election...");
        int maxId = -1;
        for (Process p : processes) {
            if (p.id > maxId) {
                maxId = p.id;
            }
        }
        leaderId = maxId;
        System.out.println("Ring Algorithm elected Process " + leaderId + " as the leader.");
    }
}
