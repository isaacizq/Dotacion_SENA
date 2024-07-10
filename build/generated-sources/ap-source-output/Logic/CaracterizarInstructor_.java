package Logic;

import Logic.Area;
import Logic.Clima;
import Logic.Instructor;
import Logic.Sexo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(CaracterizarInstructor.class)
public class CaracterizarInstructor_ { 

    public static volatile SingularAttribute<CaracterizarInstructor, String> descripcion;
    public static volatile SingularAttribute<CaracterizarInstructor, Clima> climaIdclima;
    public static volatile SingularAttribute<CaracterizarInstructor, Integer> ano;
    public static volatile SingularAttribute<CaracterizarInstructor, Area> areaIdarea;
    public static volatile SingularAttribute<CaracterizarInstructor, Instructor> instructorIdinstructor;
    public static volatile SingularAttribute<CaracterizarInstructor, Sexo> sexoIdsexo;
    public static volatile SingularAttribute<CaracterizarInstructor, Integer> idCaracterizarInstructor;

}