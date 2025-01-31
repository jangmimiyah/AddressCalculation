//in this address calculation emerut... you can type in the missing parts such as if youre missing the index, esize, base, Upper bounds, or the adress calculation itself. it will help ya solve it!

import java.util.Scanner;

public class Main2 {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

// Ask for the number of dimensions either 1-4D lang hehe
System.out.print("Enter the number of dimensions (1-4): ");
int numDimensions = getIntInput(scanner, 1, 4, "Please enter a number between 1 and 4 for the number of dimensions.");

// Ask for the base
int baseAddress = getBaseAddress(scanner);

// Ask for esize
int elementSize = getElementSize(scanner);

// Ask for calculated address if ever meron nang na provide
int targetAddress = getTargetAddress(scanner, baseAddress);

// Get the upper bounds and target index for all dimensions in one go
int[] upperBounds = new int[numDimensions];
int[] targetIndices = new int[numDimensions];
for (int i = 0; i < numDimensions; i++) {
upperBounds[i] = getUpperBound(scanner, i);
targetIndices[i] = getTargetIndex(scanner, i);
}

// Calculate the total number of elements
int totalElements = 1;
for (int bound : upperBounds) {
totalElements *= bound;
}
System.out.println("The total number of elements is: " + totalElements);

// If the calculated address was not provided, calculate it using base address and offsets
if (targetAddress == -1) {
System.out.println("Target address is missing, calculating...");
targetAddress = calculateTargetAddress(baseAddress, upperBounds, targetIndices, elementSize);
System.out.println("The calculated target address is: " + targetAddress);
}

// Calculate the address of the target element
int address = calculateAddress(baseAddress, elementSize, upperBounds, targetIndices);
System.out.println("The address of the target element is: " + address);
}

public static int getIntInput(Scanner scanner, int min, int max, String errorMessage) {
int input = scanner.nextInt();
while (input < min || input > max) {
System.out.println(errorMessage);
input = scanner.nextInt();
}
return input;
}

public static int getBaseAddress(Scanner scanner) {
System.out.print("Enter the base address (Alpha) or type -1 if missing: ");
int baseAddress = scanner.nextInt();
if (baseAddress == -1) {
System.out.print("Please provide the missing base address value: ");
baseAddress = scanner.nextInt();
}
return baseAddress;
}

public static int getElementSize(Scanner scanner) {
System.out.print("Enter the element size (in bytes) or type -1 if missing: ");
int elementSize = scanner.nextInt();

// If element size is missing, calculate it
if (elementSize == -1) {
System.out.println("Element size is missing, calculating...");
elementSize = calculateElementSize(scanner);
System.out.println("The calculated element size is: " + elementSize + " bytes");
}
return elementSize;
}

public static int calculateElementSize(Scanner scanner) {
System.out.print("Enter the target address: ");
int targetAddress = scanner.nextInt();
System.out.print("Enter the base address: ");
int baseAddress = scanner.nextInt();

System.out.print("Enter the total number of elements: ");
int totalElements = scanner.nextInt();

int addressDifference = targetAddress - baseAddress;

// Calculate esize
if (addressDifference > 0 && totalElements > 0) {
return addressDifference / totalElements;
} else {
System.out.println("Invalid address difference or total elements.");
return 0; 
}
}

public static int getTargetAddress(Scanner scanner, int baseAddress) {
System.out.print("Enter the target address or type -1 if missing: ");
int targetAddress = scanner.nextInt();

// If calculated address is missing, calculate it using base, upper bounds, and target indices
if (targetAddress == -1) {
System.out.println("Target address is missing, calculating...");
targetAddress = calculateTargetAddress(baseAddress, null, null, 0);
System.out.println("The calculated target address is: " + targetAddress);
}
return targetAddress;
}

public static int calculateTargetAddress(int baseAddress, int[] upperBounds, int[] targetIndices, int elementSize) {
// Calculate the offset
int offset = 0;
int product = 1;
if (upperBounds != null && targetIndices != null) {
for (int d = upperBounds.length - 1; d >= 0; d--) {
offset += targetIndices[d] * product;
product *= upperBounds[d];
}
}
return baseAddress + (offset * elementSize);
}

public static int getUpperBound(Scanner scanner, int dimension) {
System.out.print("Enter the upper bound for dimension " + (dimension + 1) + ": ");
return scanner.nextInt();
}

public static int getTargetIndex(Scanner scanner, int dimension) {
System.out.print("Enter the target element index for dimension " + (dimension + 1) + ": ");
return scanner.nextInt();
}

public static int calculateAddress(int baseAddress, int elementSize, int[] upperBounds, int[] targetIndices) {
int offset = 0;
int product = 1;

// Dynamically calculate the offset based on the dimensions
for (int d = upperBounds.length - 1; d >= 0; d--) {
offset += targetIndices[d] * product;
product *= upperBounds[d];
}

// Final address calculation
return baseAddress + offset * elementSize;
}
}