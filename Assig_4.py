class Node:
    def __init__(self, name, clock_time):
        self.name = name
        self.clock_time = clock_time  # in seconds

    def get_time(self):
        return self.clock_time

    def adjust_time(self, offset):
        self.clock_time += offset

class BerkeleyAlgorithm:
    def __init__(self, nodes):
        self.nodes = nodes
        self.master = nodes[0]  # first node is master

    def synchronize(self):
        print("Master Node:", self.master.name)
        
        # Step 1: Master polls all nodes
        time_diffs = []
        total_time = 0
        print("\nPolling all nodes for current time:")
        for node in self.nodes:
            time = node.get_time()
            print(f"{node.name} time: {time}")
            total_time += time
            time_diffs.append((node, time))

        # Step 2: Compute average time
        avg_time = total_time / len(self.nodes)
        print(f"\nAverage time: {avg_time:.2f}")

        # Step 3: Compute offset for each node and send adjustment
        print("\nAdjusting clocks:")
        for node, time in time_diffs:
            offset = avg_time - time
            print(f"{node.name} offset: {offset:+.2f}")
            node.adjust_time(offset)

        print("\nClocks after synchronization:")
        for node in self.nodes:
            print(f"{node.name} time: {node.get_time():.2f}")

# Example Usage
if __name__ == "__main__":
    nodes = [
        Node("Node1", 100.0),
        Node("Node2", 98.5),
        Node("Node3", 102.3),
        Node("Node4", 97.0),
    ]

    berkeley = BerkeleyAlgorithm(nodes)
    berkeley.synchronize()

