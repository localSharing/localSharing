package pandha.swe.localsharing.controller.pattern.backend;

public abstract class Umwandler<T, E> {

	public E wandleUm() {
		return wandleUmIntern();
	}

	protected abstract E wandleUmIntern();

}
