package cl.uchile.dcc.feedback.mappers;

public interface Mapper<E,M> {

	public M getSummary(E entity);
	public M getBasic(E entity);
	public M getData(E entity);
}
