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

    public SudokuSolver() {
        sudoku = new int[9][9]; 
        sudokuCon = new ConjuntoA[3][3];
        
    }
    
    public boolean estaEnFila(int fila, int num){
        return true;
    }
    
    public boolean estaEnColumna(int col, int num){
        return true;
    }
    
    public boolean estaEnCuadro(int fila, int col, int num){
        return true;
    }
    
    public boolean numeroValido(int fila, int col, int num){
        return true;
    } 
    
    public void resuelveSudoku(){
        
    }
    
    
    
    
}
