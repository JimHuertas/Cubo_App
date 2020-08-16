package cubo.timer.com;

public class Scramble {
    private String scr = "";
    private String[] scr2;
    private int tam;

     public Scramble(){
         this.tam=(int)(Math.random()*(13-21+1)+21); //numeros entre 14 y 20 incluidos ambos
         String txt ="";
         scr2 = new String[tam];
         for (int i=0; i<tam; i++){
             scr =generar_scramble() + " ";
             txt += scr;
             scr2[i] = scr;
         }
         scr=txt;
     }


    public String generar_scramble(){
        String txt = "";
        //numeros entre el 1 y 6 includios ambos
        int mov = (int) (Math.random()*6+1);
        int doble = (int) (Math.random()*2);
        int prima = (int) (Math.random()*2);

        if (mov == 1){txt += "R";}
        else if (mov == 2){ txt = txt + "L"; }
        else if (mov == 3){ txt = txt + "F"; }
        else if (mov == 4){ txt = txt + "B"; }
        else if (mov == 5){ txt = txt + "D"; }
        else if (mov == 6){ txt = txt + "U"; }

        if(doble==1 && (prima==1 || prima==0)){
            txt += "2";
        }
        else if(doble==0 && prima== 1){
            txt  += "\'";
        }
        return txt;
    }

    public String get_cadena(){ return this.scr; }
    public String [] get_array_scr(){ return this.scr2; }
    public int get_tam(){ return this.tam; }
}
