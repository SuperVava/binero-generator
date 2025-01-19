import java.util.ArrayList;
import java.util.Arrays;

public class ArrayGenerator {
    int[][] array;
    int size;

    public ArrayGenerator(int size) {
        this.size = size;
        this.array = new int[size][size];
    }

    public int[][] getArray() {
        for (int i = 0; i < size; i++) {
            while (!lineOk(array[i])) {
                array[i] = toBinary((int) (Math.random() * (Math.pow(2, size) - 1)));
            }
        }
        array = equaliseLine(array);
        System.out.println(checkEqualLine(array));
        return array;
    }

    public int[][] equaliseLine(int[][] array) {
        array = turn(array);

        //niveau d'erreur à chaque ligne
        ArrayList<Integer> errors = new ArrayList<>();
        for(int[] line : array) {
            int errorLevel = size/2;
            for(int number : line) errorLevel -= number;
            errors.add(errorLevel);
        }

        //indexe de chaque ligne trié si positif ou negatif
        ArrayList<Integer> lineMajor = new ArrayList<>();
        ArrayList<Integer> lineMinor = new ArrayList<>();
        for (int i = 0; i < size; i++){
            if(errors.get(i) < 0) for(int j = errors.get(i); j < 0; j++) lineMajor.add(i);
            else if(errors.get(i) > 0) for(int j = errors.get(i); j > 0; j--)lineMinor.add(i);
        }

        //vérifie chaque indexe dans ligneMajeur et mineur, change les première valeurs compatibles dans la grille
        for (int i = 0; i < lineMajor.size(); i++){
            int j = 0;
            while (true){
                if (array[lineMajor.get(i)][j] == 1 && array[lineMinor.get(i)][j] == 0) {
                    array[lineMajor.get(i)][j] = 0;
                    array[lineMinor.get(i)][j] = 1;
                    break;
                }
                j++;
            }
        }

        return array;
    }

    private int[] toBinary(int number) {
        int[] binary = new int[size];
        int i = 0;
        while (number > 0) {
            binary[i++] = number % 2;
            number = number / 2;
        }
        return binary;
    }

    private int[][] turn(int[][] array) {
        int[][] newArray = new int[size][size];
        for (int i = 0; i < size; i++) {
            //chaque ligne

            for (int j = 0; j < size; j++) {
                //chaque chiffre
                newArray[i][j] = array[j][i];
            }
        }
        return newArray;
    }

    private boolean lineOk(int[]line){
        int numberOneCounter = 0;
        for (int number : line) numberOneCounter += number;
        return numberOneCounter == size / 2;
    }

    private boolean checkEqualLine(int[][] array){
        ArrayList<int[]> grillTestor = new ArrayList<>();
        for(int[] line : array){
            if(grillTestor.contains(line))return false;
            grillTestor.add(line);
        }
        array = turn(array);
        grillTestor.clear();
        for(int[] line : array){
            if(grillTestor.contains(line))return false;
            grillTestor.add(line);
        }
        return true;
    }
}
