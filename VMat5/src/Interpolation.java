import java.text.DecimalFormat;

public class Interpolation {

    //факториал числа
   public static long fact(int n) {
        if(n <= 1)
            return 1;
        else
            return n * fact(n - 1);
   }

   public static double tCalc(double value, double x[]) {
       double t = 0;
       for(int i = 1; i < x.length; i++) {
           if((value < x[i]) & (value > x[i - 1])) {
                t = (value - x[i - 1]) / (x[i] - x[i - 1]);
           }
       }
       return t;
   }

   //Первая формула Гаусса
   public static double firstGauss(double value, double x[], double y[][]) {
       double result = 0;
       double t = tCalc(value, x);
       for(int i = 1; i < x.length; i++) {
               if((value < x[i]) & (value > x[i - 1])) {
                   result = y[i -1][0] + (t * y[i - 1][1]) + (((t * (t - 1)) / fact(2)) * y[i - 2][2])
                           + (((t * (t + 1) * (t - 1)) / fact(3)) * y[i - 2][3]);
               }
       }

       return result;
   }

   //Формула Бесселя
   public static double bessel(double value, double x[], double y[][]) {
       double result = 0;
       double t = tCalc(value, x);
       for(int i = 1; i < x.length; i++) {
           if((value < x[i]) & (value > x[i - 1])) {
               result = ((y[i - 1][0] + y[i][0]) / 2) + ((t - 0.5) * y[i - 1][1])
                       + (((t * (t - 1)) / fact(2)) * ((y[i - 2][2] + y[i - 1][2]) / 2))
                       + (((t * (t - 0.5) * (t - 1)) / fact(3)) * y[i - 2][3]);
           }
       }
       return result;
   }

   //Формула Стирлинга
   public static double stirling(double value, double x[], double y[][]) {
       double result = 0;
       double t = tCalc(value, x);
       for(int i = 1; i < x.length; i++) {
           if((value < x[i]) & (value > x[i - 1])) {
               result = y[i - 1][0] + (t * ((y[i - 2][1] + y[i - 1][1]) / 2)) + ((t / 2) * y[i - 2][2])
                       + (((t * (Math.pow(t, t) - 1)) / fact(3)) * ((y[i - 3][3] + y[i - 2][2]) / 2));
           }
       }
       return result;
   }

   //Вторая формула Гаусса
   public static double secondGauss(double value, double x[], double y[][]) {
       double result = 0;
       double t = tCalc(value, x);
       for(int i = 1; i < x.length; i++) {
           if((value < x[i]) & (value > x[i - 1])) {
               result = y[i - 1][0] + (t * y[i - 2][1]) + (((t * (t + 1)) / fact(2)) * y[i - 2][2])
                       + (((t * (t - 1) * (t + 1)) / fact(3)) * y[i - 3][3]);
           }
       }
       return result;
   }

   public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.###");

        int n = 30;
        int m = 13;
        double[] x = {1.50, 1.55, 1.60, 1.65, 1.70, 1.75, 1.80, 1.85, 1.90, 1.95, 2.00, 2.05, 2.10};
        double[][] y = new double[m][m];
        y[0][0] = 15.132;
        y[1][0] = 17.422;
        y[2][0] = 20.393;
        y[3][0] = 23.994;
        y[4][0] = 28.160;
        y[5][0] = 32.812;
        y[6][0] = 37.857;
        y[7][0] = 43.189;
        y[8][0] = 48.689;
        y[9][0] = 54.225;
        y[10][0] = 59.653;
        y[11][0] = 64.817;
        y[12][0] = 69.550;

        //Таблица разностей
        for(int i = 1; i < m; i++) {
            for(int j = 0; j < m - i; j++) {
                y[j][i] = y[j + 1][i - 1] - y[j][i-1];
            }
        }

       System.out.println("Таблица конечных разностей:");
        for(int i = 0; i < m; i++) {
            System.out.print(x[i] + " \t");
            for(int j = 0; j < m - i; j++){
                System.out.print(df.format(y[i][j]) + " \t\t");
            }
            System.out.println("");
        }

        for(int i = 1; i <= n; i++) {
            double value = 1.83 + (0.003 * i);
            System.out.println("При n = " + i + ": ");
            System.out.println("1 формула Гаусса: " + df.format(firstGauss(value, x, y)));
            System.out.println("Формула Бесселя: " + df.format(bessel(value, x, y)));
            System.out.println("Формула Стирлинга: " + df.format(stirling(value, x, y)));
            System.out.println("2 формула Гаусса: " + df.format(secondGauss(value, x, y)));
        }
   }
}
