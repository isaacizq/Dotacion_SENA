package Logic;

import Logic.Coordinador;
import Logic.Instructor;
import Logic.Regional;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Centro.class)
public class Centro_ { 

    public static volatile ListAttribute<Centro, Instructor> instructorList;
    public static volatile SingularAttribute<Centro, Regional> regionalIdregional;
    public static volatile ListAttribute<Centro, Coordinador> coordinadorList;
    public static volatile SingularAttribute<Centro, Integer> idcentro;
    public static volatile SingularAttribute<Centro, String> nombre;

}