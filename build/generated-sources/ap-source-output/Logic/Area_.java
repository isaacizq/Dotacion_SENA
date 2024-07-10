package Logic;

import Logic.CaracterizarInstructor;
import Logic.Dotacion;
import Logic.Red;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile ListAttribute<Area, CaracterizarInstructor> caracterizarInstructorList;
    public static volatile SingularAttribute<Area, Integer> idarea;
    public static volatile SingularAttribute<Area, Red> redIdred;
    public static volatile ListAttribute<Area, Dotacion> dotacionList;
    public static volatile SingularAttribute<Area, String> nombre;

}