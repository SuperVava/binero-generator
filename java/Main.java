public class Main {

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        int size = 8;
        ArrayGenerator generator = new ArrayGenerator(size);
        int[][] array = generator.getArray();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print("[" + array[i][j] + "] ");
            }
            System.out.println("");
        }
        System.out.print("Done.");
    }
}
