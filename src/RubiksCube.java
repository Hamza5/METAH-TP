public class RubiksCube {

    private String state;
    public RubiksCube(String initialState){
        state = initialState;
    }

    public void up(){
        StringBuilder changedState = new StringBuilder(state);
        // The top face
        changedState.setCharAt(0, state.charAt(6));
        changedState.setCharAt(1, state.charAt(3));
        changedState.setCharAt(2, state.charAt(0));
        changedState.setCharAt(3, state.charAt(7));
        changedState.setCharAt(5, state.charAt(1));
        changedState.setCharAt(6, state.charAt(8));
        changedState.setCharAt(7, state.charAt(5));
        changedState.setCharAt(8, state.charAt(2));
        // The front face
        changedState.setCharAt(9, state.charAt(27));
        changedState.setCharAt(10, state.charAt(28));
        changedState.setCharAt(11, state.charAt(29));
        // The left face
        changedState.setCharAt(18, state.charAt(9));
        changedState.setCharAt(19, state.charAt(10));
        changedState.setCharAt(20, state.charAt(11));
        // The right face
        changedState.setCharAt(27, state.charAt(36));
        changedState.setCharAt(28, state.charAt(37));
        changedState.setCharAt(29, state.charAt(38));
        // The back face
        changedState.setCharAt(36, state.charAt(18));
        changedState.setCharAt(37, state.charAt(19));
        changedState.setCharAt(38, state.charAt(20));
        // Replace the previous state
        state = changedState.toString();
    }

    public void up_inverted(){
        StringBuilder changedState = new StringBuilder(state);
        // The top face
        changedState.setCharAt(0, state.charAt(2));
        changedState.setCharAt(1, state.charAt(5));
        changedState.setCharAt(2, state.charAt(8));
        changedState.setCharAt(3, state.charAt(1));
        changedState.setCharAt(5, state.charAt(7));
        changedState.setCharAt(6, state.charAt(0));
        changedState.setCharAt(7, state.charAt(3));
        changedState.setCharAt(8, state.charAt(6));
        // The front face
        changedState.setCharAt(9, state.charAt(18));
        changedState.setCharAt(10, state.charAt(19));
        changedState.setCharAt(11, state.charAt(20));
        // The left face
        changedState.setCharAt(18, state.charAt(36));
        changedState.setCharAt(19, state.charAt(37));
        changedState.setCharAt(20, state.charAt(38));
        // The right face
        changedState.setCharAt(27, state.charAt(9));
        changedState.setCharAt(28, state.charAt(10));
        changedState.setCharAt(29, state.charAt(11));
        // The back face
        changedState.setCharAt(36, state.charAt(27));
        changedState.setCharAt(37, state.charAt(28));
        changedState.setCharAt(38, state.charAt(29));
        // Replace the previous state
        state = changedState.toString();
    }

    public void left(){
        StringBuilder changedState = new StringBuilder(state);
        // The top face
        changedState.setCharAt(0, state.charAt(44));
        changedState.setCharAt(3, state.charAt(41));
        changedState.setCharAt(6, state.charAt(38));
        // The front face
        changedState.setCharAt(9, state.charAt(0));
        changedState.setCharAt(12, state.charAt(3));
        changedState.setCharAt(15, state.charAt(6));
        // The left face
        changedState.setCharAt(18, state.charAt(24));
        changedState.setCharAt(19, state.charAt(21));
        changedState.setCharAt(20, state.charAt(18));
        changedState.setCharAt(21, state.charAt(25));
        changedState.setCharAt(23, state.charAt(19));
        changedState.setCharAt(24, state.charAt(26));
        changedState.setCharAt(25, state.charAt(23));
        changedState.setCharAt(26, state.charAt(20));
        // The back face
        changedState.setCharAt(38, state.charAt(47));
        changedState.setCharAt(41, state.charAt(50));
        changedState.setCharAt(44, state.charAt(53));
        // The bottom face
        changedState.setCharAt(47, state.charAt(15));
        changedState.setCharAt(50, state.charAt(12));
        changedState.setCharAt(53, state.charAt(9));
        // Replace the previous state
        state = changedState.toString();
    }
    
    public void BottomLeft(){
        StringBuilder changedState = new StringBuilder(state);
       
      
        changedState.setCharAt(15, state.charAt(33));
        changedState.setCharAt(16, state.charAt(34));
        changedState.setCharAt(17, state.charAt(35));
        
        changedState.setCharAt(24, state.charAt(15));
        changedState.setCharAt(25, state.charAt(16));
        changedState.setCharAt(26, state.charAt(17));
        
        changedState.setCharAt(33, state.charAt(42));
        changedState.setCharAt(34, state.charAt(43));
        changedState.setCharAt(35, state.charAt(44));
       
        changedState.setCharAt(42, state.charAt(24));
        changedState.setCharAt(43, state.charAt(25));
        changedState.setCharAt(44, state.charAt(26));
        
        changedState.setCharAt(45, state.charAt(47));
        changedState.setCharAt(46, state.charAt(50));
        changedState.setCharAt(47, state.charAt(53));
        changedState.setCharAt(48, state.charAt(46));
        changedState.setCharAt(49, state.charAt(49));
        changedState.setCharAt(50, state.charAt(52));
        changedState.setCharAt(51, state.charAt(45));
        changedState.setCharAt(52, state.charAt(48));
        changedState.setCharAt(53, state.charAt(51));
        // Replace the previous state
        state = changedState.toString();
    }
    
    public void BottomRight(){
        StringBuilder changedState = new StringBuilder(state);
       
      
        changedState.setCharAt(15, state.charAt(24));
        changedState.setCharAt(16, state.charAt(25));
        changedState.setCharAt(17, state.charAt(26));
        
        changedState.setCharAt(24, state.charAt(42));
        changedState.setCharAt(25, state.charAt(42));
        changedState.setCharAt(26, state.charAt(44));
        
        changedState.setCharAt(33, state.charAt(15));
        changedState.setCharAt(34, state.charAt(16));
        changedState.setCharAt(35, state.charAt(17));
       
        changedState.setCharAt(42, state.charAt(33));
        changedState.setCharAt(43, state.charAt(34));
        changedState.setCharAt(44, state.charAt(35));
        
        changedState.setCharAt(45, state.charAt(51));
        changedState.setCharAt(46, state.charAt(48));
        changedState.setCharAt(47, state.charAt(45));
        changedState.setCharAt(48, state.charAt(52));
        changedState.setCharAt(49, state.charAt(49));
        changedState.setCharAt(50, state.charAt(46));
        changedState.setCharAt(51, state.charAt(53));
        changedState.setCharAt(52, state.charAt(50));
        changedState.setCharAt(53, state.charAt(47));
        // Replace the previous state
        state = changedState.toString();
    }
    
    public void BackLeft(){
        StringBuilder changedState = new StringBuilder(state);
       
      
        changedState.setCharAt(0, state.charAt(27));
        changedState.setCharAt(1, state.charAt(28));
        changedState.setCharAt(2, state.charAt(29));
        
        changedState.setCharAt(18, state.charAt(0));
        changedState.setCharAt(21, state.charAt(1));
        changedState.setCharAt(24, state.charAt(2));
        
        changedState.setCharAt(29, state.charAt(47));
        changedState.setCharAt(32, state.charAt(46));
        changedState.setCharAt(35, state.charAt(45));
       
        changedState.setCharAt(36, state.charAt(42));
        changedState.setCharAt(37, state.charAt(39));
        changedState.setCharAt(38, state.charAt(36));
        changedState.setCharAt(39, state.charAt(43));
        changedState.setCharAt(40, state.charAt(40));
        changedState.setCharAt(41, state.charAt(37));
        changedState.setCharAt(42, state.charAt(44));
        changedState.setCharAt(43, state.charAt(42));
        changedState.setCharAt(44, state.charAt(38));
        
        changedState.setCharAt(51, state.charAt(18));
        changedState.setCharAt(52, state.charAt(21));
        changedState.setCharAt(53, state.charAt(24));
        // Replace the previous state
        state = changedState.toString();
    }
    
    public void BackRight(){
        StringBuilder changedState = new StringBuilder(state);
       
      
        changedState.setCharAt(0, state.charAt(18));
        changedState.setCharAt(1, state.charAt(21));
        changedState.setCharAt(2, state.charAt(24));
        
        changedState.setCharAt(18, state.charAt(51));
        changedState.setCharAt(21, state.charAt(52));
        changedState.setCharAt(24, state.charAt(53));
        
        changedState.setCharAt(29, state.charAt(0));
        changedState.setCharAt(32, state.charAt(1));
        changedState.setCharAt(35, state.charAt(2));
       
        changedState.setCharAt(36, state.charAt(38));
        changedState.setCharAt(37, state.charAt(41));
        changedState.setCharAt(38, state.charAt(44));
        changedState.setCharAt(39, state.charAt(37));
        changedState.setCharAt(40, state.charAt(40));
        changedState.setCharAt(41, state.charAt(43));
        changedState.setCharAt(42, state.charAt(36));
        changedState.setCharAt(43, state.charAt(39));
        changedState.setCharAt(44, state.charAt(42));
        
        changedState.setCharAt(51, state.charAt(29));
        changedState.setCharAt(52, state.charAt(32));
        changedState.setCharAt(53, state.charAt(35));
        // Replace the previous state
        state = changedState.toString();
    }
    @Override
    public String toString(){
        StringBuilder representation = new StringBuilder();
        for (int i=0; i<54; i=i+9){
            for (int j=0; j<9; j++)
                representation.append(state.charAt(i+j));
            representation.append('\n');
        }
        return representation.toString();
    }

    public String getTopFace(){
        return state.substring(0, 9);
    }

    public String getFrontFace(){
        return state.substring(9, 18);
    }

    public String getLeftFace(){
        return state.substring(18, 27);
    }

    public String getRightFace(){
        return state.substring(27, 36);
    }

    public String getBackFace(){
        return state.substring(36, 45);
    }

    public String getBottomFace(){
        return state.substring(45, 54);
    }

}
