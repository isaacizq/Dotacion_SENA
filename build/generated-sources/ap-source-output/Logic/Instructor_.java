package Logic;

import Logic.CaracterizarInstructor;
import Logic.Centro;
import Logic.Coordinador;
import Logic.Descuento;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-24T12:06:48")
@StaticMetamodel(Instructor.class)
public class Instructor_ { 

    public static volatile SingularAttribute<Instructor, String> apellidos;
    public static volatile ListAttribute<Instructor, CaracterizarInstructor> caracterizarInstructorList;
    public static volatile SingularAttribute<Instructor, Centro> centroIdcentro;
    public static volatile SingularAttribute<Instructor, Integer> idinstructor;
    public static volatile ListAttribute<Instructor, Descuento> descuentoList;
    public static volatile SingularAttribute<Instructor, Coordinador> coordinadorIdcoordinador;
    public static volatile SingularAttribute<Instructor, String> correo;
    public static volatile SingularAttribute<Instructor, String> telefono;
    public static volatile SingularAttribute<Instructor, String> nombres;

}