import java.util.List;
import java.util.ArrayList;



public class Indexed {
    
        public static String indexed_mode(String operand){
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
        public static String get_offsetreg(String Operand){
            int index = Operand.indexOf(",");
            return Operand.substring(index).replace(",", "").replace("-", "").replace("++", "").toUpperCase();
        }
        public static String concatenateChars(List<Character> charList) {
            StringBuilder stringBuilder = new StringBuilder();
    for (char c : charList) {
        stringBuilder.append(c);
        
    }

    return stringBuilder.toString();
    }
    public static String convertBinaryToHex(String binaryString) {
        
        int decimal = Integer.parseInt(binaryString, 2);

        
        String hexString = Integer.toHexString(decimal);

        return hexString;
    }
        
        public static String calcule_operand(String operand,String ACC){
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
        
        


        
        public static int offset_convert(String offset){
            
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

            
    
    
    
        
            
            
}
    

