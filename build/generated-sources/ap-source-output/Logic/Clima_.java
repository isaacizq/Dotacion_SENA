package Logic;

import Logic.CaracterizarInstructor;
import Logic.Dotacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Clima.class)
public class Clima_ { 

    public static volatile ListAttribute<Clima, CaracterizarInstructor> caracterizarInstructorList;
    public static volatile SingularAttribute<Clima, Integer> idclima;
    public static volatile ListAttribute<Clima, Dotacion> dotacionList;
    public static volatile SingularAttribute<Clima, String> nombre;

}