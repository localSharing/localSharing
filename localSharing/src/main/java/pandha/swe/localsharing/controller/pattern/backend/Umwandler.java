package pandha.swe.localsharing.controller.pattern.backend;

public abstract class Umwandler<E> {

	public E wandleUm() {
		return wandleUmIntern();
	}

	protected abstract E wandleUmIntern();

}
