package my;

@FunctionalInterface
public interface Criterion<E> {

    boolean test(E e);

    default Criterion<E> negate() {
        return e -> !this.test(e);
    }

    default Criterion<E> and(Criterion<E> second) {
        return e -> this.test(e) && second.test(e);
    }

    default Criterion<E> or(Criterion<E> second) {
        return e -> this.test(e) || second.test(e);
    }

//    static <E> Criterion<E> negate(Criterion<E> criterion) {
//        return e -> !criterion.test(e);
//    }
//
//    static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second) {
//        return e -> first.test(e) && second.test(e);
//    }
//
//    static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second) {
//        return e -> first.test(e) || second.test(e);
//    }

}
