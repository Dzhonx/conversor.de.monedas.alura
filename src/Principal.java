import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaTasa consulta = new ConsultaTasa();
        int opcion = 0;

        while (opcion != 7) {

            System.out.println("============================================");
            System.out.println("---------CONVERSOR DE MONEDAS ALURA---------");
            System.out.println("============================================");
            System.out.println("1) USD -> ARS (Peso argentino)");
            System.out.println("2) ARS -> USD (Dolar)");
            System.out.println("3) USD -> BRL (Real brasileño)");
            System.out.println("4) BRL -> USD (Dolar)");
            System.out.println("5) USD -> COP (Peso colombiano)");
            System.out.println("6) COP -> USD (Dolar)");
            System.out.println("7) Salir");
            System.out.println("--------------------------------------------");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Ingrese un número válido.\n");
                continue;
            }

            if (opcion == 7) {
                System.out.println("¡Gracias por usar el conversor!");
                break;
            }

            String base = "";
            String target = "";

            switch (opcion) {
                case 1 -> { base = "USD"; target = "ARS"; }
                case 2 -> { base = "ARS"; target = "USD"; }
                case 3 -> { base = "USD"; target = "BRL"; }
                case 4 -> { base = "BRL"; target = "USD"; }
                case 5 -> { base = "USD"; target = "COP"; }
                case 6 -> { base = "COP"; target = "USD"; }
                default -> {
                    System.out.println("Opción inválida.\n");
                    continue;
                }
            }

            System.out.print("Ingrese el monto a convertir: ");
            double monto;

            try {
                monto = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Monto inválido.\n");
                continue;
            }

            try {
                ResultadoConversion r = consulta.consultarPar(base, target, monto);

                System.out.println("\n--- RESULTADO ---");
                System.out.println("Moneda base: " + r.baseCode());
                System.out.println("Moneda destino: " + r.targetCode());
                System.out.println("Tasa: " + r.conversionRate());
                System.out.println("Monto convertido: " + r.conversionResult());
                System.out.println("-------------------\n");

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage() + "\n");
            }
        }
    }
}
