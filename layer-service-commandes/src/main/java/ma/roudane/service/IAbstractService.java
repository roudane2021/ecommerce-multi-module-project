package ma.roudane.service;

public abstract class IAbstractService<T> {




    public T saveEntity(T commande) {


        return null;
    }


    public abstract void doBeforeAdd();

    public abstract void doAfterAdd();
}
