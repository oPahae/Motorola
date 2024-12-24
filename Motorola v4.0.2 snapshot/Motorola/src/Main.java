import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void createTextFile(String content) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner un répertoire");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();

            String fileName = JOptionPane.showInputDialog("Nom du fichier (sans extension):");
            if (fileName != null && !fileName.isEmpty()) {
                Path filePath = Paths.get(selectedDirectory.getAbsolutePath(), fileName + ".asm");

                try {
                    Files.createDirectories(filePath.getParent());
                    Files.createFile(filePath);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                        writer.write(content);
                    }

                    JOptionPane.showMessageDialog(null, "Fichier créé avec succès : " + filePath);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la création du fichier : " + e.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Le nom du fichier ne peut pas être vide.", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aucun répertoire sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String readFileContent() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner un fichier");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                return content.toString();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier : " + e.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aucun fichier sélectionné.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public static void main(String[] args) {

        ComponentsPanel componentsPanel = new ComponentsPanel();
        Color color = new Color(232, 20, 5);

        LinkedHashMap<String, String> ramData = new LinkedHashMap<>();
        ramData.put("FC00", "");
        ramData.put("FC01", "");
        ramData.put("FC02", "");
        ramData.put("FC03", "");
        ramData.put("FC04", "");
        ramData.put("FC05", "");
        RAM ram = new RAM(150, 230, 535, 30, ramData, color);

        LinkedHashMap<String, String> romData = new LinkedHashMap<>();
        romData.put("0001", "");
        romData.put("0002", "");
        romData.put("0003", "");
        romData.put("0005", "");
        romData.put("0006", "");
        romData.put("0007", "");
        ROM rom = new ROM(150, 230, 700, 30, romData, color);

        Registre registreA = new Registre("A", "00", 120, 30, 130, 300, color);
        Registre registreB = new Registre("B", "00", 120, 30, 260, 300, color);
        Registre registreX = new Registre("X", "0000", 250, 30, 130, 340, color);
        Registre registreY = new Registre("Y", "0000", 250, 30, 130, 380, color);
        Registre registreU = new Registre("U", "0000", 250, 30, 130, 420, color);
        Registre registreS = new Registre("S", "0000", 250, 30, 130, 460, color);
        Registre registrePC = new Registre("PC", "FC00", 250, 30, 130, 500, color);
        Registre registreDP = new Registre("DP", "00", 150, 30, 70, 200, color);

        Registre flagN = new Registre("N", "0", 120, 30, 750, 330, color);
        Registre flagZ = new Registre("Z", "1", 120, 30, 750, 370, color);
        Registre flagV = new Registre("V", "0", 120, 30, 750, 410, color);
        Registre flagC = new Registre("C", "0", 120, 30, 750, 450, color);
        Registre flagH = new Registre("H", "0", 120, 30, 750, 490, color);

        Controler registreI = new Controler("Instruction :", "", 250, 50, 10, 10, color);
        Controler decodeur = new Controler("Décodeur", "", 250, 30, 10, 70, color);
        Controler sequenceur = new Controler("Contrôleur séquenceur", "", 250, 30, 10, 110, color);

        Binary binA = new Binary("00000000", 60, 12, registreA.x + 60, registreA.y - 12);
        Binary binB = new Binary("00000000", 60, 12, registreB.x + 60, registreB.y - 12);

        UAL ual = new UAL(138, 205, 530, 342, "", "", "", color, Color.WHITE);

        componentsPanel.add(ram.spawn());
        componentsPanel.add(rom.spawn());
        componentsPanel.add(registreA.spawn());
        componentsPanel.add(registreB.spawn());
        componentsPanel.add(registreX.spawn());
        componentsPanel.add(registreY.spawn());
        componentsPanel.add(registreU.spawn());
        componentsPanel.add(registreS.spawn());
        componentsPanel.add(registrePC.spawn());
        componentsPanel.add(registreDP.spawn());
        componentsPanel.add(flagN.spawn());
        componentsPanel.add(flagZ.spawn());
        componentsPanel.add(flagV.spawn());
        componentsPanel.add(flagC.spawn());
        componentsPanel.add(flagH.spawn());
        componentsPanel.add(registreI.spawn());
        componentsPanel.add(decodeur.spawn());
        componentsPanel.add(sequenceur.spawn());
        componentsPanel.add(binA.spawn());
        componentsPanel.add(binB.spawn());
        componentsPanel.add(ual.spawn());

        SwingUtilities.invokeLater(() -> new Motorola(componentsPanel, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Motorola motorolaGUI = (Motorola) ((JButton) e.getSource()).getTopLevelAncestor();
                String command = e.getActionCommand();
                initLines(motorolaGUI);
                x.y("");

                switch (command) {
                    case "Éxécuter":
                        execute(motorolaGUI);
                        break;
                    case "Pas à Pas":
                        stepByStep(motorolaGUI);
                        break;
                    case "Enregistrer":
                        save(motorolaGUI);
                        break;
                    case "Effacer":
                        delete(motorolaGUI);
                        break;
                    case "Télécharger":
                        newFile(motorolaGUI);
                        break;
                    case "Ouvrir":
                        openFile(motorolaGUI);
                        break;
                    case "Sortir":
                        System.exit(0);
                        break;
                }
            }

            String[] lines;
            boolean saved = false;
            int index = 0, pointeur = 0;
            LinkedHashMap<String, Integer> tikitat = new LinkedHashMap<>();

            public void initLines(Motorola gui) {
                lines = gui.textEditor.getText().replaceAll(";.*?\\n", "\n").toUpperCase().split("\\n+");
                for(int i=0; i<lines.length; i++)
                    if(lines[i].contains(":"))
                        tikitat.put(lines[i].split(":")[0], i);

                // for(Map.Entry<String, Integer> entry : tikitat.entrySet()) {
                //     x.y(entry.getKey() + " | " + entry.getValue());
                // }

                for(int i=0; i<lines.length; i++) {
                    lines[i] = lines[i].contains(":") ? "" : lines[i];
                    // x.y(lines[i]);
                }
            }

            ////////////////////////////////////////////////////////////////////

            private void execute(Motorola gui) {
                for (int i = 0; i < lines.length; i++)
                    stepByStep(gui);
            }            

            private void stepByStep(Motorola gui) {
                if (!saved) {
                    save(gui);
                    return;
                }

                if (index >= lines.length) {
                    JOptionPane.showMessageDialog(null, "Exécution pas à pas terminée !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                ual.setUAL("", "", "");

                String line = lines[index].trim();
                String[] parts = line.split("\\s+");
                String opcode = parts[0];
                String operand = parts.length > 1 ? parts[1] : null;
                Instruction instr = new Instruction(opcode, operand);
                executeOne(instr);

                int pas = operand == null ? 1 : operand.replace("#", "").replace("$", "").length();

                operand = String.format("%s", operand);
                if(!(operand.contains("#") || operand.contains("$") || operand.contains(",")))
                    pas = 1;
                    
                registreI.valLabel.setText(ram.map.get(Instruction.getAdress(pointeur)));
                registrePC.valLabel.setText(Instruction.getAdress(pointeur + 1));
                ram.setCurrent(Instruction.getAdress(pointeur));

                index ++;
                pointeur += pas == 1 ? 1 : (pas == 4 ? 3 : 2);
                index += lines[index].equals("") ? 1 : 0;
            }

            private void save(Motorola gui) {
                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i].trim();
                    String[] parts = line.split("\\s+");
                    String opcode = parts[0];
                    String operand = parts.length > 1 ? parts[1] : null;
                    Instruction instr = new Instruction(opcode, operand);
                    if (operand != null && !Instruction.goodSyntaxe(instr)) {
                        JOptionPane.showMessageDialog(null,
                                "Mauvaise Syntaxe :(\ndans la ligne : " + (i + 1) + "\n" + Instruction.errMsg, "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                saved = true;
                gui.saveButton.setText("Effacer");
                gui.stepButton.setEnabled(true);
                gui.executeButton.setEnabled(true);
                int k = 0;
                LinkedHashMap<String, String> map = new LinkedHashMap();

                for (int i = 0; i < lines.length; i++) {
                    if(lines[i].equals(""))
                        continue;
                    String line = lines[i].trim();
                    String[] parts = line.split("\\s+");
                    String opcode = parts[0];
                    String operand = parts.length > 1 ? parts[1] : null;

                    map.put(Instruction.getAdress(k++), Instruction.getCode(new Instruction(opcode, operand)));
                    
                    operand = String.format("%s", operand);
                    if (operand != null && (operand.contains("#") || operand.contains("$") || operand.contains(","))) {
                        operand = Instruction.getFilter(operand);
                        if (operand.length() > 2) {
                            if (operand.contains(",")) {
                                if(opcode.contains("TFR") || opcode.contains("EXG")) {
                                    String[] split = operand.split(",");
                                    if(split[0].toUpperCase().equals("A") && split[1].toUpperCase().equals("B") ){
                                        map.put(Instruction.getAdress(k++), "89");
                                    }
                                    if(split[0].toUpperCase().equals("A") && split[1].toUpperCase().equals("B") ){
                                        map.put(Instruction.getAdress(k++), "98");
                                    }
                                    if(split[0].toUpperCase().equals("A") && split[1].toUpperCase().equals("DP") ){
                                        map.put(Instruction.getAdress(k++), "8B");
                                    }
                                    if(split[0].toUpperCase().equals("DP") && split[1].toUpperCase().equals("A") ){
                                        map.put(Instruction.getAdress(k++), "B8");
                                    }
                                }
                                else {
                                    if(operand.startsWith("A")){
                                        String val = calcule_operand(operand, getRegistreVal("A"));
                                        map.put(Instruction.getAdress(k++), val);
                                    }
                                    else if(operand.startsWith("B")){
                                        String val = calcule_operand(operand, getRegistreVal("B"));
                                        map.put(Instruction.getAdress(k++), val.toUpperCase());
                                   }
                                   else{
                                    String val = calcule_operand(operand, null);
                                    map.put(Instruction.getAdress(k++), val);
                               }


                                }
                            } else {
                                map.put(Instruction.getAdress(k++), operand.substring(0, 2));
                                map.put(Instruction.getAdress(k++), operand.substring(2, 4));
                            }
                        }
                        if (operand.length() <= 2) {
                            map.put(Instruction.getAdress(k++), operand);
                        }
                    }
                }
                
                ram.setRAM(map);
                registrePC.valLabel.setText(Instruction.getAdress(index));
            }

            private void delete(Motorola gui) {
                saved = false;
                index = pointeur = 0;
                gui.saveButton.setText("Enregistrer");
                gui.stepButton.setEnabled(false);
                gui.executeButton.setEnabled(false);
                gui.textEditor.setText("");
                ram.setRAM(ramData);
                rom.resetROM();
                ual.setUAL("", "", "");
                registreA.valLabel.setText("00");
                registreB.valLabel.setText("00");
                registreU.valLabel.setText("0000");
                registreS.valLabel.setText("0000");
                registreX.valLabel.setText("0000");
                registreY.valLabel.setText("0000");
                registreDP.valLabel.setText("00");
                registreI.valLabel.setText("");
                registrePC.valLabel.setText("");
                flagC.valLabel.setText("0");
                flagN.valLabel.setText("0");
                flagZ.valLabel.setText("1");
                flagH.valLabel.setText("0");
                flagV.valLabel.setText("0");
                binA.valLabel.setText("00000000");
                binB.valLabel.setText("00000000");
                return;
            }

            private void newFile(Motorola gui) {
                createTextFile(gui.textEditor.getText());
            }

            private void openFile(Motorola gui) {
                gui.textEditor.setText(readFileContent());
            }

            ////////////////////////////////////////////////////////////////////

            public void executeOne(Instruction instr) {
                String str = instr.opcode.toUpperCase();
                if (str.startsWith("LD"))
                    executeLD(instr);
                if (str.startsWith("ADD"))
                    executeADD(instr);
                if (str.startsWith("SUB"))
                    executeSUB(instr);
                if (str.startsWith("ST"))
                    executeST(instr);
                if (str.startsWith("INC"))
                    executeINC(instr);
                if (str.startsWith("DEC"))
                    executeDEC(instr);
                if (str.startsWith("LSL"))
                    executeLSL(instr);
                if (str.startsWith("LSR"))
                    executeLSR(instr);
                if (str.startsWith("ROL"))
                    executeROL(instr);
                if (str.startsWith("ROR"))
                    executeROR(instr);
                if (str.startsWith("NOP"))
                    executeNOP(instr);
                if (str.startsWith("CLR"))
                    executeCLR(instr);
                if (str.startsWith("SWI"))
                    executeSWI(instr);
                if (str.startsWith("END"))
                    executeEND(instr);
                if (str.startsWith("COM"))
                    executeCOM(instr);
                if (str.startsWith("NEG"))
                    executeNEG(instr);
                if (str.startsWith("CMP"))
                    executeCMP(instr);
                if (str.startsWith("EXG"))
                    executeEXG(instr);
                if (str.startsWith("TFR"))
                    executeTFR(instr);
                if (str.startsWith("JMP"))
                    executeJMP(instr);
                if (str.startsWith("RTS"))
                    executeRTS(instr);
                if (str.startsWith("BEQ"))
                    executeBEQ(instr);

                if (str.startsWith("BEQ"))
                    executeBEQ(instr);
                if (str.startsWith("BNE"))
                    executeBNE(instr);
                if (str.startsWith("BMI"))
                    executeBMI(instr);
                if (str.startsWith("BPL"))
                    executeBPL(instr);
                if (str.startsWith("BCC"))
                    executeBCC(instr);
                if (str.startsWith("BCS"))
                    executeBCS(instr);
                if (str.startsWith("BVC"))
                    executeBVC(instr);
                if (str.startsWith("BVS"))
                    executeBVS(instr);
                if (str.startsWith("BRA"))
                    executeBRA(instr);

            }

            public String offsetshifter(String Operand) {
                // Split the operand into two parts
                String[] Split = Operand.split(",");
                if (Split.length != 2) {
                    throw new IllegalArgumentException(
                            "Operand must be in the format '$value,register' or 'value,register'.");
                }

                try {
                    // Parse the first part (the offset in hex or decimal)
                    String firstPart = Split[0].trim();
                    int value1 = 0;

                    if (firstPart.startsWith("$")) {
                        // Convert from hex string to integer
                        value1 = Integer.parseInt(firstPart.substring(1), 16); // Remove the "$" and parse as hex
                    } else {
                        // Parse as a decimal number
                        value1 = Integer.parseInt(firstPart); // Parse directly as decimal
                    }

                    // Parse the second part (register offset as string)
                    String register = Split[1].trim();
                    String registerValue = getRegistreVal(Split[1]); // Default string value for the register

                    switch (register) {
                        case "X":
                            registerValue = getRegistreVal("X"); // Default value for X
                            break;
                        case "Y":
                            registerValue = getRegistreVal("Y"); // Default value for Y
                            break;
                        case "U":
                            registerValue = getRegistreVal("U"); // Default value for U
                            break;
                        case "S":
                            registerValue = getRegistreVal("S"); // Default value for S
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid register: " + register);
                    }

                    // Convert registerValue string to integer for addition
                    int registerInt = Integer.parseInt(registerValue, 16);

                    // Add the two values
                    int result = value1 + registerInt;

                    // Convert the result to a 4-digit hex string
                    String hexResult = Integer.toHexString(result).toUpperCase();

                    // Ensure the result is padded to 4 characters
                    while (hexResult.length() < 4) {
                        hexResult = "0" + hexResult;
                    }

                    return hexResult; // Return the final 16-bit hex address
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in operand: " + Operand, e);
                }
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////
            
            public void executeCMP(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String value = getRegistreVal(reg);
                String operand = instr.operand;
                String mode = Instruction.detectMode(instr);
                int currentValue = Integer.parseInt(value, 16);
                switch (mode) {
                    case "immediat":
                        value = operand.replace("#", "").replace("$", "");
                        break;
                    case "direct":
                        String adresseDirecte = operand.replace("$", "");
                        value = rom.map.getOrDefault(registreDP.valLabel.getText() + adresseDirecte, "00");
                        break;
                    case "etendu":
                        String adresseEtendue = operand.replace("$", "");
                        value = rom.map.getOrDefault(adresseEtendue, "00");
                        break;
                    default:
                        break;

                }

                int Comp = Integer.parseInt(value, 16);
                if (currentValue - Comp == 0) {
                    flagN.valLabel.setText((currentValue - Comp) < 0 ? "1" : "0");
                    flagZ.valLabel.setText((currentValue - Comp) == 0 ? "1" : "0");
                }
                if (currentValue - Comp < 0) {
                    flagN.valLabel.setText((currentValue - Comp) < 0 ? "1" : "0");
                    flagZ.valLabel.setText((currentValue - Comp) == 0 ? "1" : "0");
                }
                if (currentValue - Comp == 0) {
                    flagN.valLabel.setText((currentValue - Comp) < 0 ? "1" : "0");
                    flagZ.valLabel.setText((currentValue - Comp) == 0 ? "1" : "0");
                }
            }

            public void executeLD(Instruction instr) {
                String reg = instr.opcode.substring(2);
                String operand = instr.operand;
                String mode = Instruction.detectMode(instr);
                String value = "";

                switch (mode) {
                    case "immediat":
                        value = operand.startsWith("#$") ? operand.substring(2) : operand.substring(1);
                        break;

                    case "direct":
                        String adresseDirecte = operand.replace("$", "");
                        value = rom.map.getOrDefault("" + registreDP.valLabel.getText() + adresseDirecte, "00" + adresseDirecte);
                        break;

                    case "etendu":
                        String adresseEtendue = operand.replace("$", "");
                        value = rom.map.getOrDefault(adresseEtendue, "00");
                        break;

                    case "indexe":
                        value = rom.map.getOrDefault(offsetshifter(operand), "00");
                        break;

                    default:
                        throw new IllegalArgumentException("Mode d'adressage inconnu : " + mode);
                }

                if (value == null || value.isEmpty()) {
                    throw new IllegalArgumentException("Valeur introuvable pour l'adresse ou l'opérande : " + operand);
                }

                flagZ.valLabel.setText(value.equals("00") ? "1" : "0");
                setRegistreVal(reg, value);
            }

            public void executeADD(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String operand = instr.operand;
                String mode = Instruction.detectMode(instr);
                String value = "";

                switch (mode) {
                    case "immediat":
                        value = operand.replace("#", "").replace("$", "");
                        break;
                    case "direct":
                        String adresseDirecte = operand.replace("$", "");
                        value = rom.map.getOrDefault(registreDP.valLabel.getText() + adresseDirecte, "00");
                        break;
                    case "etendu":
                        String adresseEtendue = operand.replace("$", "");
                        value = rom.map.getOrDefault(adresseEtendue, "00");
                        break;
                }

                String regValue = getRegistreVal(reg);
                int result = Integer.parseInt(regValue, 16) + Integer.parseInt(value, 16);

                ual.setUAL("" + value, "" + regValue, "" + String.format("%02X", result & 0xFF));

                flagC.valLabel.setText(result > 0xFF ? "1" : "0");
                flagZ.valLabel.setText((result & 0xFF) == 0 ? "1" : "0");

                setRegistreVal(reg, String.format("%02X", result & 0xFF));
            }

            public void executeSUB(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String operand = instr.operand;
                String mode = Instruction.detectMode(instr);
                String value = "";
            
                switch (mode) {
                    case "immediat":
                        value = operand.replace("#", "").replace("$", "");
                        break;
                    case "direct":
                        String adresseDirecte = operand.replace("$", "");
                        value = rom.map.getOrDefault(registreDP.valLabel.getText() + adresseDirecte, "00");
                        break;
                    case "etendu":
                        String adresseEtendue = operand.replace("$", "");
                        value = rom.map.getOrDefault(adresseEtendue, "00");
                        break;
                }
            
                String regValue = getRegistreVal(reg);
                int result = Integer.parseInt(regValue, 16) - Integer.parseInt(value, 16);
            
                ual.setUAL("" + value, "" + regValue, "" + String.format("%02X", result & 0xFF));
            
                flagC.valLabel.setText(result < 0 ? "1" : "0");
                flagZ.valLabel.setText((result & 0xFF) == 0 ? "1" : "0");
            
                setRegistreVal(reg, String.format("%02X", result & 0xFF));
            }
            
            public void executeST(Instruction instr) {
                String reg = instr.opcode.substring(2);
                String operand = instr.operand;
                String mode = Instruction.detectMode(instr);
                String value = getRegistreVal(reg);

                switch (mode) {
                    case "direct":
                        rom.map.put(registreDP.valLabel.getText() + operand.substring(1), value);
                        break;
                    case "etendu":
                        rom.map.put(operand.substring(1), value);
                        break;
                    case "indexe":
                        rom.map.put(offsetshifter(operand), value);
                        break;
                }
                rom.setROM(null);
            }

            public void executeINC(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String value = getRegistreVal(reg);
                int currentValue = Integer.parseInt(value, 16);
                int result = currentValue + 1;

                flagZ.valLabel.setText((result & 0xFF) == 0 ? "1" : "0");
                flagN.valLabel.setText((result & 0x80) != 0 ? "1" : "0");
                flagV.valLabel.setText((currentValue == 0x7F) ? "1" : "0");

                setRegistreVal(reg, String.format("%02X", result & 0xFF));
            }

            public void executeDEC(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String value = getRegistreVal(reg);
                int currentValue = Integer.parseInt(value, 16);
                int result = currentValue - 1;

                flagZ.valLabel.setText((result & 0xFF) == 0 ? "1" : "0");
                flagN.valLabel.setText((result & 0x80) != 0 ? "1" : "0");
                flagV.valLabel.setText((currentValue == 0x80) ? "1" : "0");

                setRegistreVal(reg, String.format("%02X", result & 0xFF));
            }

            public void executeLSL(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String val = getRegistreVal(reg);
                char[] bin = String.format("%8s", Integer.toBinaryString(Integer.parseInt(val, 16))).replace(' ', '0').toCharArray();

                char temp = bin[0];
                for (int i = 0; i < bin.length - 1; i++)
                    bin[i] = bin[i + 1];
                bin[bin.length - 1] = '0';
                flagC.valLabel.setText(temp + "");

                String str = new String(bin);
                setRegistreVal(reg, String.format("%2s", Integer.toHexString(Integer.parseInt(str, 2))).replace(' ', '0').toUpperCase());
            }

            public void executeLSR(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String val = getRegistreVal(reg);
                char[] bin = String.format("%8s", Integer.toBinaryString(Integer.parseInt(val, 16))).replace(' ', '0').toCharArray();

                char temp = bin[0];
                for (int i = bin.length - 2; i >= 0; i--)
                    bin[i + 1] = bin[i];
                bin[0] = '0';
                flagC.valLabel.setText(temp + "");

                String str = new String(bin);
                setRegistreVal(reg, String.format("%2s", Integer.toHexString(Integer.parseInt(str, 2))).replace(' ', '0').toUpperCase());
            }

            public void executeROL(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String val = getRegistreVal(reg);
                char[] bin = String.format("%8s", Integer.toBinaryString(Integer.parseInt(val, 16))).replace(' ', '0').toCharArray();

                char temp = flagC.valLabel.getText().charAt(0);
                flagC.valLabel.setText("" + bin[0]);
                for (int i = 0; i < bin.length - 1; i++)
                    bin[i] = bin[i + 1];
                bin[bin.length - 1] = temp;

                String str = new String(bin);
                setRegistreVal(reg, String.format("%2s", Integer.toHexString(Integer.parseInt(str, 2))).replace(' ', '0').toUpperCase());
            }

            public void executeROR(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String val = getRegistreVal(reg);
                char[] bin = String.format("%8s", Integer.toBinaryString(Integer.parseInt(val, 16))).replace(' ', '0').toCharArray();

                char temp = flagC.valLabel.getText().charAt(0);
                flagC.valLabel.setText("" + bin[bin.length-1]);
                for (int i = bin.length - 2; i >= 0; i--)
                    bin[i + 1] = bin[i];
                bin[0] = temp;

                String str = new String(bin);
                setRegistreVal(reg, String.format("%2s", Integer.toHexString(Integer.parseInt(str, 2))).replace(' ', '0').toUpperCase());
            }

            public void executeNOP(Instruction instr) {
                return;
            }

            public void executeCLR(Instruction instr) {
                String reg = instr.opcode.substring(3);
                setRegistreVal(reg, "00");
            }

            public void executeSWI(Instruction instr) {
                index = lines.length;
                JOptionPane.showMessageDialog(null, "Exécution pas à pas terminée !", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            public void executeEND(Instruction instr) {
                index = lines.length;
                JOptionPane.showMessageDialog(null, "Exécution pas à pas terminée !", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            public void executeCOM(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String val = getRegistreVal(reg);
                int intValue = Integer.parseInt(val, 16);

                int complementValue = (~intValue) & 0xFF;
                setRegistreVal(reg, String.format("%02X", complementValue).toUpperCase());
            
                flagZ.valLabel.setText(complementValue == 0 ? "1" : "0");
                flagN.valLabel.setText((complementValue & 0x80) != 0 ? "1" : "0");
            }
            
            public void executeNEG(Instruction instr) {
                String reg = instr.opcode.substring(3);
                String val = getRegistreVal(reg);
                int intVal = Integer.parseInt(val, 16);
            
                int negVal = (~intVal + 1) & 0xFF;
                setRegistreVal(reg, String.format("%02X", negVal).toUpperCase());
            
                flagC.valLabel.setText(negVal == 0 ? "0" : "1");
                flagZ.valLabel.setText(negVal == 0 ? "1" : "0");
            }

            public void executeTFR(Instruction instr) {
                String reg1 = instr.operand.split(",")[0];
                String reg2 = instr.operand.split(",")[1];
                setRegistreVal(reg2, getRegistreVal(reg1));
            }

            public void executeEXG(Instruction instr) {
                String reg1 = instr.operand.split(",")[0];
                String reg2 = instr.operand.split(",")[1];
                String temp = getRegistreVal(reg2);
                setRegistreVal(reg2, getRegistreVal(reg1));
                setRegistreVal(reg1, temp);
            }

            public void executeJMP(Instruction instr) {
                String tikita = instr.operand;
                index = tikitat.get(tikita);
                x.y(pointeur);
                pointeur = 0;
                for(int i=0; i<index/2; i++) {
                    String line = lines[i].trim();
                    String[] parts = line.split("\\s+");
                    String operand = parts.length > 1 ? parts[1] : null;
    
                    int pas = operand == null ? 1 : operand.replace("#", "").replace("$", "").length();
                    registreI.valLabel.setText(ram.map.get(Instruction.getAdress(pointeur)));
                    registrePC.valLabel.setText(Instruction.getAdress(pointeur + 1));
                    ram.setCurrent(Instruction.getAdress(pointeur));
    
                    pointeur += pas == 1 ? 1 : (pas == 4 ? 3 : 2);
                }
                x.y(pointeur);
                return;
            }
            
            public void executeRTS(Instruction instr) {
                int first = 0;
                for (Map.Entry<String, Integer> entry : tikitat.entrySet()) {
                    first = entry.getValue();
                    break;
                }
                index = first;
            }

            // Branchements :)
            public void executeBEQ(Instruction instr) {
                if(flagZ.valLabel.getText().equals("1"))
                    executeJMP(instr);
            }
            
            public void executeBNE(Instruction instr) {
                if(flagZ.valLabel.getText().equals("0"))
                    executeJMP(instr);
            }
            
            public void executeBMI(Instruction instr) {
                if(flagN.valLabel.getText().equals("1"))
                    executeJMP(instr);
            }
            
            public void executeBPL(Instruction instr) {
                if(flagN.valLabel.getText().equals("0"))
                    executeJMP(instr);
            }
            
            public void executeBCC(Instruction instr) {
                if(flagC.valLabel.getText().equals("1"))
                    executeJMP(instr);
            }
            
            public void executeBCS(Instruction instr) {
                if(flagC.valLabel.getText().equals("0"))
                    executeJMP(instr);
            }
            
            public void executeBVC(Instruction instr) {
                if(flagV.valLabel.getText().equals("1"))
                    executeJMP(instr);
            }
            
            public void executeBVS(Instruction instr) {
                if(flagV.valLabel.getText().equals("0"))
                    executeJMP(instr);
            }

            public void executeBRA(Instruction instr) {
                if(true)
                    executeJMP(instr);
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////

            private String getRegistreVal(String reg) {
                if (reg == null) {
                    return "";
                }

                switch (reg) {
                    case "A":
                        return (registreA != null && registreA.valLabel != null) ? registreA.valLabel.getText() : "";
                    case "B":
                        return (registreB != null && registreA.valLabel != null) ? registreB.valLabel.getText() : "";
                    case "X":
                        return (registreX != null && registreX.valLabel != null) ? registreX.valLabel.getText() : "";
                    case "Y":
                        return (registreY != null && registreY.valLabel != null) ? registreY.valLabel.getText() : "";
                    case "U":
                        return (registreU != null && registreU.valLabel != null) ? registreU.valLabel.getText() : "";
                    case "S":
                        return (registreS != null && registreS.valLabel != null) ? registreS.valLabel.getText() : "";
                    case "DP":
                        return (registreDP != null && registreDP.valLabel != null) ? registreDP.valLabel.getText() : "";
                    default:
                        return "";
                }
            }
            
            public String indexed_mode(String operand){
            String[] Split = operand.split(",");
            int index = Split[1].indexOf(",");


            if(Split[1].subSequence(0,1).equals("-")){
                
                return "auto-dec";
                
                }
                else if(Split[1].substring(1).equals("++")){
    
                    return "post-inc";
                
                }
                else if(Split[1].substring(1).equals("+")){
                
                    return "auto-inc";}
                
                else if(Split[0].substring(0).toUpperCase().equals("A") ){
                
                        return "AAccu-dec";
                    
                    }
                    else if(Split[0].substring(0).toUpperCase().equals("B")){
                
                        return "BAccu-dec";
                    
                    }
                
            
            
            
            
            
            else if(operand.subSequence(index+1, (index+2)).equals("-")){
                
                return "auto-dec";
                
                }
            else if(operand.subSequence(index+1, (index+3)).equals("--")){
                
                return "post-inc";
                
                }
                else if(Integer.parseInt(Split[0],10)<16){
                    System.out.println(operand.subSequence(index+1, (index+2)));
                    return "4bit-dec";
                    
                    }
            
            else if(Integer.parseInt(Split[0],10)<128){
                
                return "7bit-dec";    
            }
            else if(Integer.parseInt(Split[0],10)<32767){
                
                return "15bit-dec";
            
        }
        else{
            return "indetected";
        }
        
    }
    
            public String get_offsetreg(String Operand){
                int index = Operand.indexOf(",");
                return Operand.substring(index).replace(",", "").replace("-", "").replace("++", "").toUpperCase();
            }
            
            public String concatenateChars(List<Character> charList) {
        StringBuilder stringBuilder = new StringBuilder();
for (char c : charList) {
    stringBuilder.append(c);
    
}

return stringBuilder.toString();
}

            public String convertBinaryToHex(String binaryString) {
    
    int decimal = Integer.parseInt(binaryString, 2);

    
    String hexString = Integer.toHexString(decimal);

    return hexString;
}
    
            public String calcule_operand(String operand,String ACC){
    String[] Split = operand.split(",");
    String reg = get_offsetreg(operand);
    String mode = indexed_mode(operand);
    int decalage;
        if (Split[0].substring(0).toUpperCase().equals("A") || Split[0].substring(0).toUpperCase().equals("B")){
             decalage = Integer.parseInt(ACC,16);
        }
    else{
    decalage = Integer.parseInt(Split[0],10);}
    char b0 = '0';
    char b1 = '0';
    char b2 = '0';
    char b3 = '0';
    char b4 = '0';
    char b5 = '0';
    char b6 = '0';
    char b7 = '0';
        switch (reg){
            case "X":
                b5 = '0';
                b6 = '0'; 
                break;
            case "Y":
                b5 = '1';
                b6 = '0';
                break;
            case "U":
                b5 = '0';
                b6 = '1';
                break;
            case "S":
                b5 = '1';
                b6 = '1';
                break;
                    
            default:
                break;}
        switch (mode) {
                case "auto-inc":
                    b7 = '1';
                    break;
                case "post-inc":
                    b7 = '1';     
                    b0 = '1';
                    break;
                case "post-dec":
                    b7 = '1';
                    b0 = '1';
                    b1 = '1';
                    break;
                case "auto-dec":
                    b7 = '1';
                    b1 = '1';
                    
                    break;
                case "BAccu-dec":

                    b7 = '1';    
                    b0 = '1';
                    b2 = '1';
                    break;
                    case "AAccu-dec":
                    b7 = '1';    
                    b1 = '1';
                    b2 = '1';
                    break;

                case "4bit-dec":
                String binary = String.format("%4s", Integer.toBinaryString(decalage)).replace(' ', '0');
                b7 = '0';
                b0 = binary.charAt(0);
                b1 = binary.charAt(1);
                b2 = binary.charAt(2);
                b3 = binary.charAt(3);
                
                    
                    break;
                case "7bit-dec":
                b7 = '1';
                b0 = '0';
                b1 = '0';
                b2 = '0';
                b3 = '1'; 
                    break;
                case "15bit-dec":
                b7 = '1';
                b0 = '1';
                b1 = '0';
                b2 = '0';
                b3 = '1';    
                    break;
                default:
                    break;
            
                }
            List<Character> charList = new ArrayList<>();
            charList.add(b7);
            charList.add(b6);
            charList.add(b5);
            charList.add(b4);
            charList.add(b3);
            charList.add(b2);
            charList.add(b1);
            charList.add(b0);
            
            String concatenatedString = concatenateChars(charList);
            System.out.println(concatenatedString);
            String test = convertBinaryToHex(concatenatedString);
            if(mode.equals("4bit-dec")){
                return String.format("%02x", Integer.parseInt(test, 16)).replace(' ', '0');    
            }
            if(mode.equals("7bit-dec")){
                return String.format("%02x", Integer.parseInt(test, 16)).replace(' ', '0');    
            }
            if(mode.equals("15bit-dec")){
                return String.format("%02x", Integer.parseInt(test, 16)).replace(' ', '0');    
            }
            return String.format("%02x", Integer.parseInt(test, 16)).replace(' ', '0'); 
        }
    
    
            public int offset_convert(String offset){
        
        return 1;

    }
        
    
            public String indexed_Operand(String operand){
                String[] Split = operand.split(",");
                
                    switch (get_offsetreg(Split[1])) {
                        case "X":
                            
                            break;
                        case "Y":
                            
                            break;
                        case "S":
                            
                            break;
                        case "U":
                            
                            break;
                    
                    
                        default:
                            break;
                    }
                    return operand;
                
                }

            private void setRegistreVal(String reg, String val) {
                String binVal = String.format("%8s", Integer.toBinaryString(Integer.parseInt(val, 16))).replace(' ', '0');
                val = val + "000000000000000000000000000000";
                if (reg.equals("A")) {
                    registreA.valLabel.setText(val.substring(0, 2));
                    binA.valLabel.setText(binVal);
                }
                if (reg.equals("B")) {
                    registreB.valLabel.setText(val.substring(0, 2));
                    binB.valLabel.setText(binVal);
                }
                if (reg.equals("U"))
                    registreU.valLabel.setText(val.substring(0, 4));
                if (reg.equals("S"))
                    registreS.valLabel.setText(val.substring(0, 4));
                if (reg.equals("X"))
                    registreX.valLabel.setText(val.substring(0, 4));
                if (reg.equals("Y"))
                    registreY.valLabel.setText(val.substring(0, 4));
                if (reg.equals("DP"))
                    registreDP.valLabel.setText(val.substring(0, 2));
            }

        }));
    }
}
