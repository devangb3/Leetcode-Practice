public class DailyQuestions {
    class Spreadsheet {
    int[][] grid;
    public Spreadsheet(int rows) {
        grid = new int[rows][26];
    }
    
    public void setCell(String cell, int value) {
        int[] coOrdinates = parseCell(cell);
        int row = coOrdinates[0], col = coOrdinates[1];
        grid[row][col] = value;
    }
    
    public void resetCell(String cell) {
        setCell(cell, 0);
    }
    
    public int getValue(String formula) {
        formula = formula.substring(1);
        String[] check = formula.split("\\+");
        int num1 = 0;
        if(check[0].charAt(0) >= 'A' && check[0].charAt(0) <= 'Z'){
            int[] coOrdinates = parseCell(check[0]);
            num1 = grid[coOrdinates[0]][coOrdinates[1]];
        }
        else num1 = Integer.parseInt(check[0]);
        int num2 = 0;
        if(check[1].charAt(0) >= 'A' && check[1].charAt(0) <= 'Z'){
            int[] coOrdinates = parseCell(check[1]);
            num2 = grid[coOrdinates[0]][coOrdinates[1]];
        }
        else num2 = Integer.parseInt(check[1]);
        return num1 + num2;
    }
    private int[] parseCell(String Cell){
        int col = Cell.charAt(0) - 'A';

        StringBuilder temp = new StringBuilder();
        for (int i = 1; i < Cell.length(); i++) {
            temp.append(Cell.charAt(i));
        }
        int row = Integer.parseInt(temp.toString());

        return new int[]{row, col};
    }
}
    public static void main(String[] args) {
        DailyQuestions dq = new DailyQuestions();
        Spreadsheet s = dq.new Spreadsheet(3);
        System.out.println(s.getValue("=5+7"));
        s.setCell("A1", 10);
        System.out.println(s.getValue("=A1+6"));
        s.setCell("B2", 15);
        System.out.println(s.getValue("=A1+B2"));
        s.resetCell("A1");
        System.out.println(s.getValue("=A1+B2"));
    }
}
