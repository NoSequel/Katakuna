package io.github.nosequel.katakuna.button;

public interface Callback<T, R> {

    void accept(T t, R r);

}
