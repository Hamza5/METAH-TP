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
        changedState.setCharAt(4, state.charAt(4));
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

    public String getUpFace(){
        return state.substring(0, 9);
    }

}
