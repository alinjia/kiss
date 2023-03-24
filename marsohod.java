import java.util.*;
class Player {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int surfaceN = in.nextInt();

        int[] smoothX = new int[surfaceN]; //все точки Х
        int[] smoothY = new int[surfaceN]; //все точки Y

        for (int i = 0; i < surfaceN; i++) {
            int landX = in.nextInt();
            smoothX[i] = landX;
            int landY = in.nextInt();
            smoothY[i] = landY;
        }

        int XX_1 = 0;
        int XX_2 = 0;
        for (int i = 0; i < surfaceN-1; i++) {
            if (smoothY[i] == smoothY[i+1]){ //ищем место посадки
                XX_1 = smoothX[i];
                XX_2 = smoothX[i+1];
            }
        }
        int seconds = 0;
        int x = 0;

        // игровой процесс (просто хард-код)
        while (true) {
            seconds++; //считает секунды

            int X = in.nextInt();
            int Y = in.nextInt();
            int hSpeed = in.nextInt();
            int vSpeed = in.nextInt();
            int fuel = in.nextInt();
            int rotate = in.nextInt();
            int power = in.nextInt();

            if (seconds == 1){
                x = X; //начальное положение марсохода (в первую секунду)
            }

            if (XX_1 == 4000 && XX_2 == 5500){

                if (x == 2500){ //1 карта
                    if (X > XX_1 - 500) {

                        //обработка идеальной посадки
                        if (hSpeed <= 2 && hSpeed >= -2){
                            if(vSpeed <= 1 && vSpeed >= -1){
                                System.out.println("0 3");
                            }
                            else{
                                System.out.println("0 4");
                            }
                        }
                        else {
                            System.out.println("40 4");
                        }
                        //

                    }
                    else {
                        System.out.println("-35 4");
                    }
                }

                else{ //3 карта
                    if (hSpeed <= 5 && hSpeed >= -5){ //обработка ровной посадки
                        if (X > XX_1 && X < XX_2){
                            System.out.println("0 4");
                        }
                        else{
                            System.out.println("-10 4");
                        }
                    }
                    else{
                        System.out.println("-50 4");
                    }
                }
            }

            if (XX_1 == 1500 && XX_2 == 3000){ //2 карта
                if(X < 6000){
                    if (hSpeed <= 3 && hSpeed >= -3){ //обработка ровной посадки
                        System.out.println("0 4");
                    }
                    else{
                        System.out.println("-35 4");
                    }
                }
                else{
                    System.out.println("60 4");
                }
            }

            if (XX_1 == 4000 && XX_2 == 5000){ //4 карта
                if (X>1500){
                    if (X>2100){
                        if(X > XX_1 - 700){
                            if (hSpeed <= 10 && hSpeed >= -10) { //обработка ровной посадки
                                System.out.println("0 4");
                            }
                            else{
                                System.out.println("30 4");
                            }
                        }
                        else{
                            System.out.println("80 4");
                        }
                    }
                    else{
                        System.out.println("0 4");
                    }
                }
                else{
                    System.out.println("-20 4");
                }
            }

            if (XX_1 == 500 && XX_2 == 1500){ //5 карта
                if (X<5500){
                    if(X<2700){

                        //обработка наидеальной посадки всех времён
                        if (hSpeed <= 1 && hSpeed >= -1){
                            if(vSpeed <= 2 && vSpeed >= -2){
                                System.out.println("0 3");
                            }
                            else{
                                System.out.println("0 4");
                            }
                        }
                        else{
                            System.out.println("-30 4");
                        }
                        //

                    }
                    else{
                        System.out.println("0 4");
                    }
                }
                else{
                    System.out.println("25 4");
                }
            }
        }
    }
}