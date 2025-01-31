import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Use try-with-resources para automatic na mag-close ang scanner
        try (Scanner scanner = new Scanner(System.in)) {
            // Choices from 1-4D
            System.out.print("Enter the number of dimensions (1-4): ");
            int numDimensions = scanner.nextInt();

            // Ensure the input is within range
            if (numDimensions < 1 || numDimensions > 4) {
                System.out.println("Please enter a number between 1 and 4 for the number of dimensions.");
                return;
            }

            // Get the base address and element size
            System.out.print("Enter the base address (Alpha): ");
            int baseAddress = scanner.nextInt();

            System.out.print("Enter the element size (in bytes): ");
            int elementSize = scanner.nextInt();

            // Collect upper bounds and target indices
            int[] upperBounds = new int[numDimensions];
            int[] targetIndices = new int[numDimensions];

            for (int i = 0; i < numDimensions; i++) {
                System.out.print("Enter the upper bound for dimension " + (i + 1) + ": ");
                upperBounds[i] = scanner.nextInt();
            }

            for (int i = 0; i < numDimensions; i++) {
                System.out.print("Enter the target element index for dimension " + (i + 1) + ": ");
                targetIndices[i] = scanner.nextInt();
            }

            // Calculate the address
            int address = calculateAddress(baseAddress, elementSize, upperBounds, targetIndices);
            System.out.println("The address of the target element is: " + address);
        }
    }

    public static int calculateAddress(int baseAddress, int elementSize, int[] upperBounds, int[] targetIndices) {
        int offset = 0;
        int product = 1;

        // Calculate the offset based on the dimensions
        for (int d = upperBounds.length - 1; d >= 0; d--) {
            offset += targetIndices[d] * product;
            product *= upperBounds[d];
        }

        // Final address
        return baseAddress + offset * elementSize;
    }
}
