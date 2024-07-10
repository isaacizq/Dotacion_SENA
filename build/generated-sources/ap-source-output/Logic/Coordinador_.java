package Logic;

import Logic.Centro;
import Logic.Instructor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Coordinador.class)
public class Coordinador_ { 

    public static volatile SingularAttribute<Coordinador, Integer> idcoordinador;
    public static volatile SingularAttribute<Coordinador, String> apellidos;
    public static volatile SingularAttribute<Coordinador, Centro> centroIdcentro;
    public static volatile ListAttribute<Coordinador, Instructor> instructorList;
    public static volatile SingularAttribute<Coordinador, String> correo;
    public static volatile SingularAttribute<Coordinador, String> nombres;

}