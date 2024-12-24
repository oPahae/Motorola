// import java.util.*;
// import java.util.regex.*;

// public class Disassembler {

//     // A class to represent a label with its name and address
//     static class Label {
//         String name;
//         int address;

//         public Label(String name, int address) {
//             this.name = name;
//             this.address = address;
//         }

//         @Override
//         public String toString() {
//             return String.format("Label: %s, Address: 0x%04X", name, address);
//         }
//     }

//     // Function to disassemble and save label addresses
//     public static List<Label> disassemble(String assemblyCode, int startAddress) {
//         String[] lines = assemblyCode.split("\\n"); // Split code into lines
//         Pattern labelPattern = Pattern.compile("^\\s*([A-Za-z_][A-Za-z0-9_]*):"); // Regex to match labels

//         List<Label> labels = new ArrayList<>(); // List to store labels with addresses
//         int currentAddress = startAddress; // Initialize the starting address

//         // Iterate through each line of assembly code
//         for (String line : lines) {
//             Matcher matcher = labelPattern.matcher(line);
            
//             if (matcher.find()) {
//                 // Label detected; save it with the current address
//                 String labelName = matcher.group(1);
//                 labels.add(new Label(labelName, currentAddress));
//             }

//             // Increment current address: Assuming 2 bytes per instruction (adjust as needed)
//             currentAddress += 2; 
//         }

//         return labels;
//     }

//     public static void main(String[] args) {
//         // Example assembly code
//         String assemblyInput = """
//             START:      LDA #10       ; Load 10 into A
//                         STA $4000     ; Store A into memory $4000
//             LOOP:       DEC $4000     ; Decrement memory value
//                         BNE LOOP      ; Branch to LOOP if not equal to zero
//                         RTS           ; Return from subroutine
//             END_LABEL:  NOP           ; No operation
//             """;

//         int startAddress = 0x1000; // Starting address of the ROM

//         // Disassemble the code and get label addresses
//         List<Label> labels = disassemble(assemblyInput, startAddress);

//         // Print the label addresses
//         System.out.println("Detected Labels and Addresses:");
//         for (Label label : labels) {
//             System.out.println(label);
//         }
//     }
// }
