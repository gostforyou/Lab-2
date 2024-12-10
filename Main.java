import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Размер массива
        System.out.print("Введите размер массива N (строки): ");
        int N = scanner.nextInt();
        System.out.print("Введите размер массива M (столбцы): ");
        int M = scanner.nextInt();

        int[][] originA = new int[N][M];

        // Заполнение массива
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                originA[i][j] = scanner.nextInt();
            }
        }

        // 1: Сортировка столбцов по возрастанию
        int[][] array1 = copyA(originA, N, M);
        sortColumns(array1, N, M);
        System.out.println("Массив после сортировки столбцов по возрастанию:");
        printArray(array1, N, M);

        // 2: Сортировка строк по убыванию
        int[][] array2 = copyA(originA, N, M);
        sortRows(array2, N, M);
        System.out.println("Массив после сортировки строк по убыванию:");
        printArray(array2, N, M);

        // 3: Сортировка главной диагонали по возрастанию
        int[][] array3 = copyA(originA, N, M);
        sortMainDiagonal(array3, N);
        System.out.println("Массив после сортировки главной диагонали по возрастанию:");
        printArray(array3, N, M);

        // 4: Вычисление среднего арифметического (нечётных элементов)
        calculateMid(originA, N, M);

        // 5: Вывод элементов массива в зигзагообразном порядке
        System.out.println("Элементы массива в зигзагообразном порядке:");
        printZigzag(originA, N, M);

        // 6: Изменение элементов массива
        int[][] modArray = modifyArray(originA, N, M);
        System.out.println("Изменённый массив:");
        printArray(modArray, N, M);
    }

    // Копирование массива
    public static int[][] copyA(int[][] array, int N, int M) {
        int[][] newArray = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(array[i], 0, newArray[i], 0, M);
        }
        return newArray;
    }

    // Сортировка столбцов
    public static void sortColumns(int[][] array, int N, int M) {
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N - 1; i++) {
                for (int k = 0; k < N - i - 1; k++) {
                    if (array[k][j] > array[k + 1][j]) {
                        int temp = array[k][j];
                        array[k][j] = array[k + 1][j];
                        array[k + 1][j] = temp;
                    }
                }
            }
        }
    }

    // Сортировка строк
    public static void sortRows(int[][] array, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M - 1; j++) {
                for (int k = 0; k < M - j - 1; k++) {
                    if (array[i][k] < array[i][k + 1]) {
                        int temp = array[i][k];
                        array[i][k] = array[i][k + 1];
                        array[i][k + 1] = temp;
                    }
                }
            }
        }
    }

    // Сортировка главной диагонали
    public static void sortMainDiagonal(int[][] array, int N) {
        int minSize = Math.min(N, array[0].length);
        int[] diagonal = new int[minSize];

        // Извлекаем главную диагональ
        for (int i = 0; i < minSize; i++) {
            diagonal[i] = array[i][i];
        }

        // Сортируем диагональ
        for (int i = 0; i < diagonal.length - 1; i++) {
            for (int j = 0; j < diagonal.length - i - 1; j++) {
                if (diagonal[j] > diagonal[j + 1]) {
                    int temp = diagonal[j];
                    diagonal[j] = diagonal[j + 1];
                    diagonal[j + 1] = temp;
                }
            }
        }

        // Записываем отсортированную диагональ обратно в массив
        for (int i = 0; i < minSize; i++) {
            array[i][i] = diagonal[i];
        }
    }

    // вычисления среднего арифметического (нечётных элементов)
    public static void calculateMid(int[][] array, int N, int M) {
        int Count = 0;
        int Sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (array[i][j] % 2 != 0) {
                    Count++;
                    Sum += array[i][j];
                }
            }
        }

        double oddAverage;
        if (Count > 0) {
            oddAverage = (double) Sum / Count;
        } else {
            oddAverage = 0;
        }

        System.out.println("Среднее арифметическое нечётных элементов: " + oddAverage);
        System.out.println("Количество нечётных элементов: " + Count);
    }

    // зигзагообразный вывод
    public static void printZigzag(int[][] array, int N, int M) {
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < M; j++) {
                    System.out.print(array[i][j] + " ");
                }
            } else {
                for (int j = M - 1; j >= 0; j--) {
                    System.out.print(array[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    // изменяем по условию
    public static int[][] modifyArray(int[][] array, int N, int M) {
        int[][] modifiedArray = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (array[i][j] % 2 == 0) {
                    modifiedArray[i][j] = -array[i][j];
                } else {
                    modifiedArray[i][j] = array[i][j] * array[i][j];
                }
            }
        }
        return modifiedArray;
    }

    // Как выводить массив
    public static void printArray(int[][] array, int N, int M) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}

Найти еще
