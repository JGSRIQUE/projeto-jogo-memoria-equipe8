public class numerossoma {
    double primeironumero;
    double segundonumero;
    double terceironumero;
    double quartonumero;
    double somarnumeros;
    double media;
    int qntdealg;

    public void calcularnumeros() {
         somarnumeros = primeironumero+segundonumero+terceironumero+quartonumero;
                System.out.println("A soma dos quatro algarismos é de :" +somarnumeros);
    }
    public void quantidadedealgarismos(int coletarqntdalg) {
        qntdealg = coletarqntdalg;
    }
    public void mediaaritmetica() {
        media = somarnumeros/ qntdealg;
        System.out.println("Media Aritmetica :" + media);
    }
}
