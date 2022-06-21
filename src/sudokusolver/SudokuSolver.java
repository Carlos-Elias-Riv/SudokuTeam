/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokusolver;

/**
 *
 * @author cerivera
 */
public class SudokuSolver {

    private int[][] sudoku;
    private ConjuntoA<Integer>[][] sudokuCon;
    private int[] ultimoCero = new int[2];

    public SudokuSolver() {
        int[][]sudoku = {
                {0,0,6,0,3,1,0,7,0},
                {4,3,7,0,0,5,0,0,0},
                {0,1,0,4,6,7,0,0,8},
                {0,2,9,1,7,8,3,0,0},
                {0,0,0,0,0,0,0,2,6},
                {3,0,0,0,5,0,0,0,0},
                {8,0,5,0,0,4,9,1,8},
                {0,0,3,5,0,9,0,8,7},
                {7,9,0,0,8,6,0,0,4}
        };


        sudokuCon = new ConjuntoA[3][3];

        for(int i = 0; i<9; i++)
            for(int j = 0; j< 9; j++) {
                if(sudoku[i][j]!= 0)
                    sudokuCon[i / 3][j / 3].agrega(sudoku[i][j]);
                else {
                    ultimoCero[0] = i;
                    ultimoCero[1] = j;
                }
            }
        
    }
    
    public boolean estaEnFila(int fila, int num){
        int i =0;
        while(i<9 && sudoku[fila][i]!=num){
            i++;
        }

        return i<9;
    }
    
    public boolean estaEnColumna(int col, int num){
        int i =0;
        while(i<9 && sudoku[i][col]!=num){
            i++;
        }

        return i<9;

    }
    
    public boolean estaEnCuadro(int fila, int col, int num){

        return sudokuCon[fila/3][col/3].contiene(num);
    }
    
    public boolean numeroValido(int fila, int col, int num){
        return !estaEnFila(fila, num) && !estaEnColumna(col, num) && !estaEnCuadro(fila, col, num);
    } 
    
    public void resuelveSudoku(){




    }
    
    
    
    
}
