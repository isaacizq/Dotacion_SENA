package Logic;

import Logic.Descuento;
import Logic.Dotacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Elementos.class)
public class Elementos_ { 

    public static volatile ListAttribute<Elementos, Descuento> descuentoList;
    public static volatile SingularAttribute<Elementos, Integer> cantidades;
    public static volatile SingularAttribute<Elementos, Integer> idelemento;
    public static volatile ListAttribute<Elementos, Dotacion> dotacionList;
    public static volatile SingularAttribute<Elementos, String> nombre;

}