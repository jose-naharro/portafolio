package ejercicio1;

import java.util.List;
import java.util.ArrayList;

public class UtilidadesFigura {

    public static double areaMedia(List<DimensionFigura> Lista) {
        int i;
        double media, count = 0;
        for (i = 0; i < Lista.size(); i++) {
            count +=  Lista.get(i).area();
        }
        media = count / Lista.size();
        return media;
    }

    public static double perimetroMedia(List<Figura2D> Lista) {
        int i;
        double media, count = 0;

        for (i = 0; i < Lista.size(); i++) {
            count += Lista.get(i).perimetro();
        }
        media = count / Lista.size();
        return media;
    }

    public static double numSegMedia(List<Poligonos> Lista) {
        int i;
        double media, count = 0;

        for (i = 0; i < Lista.size(); i++) {
            count += Lista.get(i).getNumSegmentos();
        }
        media = count / Lista.size();
        return media;
    }


    public static double volumenMedia(List<Figura3D> Lista) {
        int i;
        double media, count = 0;

        for (i = 0; i < Lista.size(); i++) {
            count += Lista.get(i).volumen();
        }
        media = count / Lista.size() ;
        return media;
    }

    public static double numCarasMedia(List<Poliedros> Lista) {
        int i;
        double media, count = 0;

        for (i = 0; i < Lista.size(); i++) {
            count += Lista.get(i).getNumCaras();
        }
        media = count / Lista.size() ;
        return media;
    }


    public static double radioMedia(List<Curvas> Lista) {
        int i;
        double media, count = 0;

        for (i = 0; i < Lista.size(); i++) {
            count += Lista.get(i).getRadio();
        }
        media = count / Lista.size();
        return media;
    }

}
