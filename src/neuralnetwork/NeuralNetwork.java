package neuralnetwork;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class NeuralNetwork {
    double[][] data = new double[][]{
        {0, 0}, //Umur = Muda, Kegemukan = Gemuk
        {0, 1}, //Umur = Muda, Kegemukan = Gemuk
        {1, 0}, //Umur = Paruh Baya, Kegemukan = Sangat Gemuk
        {1, 2}, //Umur = Paruh Baya, Kegemukan = Terlalu Gemuk
        {2, 2}, //Umur = Tua, Kegemukan = Terlalu Gemuk
    };

    double[] hasil = new double[]{0, 0, 0, 1, 1};
    double[] weight = new double[3];
    double[] input = new double[2];
    int MAX_EPOCH = 1000;

    public double hitung(double data[], double weight[]) {
        int data3 = 1;
        double hasil = (data[0] * weight[0]) + (data[1] * weight[1]) + (data3 * weight[2]);
        return hasil;
    }

    public static void main(String[] args) {
        double learning_rate, output, error, deteksi_error, hasil_hitung;
        int iterasi = 0, threshold = 0;
        NeuralNetwork neuralNetwork = new NeuralNetwork();

        System.out.println("Neural Network : Hipertensi\n");
        
        for (int i = 0; i < neuralNetwork.weight.length; i++) {
            neuralNetwork.weight[i] = Math.random() * 2 - 1;    
            System.out.println("Weight " + (i + 1) + ": " + neuralNetwork.weight[i]);
        }
        learning_rate = 0.8;
        System.out.println("\nMiu/Learning Rate = " + learning_rate);

        do {
            iterasi++;
            deteksi_error = 0;
            for (int i = 0; i < neuralNetwork.data.length; i++) {
                hasil_hitung = neuralNetwork.hitung(neuralNetwork.data[i], neuralNetwork.weight);
                if (hasil_hitung >= threshold) {
                    output = 1;
                } else {
                    output = 0;
                }
                error = ((double) neuralNetwork.hasil[i]) - output;
                neuralNetwork.weight[0] = neuralNetwork.weight[0] + (learning_rate * error * neuralNetwork.data[i][0]);
                neuralNetwork.weight[1] = neuralNetwork.weight[1] + (learning_rate * error * neuralNetwork.data[i][1]);
                neuralNetwork.weight[2] = neuralNetwork.weight[2] + (learning_rate * error);

                deteksi_error = deteksi_error + (error * error);

                System.out.println("\nIterasi ke-" + iterasi);
                System.out.println("Data ke " + (i+1) + " (Umur : " + neuralNetwork.data[i][0] + ", Kegemukan : " + neuralNetwork.data[i][1] + ", Target : "+neuralNetwork.hasil[i]+")");
                System.out.println("Weight 1 = " + neuralNetwork.weight[0]);
                System.out.println("Weight 2 = " + neuralNetwork.weight[1]);
                System.out.println("Weight 3 = " + neuralNetwork.weight[2]);
                System.out.println("Output = " + output);
                
            }
            System.out.println("Error = " + deteksi_error);

        } while ((deteksi_error != 0) || (iterasi < neuralNetwork.MAX_EPOCH));
        System.out.println("\nProses Learning selesai pada iterasi ke-"+iterasi);
        System.out.println("Weight 1 = " + neuralNetwork.weight[0]);
        System.out.println("Weight 2 = " + neuralNetwork.weight[1]);
        System.out.println("Weight 3 = " + neuralNetwork.weight[2]);

        System.out.println("\nPilihan Umur : \n1. Muda\n2. Paruh Baya\n3. Tua");
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Umur : ");
        int umur = input.nextInt();

        System.out.println("\nPilhan Kegemukan: \n1. Gemuk\n2. Sangat Gemuk\n3. Terlalu Gemuk");
        System.out.print("Masukkan Kegemukan : ");
        int kegemukan = input.nextInt();

        if (umur == 1) {
            neuralNetwork.input[0] = 0;
        } else if (umur == 2) {
            neuralNetwork.input[0] = 1;
        } else if (umur == 3) {
            neuralNetwork.input[0] = 2;
        } else {
            System.out.println("Error");
        }

        if (kegemukan == 1) {
            neuralNetwork.input[1] = 0;
        } else if (kegemukan == 2) {
            neuralNetwork.input[1] = 1;
        } else if (kegemukan == 3) {
            neuralNetwork.input[1] = 2;
        } else {
            System.out.println("Error");
        }

        output = neuralNetwork.hitung(neuralNetwork.input, neuralNetwork.weight);
        if (output >= threshold) {
            System.out.println("\nPasien Mengalami Hipertensi");
        } else {
            System.out.println("\nPasien Tidak Mengalami Hipertensi");
        }
    }

}
