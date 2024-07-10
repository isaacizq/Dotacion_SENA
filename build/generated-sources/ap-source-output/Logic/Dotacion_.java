package Logic;

import Logic.Area;
import Logic.Clima;
import Logic.Elementos;
import Logic.Sexo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Dotacion.class)
public class Dotacion_ { 

    public static volatile SingularAttribute<Dotacion, Clima> climaIdclima;
    public static volatile SingularAttribute<Dotacion, Integer> iddotacion;
    public static volatile SingularAttribute<Dotacion, Area> areaIdarea;
    public static volatile SingularAttribute<Dotacion, Sexo> sexoIdsexo;
    public static volatile SingularAttribute<Dotacion, Integer> cantidad;
    public static volatile SingularAttribute<Dotacion, Elementos> elementosIdelemento;

}